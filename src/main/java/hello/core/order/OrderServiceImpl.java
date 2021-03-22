package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

  private final MemberRepository memberRepository = new MemoryMemberRepository();
  private DiscountPolicy discountPolicy;

  @Override
  public Order createOrder(Long memberId, String itemName, int itemPrice) {
    Member member = memberRepository.findById(memberId);
    // 난 모르겠고 할인 정책은 할인 정책에게 넘긴다.
    int discountPrice = discountPolicy.discount(member, itemPrice);

    return new Order(memberId, itemName, itemPrice, discountPrice);
  }
}
