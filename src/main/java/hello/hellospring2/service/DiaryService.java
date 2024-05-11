package hello.hellospring2.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.hellospring2.controller.DTO.ChatGptRequest;
import hello.hellospring2.controller.DTO.ChatGptResponse;
import hello.hellospring2.controller.DTO.Message;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
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

    public String createDiary(Long memberId, List<String> keywords){
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

        Member member = memberRepository.findById(memberId).get();

        Diary diary = Diary.builder()
                .member(member)
                .content(chatGptResponse.getChoices().get(0).getMessage().getContent())
                .created(LocalDateTime.now())
                .updated(LocalDateTime.now())
                .build();

        log.info("Diary created: {}", diary);

        diaryRepository.save(diary);

        return diary.getContent();
    }

    public List<String> getDiaries(Long memberId) {
        Member member = memberRepository.findById(memberId).get();
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
}
