package hello.hellospring2.service;

import hello.hellospring2.controller.DTO.SignUpFormDTO;
import org.springframework.http.ResponseEntity;

public interface MemberService {
    ResponseEntity signup(SignUpFormDTO formDTO);
}
