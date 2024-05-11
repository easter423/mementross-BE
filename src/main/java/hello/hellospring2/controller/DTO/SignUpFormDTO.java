package hello.hellospring2.controller.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
@Schema(title = "Signup : 소셜 회원가입/로그인 요청 DTO")
public class SignUpFormDTO {
    @Schema(description = "유저로부터 입력받은 instagram username. 해당 내용을 포함하지 않으면 instaId 및 phoneId 존재 여부만 체크한다.", example = "leomessi")
    private String instaUsername;
    private String instaId;
    private String phoneId;
}