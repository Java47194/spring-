package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
//테스트 케이스에 이 어노테이션이 있으면 테스트 시작 전에 트랜잭션을 시작하고,테스토 완료후에 항상 롤백 db에 데이터가 안남음
@Transactional

// test
class MemberServiceIntegrationTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository repository; //service와 Repository 객체 주소가 다르다


 /*   @BeforeEach //시작하기전
    public void beforeEach(){
         repository=new MemoryMemberRepository();
         memberService=new MemberService(repository);
    }*/


    /*@AfterEach (초기화 @Transactional 이거떄문에 필요가 없다)
    public void afterEach(){
        repository.StoreClear();
    }
*/

    @Test
    void 회원가입() {
        //given 데이트 기반
        Member member=new Member();
        member.setName("spring1");
        //when 검증
        Long saveId=memberService.join(member);
        //then 검증부
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복회원예외(){
        //given
        Member member1=new Member();
        member1.setName("spring");

        Member member2=new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");


       /* try {
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
        }*/

        //then
    }


}