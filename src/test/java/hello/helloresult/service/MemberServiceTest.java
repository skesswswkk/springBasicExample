package hello.helloresult.service;

import hello.helloresult.domain.Member;
import hello.helloresult.repository.MemberRepository;
import hello.helloresult.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    //ctrl + R : 이전 build 그대로 실행
//    MemberService memberservice = new MemberService();
//    MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();

    MemberService memberservice;
    MemoryMemberRepository memoryMemberRepository;

    @BeforeEach
    public void beforeEach(){
        memoryMemberRepository = new MemoryMemberRepository();
        memberservice = new MemberService(memoryMemberRepository);
    }

    @AfterEach //돌때마다 DB의 값을 날려준다
    public void afterEach(){
        memoryMemberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given : 상황이 주어지고 / 이 데이타 기반이구나
        Member member = new Member();
        member.setName("김진희");

        //when : 이걸 실행했을 때 / 이걸 검증하는구나
        Long saveId = memberservice.join(member);

        //then : 결과가 이게 나와야해
        Member findmember = memberservice.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findmember.getName());
    }

    //test는 정상flow도 중요한데, 예외flow가 더 중요하다
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("서정준1");

        Member member2 = new Member();
        member2.setName("서정준1");

        //when
//        Long saveId = memberservice.join(member);
        memberservice.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberservice.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 이름입니다.");

/*      try{
            memberservice.join(member2);
            fail();
        } catch(IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 이름입니다."); //여기 탐
        }
*/

        //then
    }

    @Test
    void findMembers() {

    }

    @Test
    void findOne() {
    }
}