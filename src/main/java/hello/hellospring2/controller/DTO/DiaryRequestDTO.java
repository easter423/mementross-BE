package hello.hellospring2.controller.DTO;

import lombok.Data;

import java.util.List;

@Data
public class DiaryRequestDTO {
    private Long memberId;
    private List<String> keywords;
}
