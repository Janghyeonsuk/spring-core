package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor //final이 붙은 필드를 모아서 생성자를 자동으로 생성
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

/*
    // 필드 주입 -> 코드가 간결 but 자바로 테스트할 방법이 없음
   @Autowired private final MemberRepository memberRepository;
   @Autowired private final DiscountPolicy discountPolicy;

   public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }


    private  MemberRepository memberRepository = new MemoryMemberRepository();
    private  DiscountPolicy discountPolicy = new RateDiscountPolicy();


    // 수정자 주입 -> 선택적, 변경 가능성이 있는 의존관계에서 사용
    // private  MemberRepository memberRepository; private  DiscountPolicy discountPolicy;로 final을 빼야함
    @Autowired(required = false) // 선택적
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

 */

    // 생성자 주입 -> 불변적, 필수값에 사용 , final을 유일하게 사용 가능
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); // 회원 정보 조회
        int discountPrice = discountPolicy.discount(member, itemPrice); // 할인 정책에 회원을 전달

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
    
    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
