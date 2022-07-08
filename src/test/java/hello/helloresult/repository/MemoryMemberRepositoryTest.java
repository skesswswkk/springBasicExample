package hello.helloresult.repository;

import hello.helloresult.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;


class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    //data clear2
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("김진희");
        repository.save(member);

        Member result = repository.findById(member.getId()).get();
//        System.out.println("result = " + (result == member));
//        Assertions.assertEquals(member, result); //Jupiter
        Assertions.assertThat(member).isEqualTo(result); //Junit
    }

    @Test
    public void findByName(){
        //회원1
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        //회원2
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        Assertions.assertThat(result).isEqualTo(member1); //Junit
    }

    @Test
    public void findAll(){
        //회원1
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        //회원2
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2); //Junit
    }
}
