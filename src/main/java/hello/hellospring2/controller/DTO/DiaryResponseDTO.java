package hello.hellospring2.controller.DTO;

import jdk.jshell.Snippet;
import lombok.Data;

@Data
public class DiaryResponseDTO {
    private String memberGuid;
    private Long diaryId;
    private String content;

    public DiaryResponseDTO(String memberGuid, Long diaryId, String content) {
        this.memberGuid = memberGuid;
        this.diaryId = diaryId;
        this.content = content;
    }
}
