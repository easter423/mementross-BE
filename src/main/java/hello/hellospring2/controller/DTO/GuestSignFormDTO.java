package hello.hellospring2.controller.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "GuestSign : 게스트 회원가입/로그인 요청 DTO")
public class GuestSignFormDTO {
    //@Parameter(name = "phoneId", description = "Phone unique Id.", example = "u0_a225", required = true)
    @Schema(description = "Phone unique Id.", example = "u0_a225", requiredMode = Schema.RequiredMode.REQUIRED)
    private String phoneId;
}