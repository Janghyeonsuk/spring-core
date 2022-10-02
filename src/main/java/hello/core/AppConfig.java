package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    // 생성자를 통해 Dependency Injection(의존관계 주입)
    // 리팩토링을 통해서 역할을 세우고 구현이 들어가도록 설계
    // 역할과 구현 클래스가 한눈에 확인, 중복 제거
    // OCP, DIP 만족


    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()   2번 호출이 되는데 싱글톤이 깨지는가?

    //@Configuration 사용x, @Bean 사용 -> memberRepository 총 3번 호출 -> 싱글톤 보장 x
    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.memberRepository
    //call AppConfig.orderService
    //call AppConfig.memberRepository

    //@Configuration 사용 -> CGLIB 통해서 스프링 컨테이너에 있으면 찾아서 반환 -> 싱글톤 보장
    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.orderService

//    @Autowired MemberRepository memberRepository; //스프링에서 의존관계 자동 주입

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
