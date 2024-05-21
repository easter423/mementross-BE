package hello.hellospring2.service;

import hello.hellospring2.controller.DTO.GuestSignFormDTO;
import hello.hellospring2.controller.DTO.SignUpFormDTO;
import hello.hellospring2.domain.Member;
import hello.hellospring2.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public ResponseEntity<Result> signup(SignUpFormDTO  formDTO) {

        Optional<Member> member = memberRepository.findByInstaId(formDTO.getInstaId());

        log.info("member: {}", member);
        if (member.isEmpty()) {
            Optional<Member> member2 = memberRepository.findByPhoneId(formDTO.getPhoneId());
            log.info("member2: {}", member2);
            if (member2.isPresent()) {
                if (formDTO.getInstaUsername()!=null) {
                    member2.get().setInstaUsername(formDTO.getInstaUsername());
                    member2.get().setInstaId(formDTO.getInstaId());
                    memberRepository.save(member2.get());
                    return ResponseEntity.ok().body(new Result(member2.get(), 1));
                }
                return ResponseEntity.ok().body(new Result(member2.get(), 2));
            }

            Member newMember = Member.builder()
                    .instaUsername(formDTO.getInstaUsername())
                    .instaId(formDTO.getInstaId())
                    .phoneId(formDTO.getPhoneId())
                    .guid(UUID.randomUUID().toString())
                    .invalid(false)
                    .build();

            log.info("newMember: {}", newMember);


            if (formDTO.getInstaUsername()!=null) {
                memberRepository.save(newMember);
            }
            return ResponseEntity.ok().body(new Result(newMember, 1));
        } else {
            return ResponseEntity.ok().body(new Result(member.get(), 0));
        }
    }

    public ResponseEntity<Result> guestsignup(GuestSignFormDTO formDTO) {

        Optional<Member> member = memberRepository.findByPhoneId(formDTO.getPhoneId());

        if (member.isEmpty()) {
            Member newMember = Member.builder()
                    .phoneId(formDTO.getPhoneId())
                    .guid(UUID.randomUUID().toString())
                    .invalid(false)
                    .build();

            memberRepository.save(newMember);
            return ResponseEntity.ok().body(new Result(newMember, 1));
        } else {
            return ResponseEntity.ok().body(new Result(member.get(), 0));
        }
    }

    public ResponseEntity<StatusResult> guestsignout(GuestSignFormDTO formDTO) {

        Optional<Member> member = memberRepository.findByPhoneId(formDTO.getPhoneId());

        if (member.isEmpty()) {
            return ResponseEntity.ok().body(new StatusResult(0));
        } else {
            member.get().setInvalid(true);
            memberRepository.save(member.get());
            return ResponseEntity.ok().body(new StatusResult(1));
        }
    }
}

