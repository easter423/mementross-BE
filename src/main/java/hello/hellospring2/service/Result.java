package hello.hellospring2.service;

import hello.hellospring2.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result{
//    private Long id;
    private String guid;
    private String instaUsername;
    private String instaId;
    private String phoneId;
    private int status;

    public Result(Member member, int status){
//        this.id = member.getId();
        this.guid = member.getGuid();
        this.instaUsername = member.getInstaUsername();
        this.instaId = member.getInstaId();
        this.phoneId = member.getPhoneId();
        this.status = status;
    }
}
