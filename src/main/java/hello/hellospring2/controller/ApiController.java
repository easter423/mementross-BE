package hello.hellospring2.controller;

import hello.hellospring2.controller.DTO.GuestSignFormDTO;
import hello.hellospring2.controller.DTO.SignUpFormDTO;
import hello.hellospring2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity userSignup(@RequestBody SignUpFormDTO formDTO) {
        return memberService.signup(formDTO);
    }

    @PostMapping("/guestsignup")
    public ResponseEntity GuestuserSignup(@RequestBody GuestSignFormDTO formDTO) {
        return memberService.guestsignup(formDTO);
    }
    @PostMapping("/guestsignout")
    public ResponseEntity GuestuserSignout(@RequestBody GuestSignFormDTO formDTO) {
        return memberService.guestsignout(formDTO);
    }

}
