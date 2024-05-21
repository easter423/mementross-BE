package hello.hellospring2.controller;


import hello.hellospring2.controller.DTO.DiaryRequestDTO;
import hello.hellospring2.controller.DTO.DiaryResponseDTO;
import hello.hellospring2.controller.DTO.MemberDiaryRequestDTO;
import hello.hellospring2.domain.Diary;
import hello.hellospring2.service.DiaryService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/diary")
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    /**
     * 다이어리 생성
     * @param requestDTO
     */
    @PostMapping
    public ResponseEntity<DiaryResponseDTO> createDiary(@RequestBody DiaryRequestDTO requestDTO) {
        Diary result = diaryService.createDiary(requestDTO.getMemberId(), requestDTO.getCreated(), requestDTO.getKeywords());
        return ResponseEntity.ok(new DiaryResponseDTO(requestDTO.getMemberId(), result.getId(), result.getContent()));
    }

    @Parameter(name = "memberGuid, time", description = "Me", example = "1", required = true)
    @GetMapping
    public List<DiaryResponseDTO> getDiariesWithTime(@ModelAttribute MemberDiaryRequestDTO requestDTO) {
        List<Diary> diaries = diaryService.getDiariesWithTime(requestDTO.getMemberGuid(), requestDTO.getTime());
        return diaries.stream().map(diary ->
                new DiaryResponseDTO(requestDTO.getMemberGuid(), diary.getId(), diary.getContent())).collect(Collectors.toList());
    }

    @Parameter(name = "diaryId", description = "Diary Primary Key to be updated", example = "1", required = true)
    @PatchMapping("/{diaryId}")
    public void updateDiary(@PathVariable(name = "diaryId") Long diaryId, @RequestBody String content) {
        diaryService.updateDiary(diaryId, content);
    }

    @Parameter(name = "diaryId", description = "Diary Primary Key to be deleted", example = "1", required = true)
    @DeleteMapping("/{diaryId}")
    public void deleteDiary(@PathVariable(name = "diaryId") Long diaryId) {
        diaryService.deleteDiary(diaryId);
    }
}
