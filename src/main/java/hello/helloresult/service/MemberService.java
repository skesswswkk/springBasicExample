package hello.helloresult.service;

import hello.helloresult.domain.Member;
import hello.helloresult.repository.MemberRepository;
import hello.helloresult.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

//repository 클래스(save, findById 등 저장소에 넣었다 뺐다)와 달리,
//service 클래스는 비즈니스 용어에 가까운 네이밍 필요
//cmd + shift + T : test

//@Service -> Bean 으로 대체 //Spring이 올라올 때, @Service 보고 [Spring Container]에 MemberService를 등록해준다.
@Transactional //data를 저장하거나 변경할 때 필요함
public class MemberService {

    private final MemberRepository memberRepository;

    //Command + N -> Constructor
    //MemberService의 memberRepository를 외부에서 넣어주도록 생성자 생성
    //MemberService 입장에서는 memberRepository를 외부에서 넣어주므로 DI(의존성 주입)
    //@Autowired -> Bean 으로 대체
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member){

        long start = System.currentTimeMillis();

        //중복 회원 이름 가입 블가
        //함수 만드는 단축키 : control + T -> Extract Method 검색;
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m ->{//값이 있으면 아래 내용 throw
                throw new IllegalStateException("이미 존재하는 회원 이름입니다.");
            });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
