package hello.helloresult.repository;

import hello.helloresult.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//아직 데이터 저장소가 선정되지 않음(가상의 시나리오)
//개발을 진행하기 위해 초기 개발 단계에서는 '구현체'로 가벼운 메모리 기반의 저장소 사용
//MemoryMemberRepository : 리포지토리 구현체
//@Repository -> Bean 으로 대체
/**
 * 정형화된 패턴
 * @Controller : 통해서 외부 요청을 받고,
 * @Service : 통해서 비즈니스 로직 만들고,
 * @Repository : 에서 데이터 저장하고,
 */
public class MemoryMemberRepository implements MemberRepository {

    //아래 static은 인스턴스와 상관없이 클래스 레벨에 붙는다 그래서
    //"= new MemoryMemberRepository"로 여러번 생성해줘도 DB에 하나 잡힘
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; //key값 생성을 위한

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // Optional.ofNullable()로 감싸야 크라이언트에서 후처리 가능
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() //store 전부 돌면서 .filter
                .filter(member -> member.getName().equals(name))
                .findAny(); //하나 찾아지면 바로 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    //data clear1
    public void clearStore(){
        store.clear();
    }
}
