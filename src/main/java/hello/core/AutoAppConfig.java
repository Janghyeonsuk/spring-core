package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration // 설정 정보
@ComponentScan( // @Component 어노테이션이 붙은 클래스를 스캔해서 자동으로 스프링 빈으로 등록한다.
        basePackages = "hello.core",
//        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) //컴포넌트 스캔에서 제외할것
)
public class AutoAppConfig {

/*
    //자동 bean 주입과 수동 bean 주입 충돌
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
*/
}
