package hello.hellospring2.controller;


import hello.hellospring2.controller.DTO.DiaryRequestDTO;
import hello.hellospring2.service.DiaryService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String createDiary(@RequestBody DiaryRequestDTO requestDTO) {
        String result = diaryService.createDiary(requestDTO.getMemberId(), requestDTO.getKeywords());
        return result;
    }

    @Parameter(name = "diaryId", description = "Diary Primary Key to be added", example = "1", required = true)
    @GetMapping("/{diaryId}")
    public String getDiary(@PathVariable(name = "diaryId") Long diaryId) {
        return diaryService.getDiary(diaryId);
    }

    @Parameter(name = "memberId", description = "Member Primary Key", example = "1", required = true)
    @GetMapping("/member/{memberId}")
    public List<String> getDiaries(@PathVariable(name = "memberId") Long memberId) {
        return diaryService.getDiaries(memberId);
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
