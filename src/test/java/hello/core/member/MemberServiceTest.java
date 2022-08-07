package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    MemberService memberService;
    // 실행을 하기전에 appConfig 생성 -> memberService 할당, Test가 2개면 2번 실행된다.
    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

//    MemberService memberService = new MemberServiceImpl();

    @Test
    @DisplayName("회원가입")
    void join() {
        //given
        Member member = new Member(1l, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1l);

        ///then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
