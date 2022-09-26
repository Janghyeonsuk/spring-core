package hello.core;

import hello.core.member.*;
import hello.core.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService(); //-> memberServiceImpl 생성(MemoryMemberRepository 사용하는)
//        OrderService orderService = appConfig.orderService();// -> orderServiceImpl 생성(MemoryMemberRepository, RateDiscountPolicy 사용하는)

        //ApplicationContext-> 스프링 컨테이너 , 인터페이스(다형성)
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class); //(method name, return type)

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(memberId);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}

