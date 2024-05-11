package hello.hellospring2.repository;

import hello.hellospring2.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    @Query(value = "SELECT * FROM member WHERE member_insta_id = :name AND invalid = false", nativeQuery = true)
    Optional<Member> findByInstaId(@Param("name")String name);
    @Query(value = "SELECT * FROM member WHERE member_phone_id = :phoneId AND invalid = false", nativeQuery = true)
    Optional<Member> findByPhoneId(@Param("phoneId")String phoneId);
}