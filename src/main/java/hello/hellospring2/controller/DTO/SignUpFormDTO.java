package hello.hellospring2.controller.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "Signup : 소셜 회원가입/로그인 요청 DTO")
public class SignUpFormDTO {
    @Schema(description = "유저로부터 입력받은 instagram username. 해당 내용을 포함하지 않으면 instaId 및 phoneId 존재 여부만 체크한다.", example = "leomessi", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    //@Parameter(name = "instaUsername", description = "Instagram User Name which the user provided. If there is none, it will only check user existence", example = "leomessi", required = false)
    private String instaUsername;
    @Schema(description = "Instagram Id which Instagram Oauth provided.", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    //@Parameter(name = "instaId", description = "Instagram Id which Instagram Oauth provided.", example = "1", required = true)
    private String instaId;
    @Schema(description = "Phone unique Id.", example = "u0_a225", requiredMode = Schema.RequiredMode.REQUIRED)
    //@Parameter(name = "phoneId", description = "Phone unique Id.", example = "u0_a225", required = true)
    private String phoneId;
}