package hello.hellospring2.service;

import hello.hellospring2.controller.DTO.SignUpFormDTO;
import hello.hellospring2.domain.Member;
import hello.hellospring2.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public ResponseEntity<Result> signup(SignUpFormDTO formDTO) {

        Optional<Member> member = memberRepository.findByInstaId(formDTO.getInstaId());

        if (member.isEmpty()) {
            Member newMember = Member.builder()
                    .instaUsername(formDTO.getInstaUsername())
                    .instaId(formDTO.getInstaId())
                    .phoneId(formDTO.getPhoneId())
                    .guid(UUID.randomUUID().toString())
                    .build();

            memberRepository.save(newMember);
            return ResponseEntity.ok().body(new Result(newMember, 1));
        } else {
            return ResponseEntity.ok().body(new Result(member.get(), 0));
        }
    }
}

@Getter
@Setter
class Result{
    private Long id;
    private String guid;
    private String instaUsername;
    private String instaId;
    private String phoneId;
    private int status;

    public Result(Member member, int status){
        this.id = member.getId();
        this.guid = member.getGuid();
        this.instaUsername = member.getInstaUsername();
        this.instaId = member.getInstaId();
        this.phoneId = member.getPhoneId();
        this.status = status;
    }
}