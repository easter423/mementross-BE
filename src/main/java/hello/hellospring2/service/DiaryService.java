package hello.hellospring2.service;

import hello.hellospring2.controller.DTO.*;
import hello.hellospring2.domain.Diary;
import hello.hellospring2.domain.Member;
import hello.hellospring2.repository.DiaryRepository;
import hello.hellospring2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final MemberRepository memberRepository;

    String url = "https://api.openai.com/v1/chat/completions";

    @Value("${openai.api-key}")
    private String apiKey;

    @Value("${openai.model}")
    private String model;

    String prompt = "#Context#" +
            "I want to write a diary in korean based on a Instagram post I uploaded" +
            "#Objective#" +
            "Act as a diary writer, that will write a diary based on the description of the photo and additional information provided by me." +
            "#Style#" +
            "It should be brief, its content only based on the description of the photo provided and additional information I provide. do not describe photo in the diary nor mention taking/uploading of the photo. diary should be written in past tense." +
            "#Tone#" +
            "Do not exaggerate, write as if you are a blunt person, explaining to a friend. each phrases should be connected so that they form a long sentence, starting new sentence only if necessary. Do not end phrases with '습니다'or'한다'or'했다'or'었다'. if it does, convert to '어'" +
            "#Audience#\"\n" +
            "Only I will be the one to read it.\"\n" +
            "#Response#\"\n" +
            "A short diary in korean.\"\n" +
            "#description of the photo#";

    public Diary createDiary(String memberGuid, LocalDateTime created, List<String> keywords, String imageUri){
        for (String keyword : keywords) {
            prompt += keyword;
        }

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        ChatGptRequest chatGptRequest = new ChatGptRequest();
        chatGptRequest.setModel(model);
        chatGptRequest.getMessages().add(new Message("user", prompt));

        HttpEntity<ChatGptRequest> chatGptRequestHttpEntity = new HttpEntity<>(chatGptRequest, headers);

        ResponseEntity<ChatGptResponse> response = restTemplate.postForEntity(url, chatGptRequestHttpEntity, ChatGptResponse.class);

        ChatGptResponse chatGptResponse = response.getBody();
        System.out.println(chatGptResponse.getChoices().get(0).getMessage().getContent());

        Member member = memberRepository.findById(memberGuid).get();

        Diary diary = Diary.builder()
                .member(member)
                .content(chatGptResponse.getChoices().get(0).getMessage().getContent())
                .imageUrl(imageUri)
                .created(created)
                .updated(created)
                .build();

        log.info("Diary created: {}", diary);

        diaryRepository.save(diary);

        return diary;
    }

    public List<String> getDiaries(String memberGuId) {
        Member member = memberRepository.findById(memberGuId).get();
        List<Diary> diaries = diaryRepository.findByMember(member);

        return diaries.stream()
                .map(Diary::getContent)
                .toList();
    }

    public void updateDiary(Long diaryId, String content) {
        Diary diary = diaryRepository.findById(diaryId).get();
        diary.setContent(content);
        diary.setUpdated(LocalDateTime.now());
        diaryRepository.save(diary);
    }

    public void deleteDiary(Long diaryId) {
        diaryRepository.deleteById(diaryId);
    }

    public String getDiary(Long diaryId) {
        Diary diary = diaryRepository.findById(diaryId).get();
        return diary.getContent();
    }

    public List<Diary> getDiariesWithTime(String memberGuid, LocalDateTime time) {
        Member member = memberRepository.findById(memberGuid).get();
        time = time.withHour(0).withMinute(0).withSecond(0).withNano(0);
        List<Diary> diaries = diaryRepository.findByMemberAndCreatedBetween(member, time, time.plusDays(1));

        return diaries;
    }

    public String createImageByTextDiary(String memberGuId, String content) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        DalleRequest dalleRequest = new DalleRequest();
        dalleRequest.setModel("dall-e-3");
        dalleRequest.setPrompt(content);
        dalleRequest.setN(1L);
        dalleRequest.setSize("1024x1024");


        HttpEntity<DalleRequest> dalleRequestHttpEntity = new HttpEntity<>(dalleRequest, headers);
        url = "https://api.openai.com/v1/images/generations";
        ResponseEntity<DalleResponse> response = restTemplate.postForEntity(url, dalleRequestHttpEntity, DalleResponse.class);

        DalleResponse dalleResponse = response.getBody();
        assert dalleResponse != null;

        Diary diary = diaryRepository.findByMember(memberRepository.findById(memberGuId).get()).get(0);
        diary.setCreatedImage(dalleResponse.getData().get(0).getUrl());

        return dalleResponse.getData().get(0).getUrl();
    }

}
