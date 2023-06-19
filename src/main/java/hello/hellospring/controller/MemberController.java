package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        System.out.println("memberService = " + memberService.getClass()); // 프록시 확인 방법 memberService = class hello.hellospring.service.MemberService$$EnhancerBySpringCGLIB$$fd102793
    }
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

        //get 방식은 조회 post는 등록
    @PostMapping("/members/new")
    public String create(MemberForm form){
        System.out.println("form =" +form.getName());
        Member member=new Member();
        member.setName(form.getName());
        System.out.println("member1 =" +member);
        memberService.join(member);
        System.out.println("member2 =" +member);

        return "redirect:/";

    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> list = memberService.findMembers();
        model.addAttribute("list",list);
        return "members/memberList";
    }
}
