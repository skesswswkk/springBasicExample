package hello.helloresult.controller;

import hello.helloresult.domain.Member;
import hello.helloresult.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//MemberController는 MemberService를 통해 회원가입, 조회 가능
//= MemberController가 MemberService를 의존한다
@Controller //Spring이 첨에 뜰 Spring Container 생성시, @Controller annotation이 있으면
            // MemberController 객체를 생성하여 Spring에 넣어어 Spring이 관리한다.
public class MemberController {

//    private final MemberService memberservice = new MemberService();
    private final MemberService memberservice;

    @Autowired //MemberController 생성될 때, Spring Bean에 등록되어 있는 MemberService 객체를 가져다가(의존성) 넣어준다.(주입) = DI
    public MemberController(MemberService memberservice) { //<- Bean을 통해 Container에 올라간 MemberService을 파라미터로 넣어준다.
        this.memberservice = memberservice;
    }

    //url창에 입력하는 것 = GetMapping(data 조회)
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    //createMemberForm.html 소스에
    //<form action="/members/new" method="post">
    //data를 form에 넣어 전달하는 방식 = PostMapping(data 등록)
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " + member.getName());

        memberservice.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberservice.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }
}