package hello.helloresult.service;

import hello.helloresult.aop.TimeTraceAop;
import hello.helloresult.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    //Jdbc
//    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource){
//        this.dataSource = dataSource;
//    }

    //JPA
//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em){
//        this.em = em;
//    }

    //SpringDataJpa
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean //=내가 Bean을 등록할꺼야
    public MemberService memberService(){
        //파라미터 넣기 전에 빨간 줄에 Command + P : 파라미터 알 수 있음
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository(); //'메모리'
//        return new JdbcMemberRepository(dataSource); //'순수 Jdbc'
//        return new JdbcTemplateMemberRepository(dataSource); //'스프링 JdbcTemplate'
//        return new JpaMemberRepository(em); //JPA
//    }

//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }
}
