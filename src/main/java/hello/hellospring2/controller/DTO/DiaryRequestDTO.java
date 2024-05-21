package hello.hellospring2.controller.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(title = "Diary DTO")
public class DiaryRequestDTO {
    @Schema(description = "Member unique Id.", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private String memberId;

    @Schema(description = "Diary date.", example = "2024-01-01", requiredMode = Schema.RequiredMode.AUTO)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime created;
    @Schema(description = "Prompt Keyword list.", example = "[\"happy\"]", requiredMode = Schema.RequiredMode.AUTO)
    private List<String> keywords;


}
