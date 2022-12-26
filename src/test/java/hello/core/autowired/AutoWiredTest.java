package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutoWiredTest {

    @Test
    void AutoWiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class); //스프링 빈으로 등록 됨

    }

    static class TestBean {

        @Autowired(required = false) //의존관계 x -> 수정자 메소드가 호출이 되지 않음
        public void SetNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void SetNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired
        public void SetNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }


    }

}
