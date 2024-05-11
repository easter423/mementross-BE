package hello.hellospring2.service;

import hello.hellospring2.controller.DTO.GuestSignFormDTO;
import hello.hellospring2.controller.DTO.SignUpFormDTO;
import org.springframework.http.ResponseEntity;

public interface MemberService {
    ResponseEntity signup(SignUpFormDTO formDTO);
    ResponseEntity guestsignup(GuestSignFormDTO formDTO);
    ResponseEntity guestsignout(GuestSignFormDTO formDTO);
}
