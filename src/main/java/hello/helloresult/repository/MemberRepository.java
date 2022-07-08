package hello.helloresult.repository;

import hello.helloresult.domain.Member;

import java.util.List;
import java.util.Optional;

//리포지토리 : 도메인 객체를 DB에 저장하고 관리(데이터베이스에 접근)
//아직 데이터 저장소가 선정되지 않아, 우선 인테페이스로 구현클래스 변경할 수 있도록 설계
public interface MemberRepository {
    Member save(Member member);

    //null일 수도 있는 경우, Optional<>로 감싼다
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
