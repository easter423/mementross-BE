package hello.hellospring2.repository;

import hello.hellospring2.domain.Diary;
import hello.hellospring2.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findByMember(Member member);

    List<Diary> findByMemberAndCreated(Member member, LocalDateTime time);

    List<Diary> findByMemberAndCreatedBetween(Member member, LocalDateTime minusDays, LocalDateTime plusDays);
}
