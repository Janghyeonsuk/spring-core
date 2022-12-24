package hello.core;

import hello.core.member.MemberService;
import hello.core.order.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CoreApplicationTests {

	@Autowired MemberService memberService;
	@Autowired OrderService orderService;
	@Test
	void contextLoads() {
	}

}
