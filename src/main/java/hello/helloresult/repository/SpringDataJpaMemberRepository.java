package hello.helloresult.repository;

import hello.helloresult.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/*  SpringDataJpaMemberRepository가 interface이지만
    JpaRepository 를 extends하고 있으면 SpringDataJpa가 구현체를 자동으로 생성
 */
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);
}
