package hello.hellospring2.domain;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Diary {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "diary_id", nullable = false)
    private Long id;

    @Column(name = "diary_title", nullable = true)
    private String title;

    @Column(name = "diary_content", nullable = true)
    private String content;

    @Column(name = "diary_created", nullable = false)
    private LocalDateTime created;

    @Column(name = "diary_updated", nullable = false)
    private LocalDateTime updated;

    @Column(name = "diary_imageUrl", nullable = true)
    private String imageUrl;

    @Column(name = "createdImage", nullable = true)
    private String createdImage;

    @Column(name = "diary_image2txt", nullable = true)
    private String image2txt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_guid", nullable = false)
    private Member member;

}