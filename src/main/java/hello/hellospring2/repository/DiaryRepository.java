package hello.hellospring2.repository;

import hello.hellospring2.domain.Diary;
import hello.hellospring2.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findByMember(Member member);
}
