package hello.hellospring2.controller.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "Diary DTO")
public class DiaryRequestDTO {
    @Schema(description = "Member unique Id.", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long memberId;
    @Schema(description = "Prompt Keyword list.", example = "[\"happy\"]", requiredMode = Schema.RequiredMode.AUTO)
    private List<String> keywords;
}
