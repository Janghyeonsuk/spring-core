package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

//싱글톤과 다르게 프로토 타입은 객체 생성을 호출마다 생성하여 다른 객체값을 받는다
public class PrototypeTest {
    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);

        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        //수동으로 닫아야함
        prototypeBean1.close();
        prototypeBean2.close();

        //닫히지 않음
        ac.close();
    }

    @Scope("prototype")
//    @Component AnnotationConfigApplicationContext에 클래스를 지정하게 되면 컴포넌트 스캔 대상처럼 동작해 스프링 빈으로 등록
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init" + this);
        }

        //프로토타입 빈은 스프링컨테이너가 생성과 의존관계 주입, 초기화까지만 관리하고 더이상 관리하지 않아 @PreDestroy 같은 종료 메소드가 실행되지 않음
        @PreDestroy
        public void close() {
            System.out.println("PrototypeBean.close");
        }
    }
}