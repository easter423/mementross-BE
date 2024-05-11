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
@Schema(title = "Signup : 게스트 회원가입/로그인 요청 DTO")
public class GuestSignFormDTO {
    private String phoneId;
}