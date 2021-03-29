package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {
  // 메서드 명을 보는 순간 역할이 드러난다.
  // memberService에서는 memberServiceImpl을 쓸 것이다.
  public MemberService memberService() {
    return new MemberServiceImpl(memberRepository());
  }

  // memberRepository는 memory로 쓸 것이다.
  // 그래서 나중에 다른 repository를 쓰려면 이 코드만 바꾸면 된다.
  private MemberRepository memberRepository() {
    return new MemoryMemberRepository();
  }

  public OrderService orderService() {
    return new OrderServiceImpl(memberRepository(), discountPolicy());
  }

  private DiscountPolicy discountPolicy() {
//    return new FixDiscountPolicy();
    return new RateDiscountPolicy();
  }

}
