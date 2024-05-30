package hello.hellospring2.controller.DTO;

import jdk.jshell.Snippet;
import lombok.Data;

@Data
public class DiaryResponseDTO {
    private String memberGuid;
    private Long diaryId;
    private String content;
    private String imageUrl;

    public DiaryResponseDTO(String memberGuid, Long diaryId, String content, String imageUrl) {
        this.memberGuid = memberGuid;
        this.diaryId = diaryId;
        this.content = content;
        this.imageUrl = imageUrl;
    }
}
