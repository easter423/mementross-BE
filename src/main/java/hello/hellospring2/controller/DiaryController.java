package hello.hellospring2.controller;


import hello.hellospring2.controller.DTO.DiaryRequestDTO;
import hello.hellospring2.service.DiaryService;
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
    public void createDiary(@RequestBody DiaryRequestDTO requestDTO) {
        diaryService.createDiary(requestDTO.getMemberId(), requestDTO.getKeywords());
    }

    @GetMapping("/{diaryId}")
    public String getDiary(@PathVariable Long diaryId) {
        return diaryService.getDiary(diaryId);
    }

    @GetMapping("/{memberId}")
    public List<String> getDiaries(@PathVariable Long memberId) {
        return diaryService.getDiaries(memberId);
    }

    @PatchMapping("/{diaryId}")
    public void updateDiary(@PathVariable Long diaryId, @RequestBody String content) {
        diaryService.updateDiary(diaryId, content);
    }

    @DeleteMapping("/{diaryId}")
    public void deleteDiary(@PathVariable Long diaryId) {
        diaryService.deleteDiary(diaryId);
    }
}
