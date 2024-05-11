package hello.hellospring2.domain;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.*;

@Getter
@Entity
@Builder
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable=false)
    private Long id;

    @Column(name = "member_guid", nullable = false)
    private String guid;

    @Setter
    @Column(name = "member_insta_username", nullable = true)
    private String instaUsername;

    @Setter
    @Column(name = "member_insta_id", nullable = true)
    private String instaId;

    @Column(name = "member_insta_posting", nullable = true)
    private String instaPosting;

    @Column(name = "member_phone_id", nullable = true)
    private String phoneId;

    @Column(name = "invalid", nullable = false)
    private Boolean invalid;

    public void setInvalid(boolean b) {
        this.invalid=b;
    }

}