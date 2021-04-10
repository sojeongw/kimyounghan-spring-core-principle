package hello.core.autowired;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AllBeanTest {

  @Test
  void findAllBean() {
    // 두 클래스를 스프링 빈으로 등록한다.
    ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

    DiscountService discountService = ac.getBean(DiscountService.class);
    Member member = new Member(1L, "userA", Grade.VIP);
    int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

    assertThat(discountService).isInstanceOf(DiscountService.class);
    assertThat(discountPrice).isEqualTo(1000);

    int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
    assertThat(rateDiscountPrice).isEqualTo(2000);
  }

  static class DiscountService {

    private final Map<String, DiscountPolicy> policyMap;
    private final List<DiscountPolicy> policyList;

    // 빈을 등록할 때 맵과 리스트를 주입받는다.
    @Autowired
    public DiscountService(
        Map<String, DiscountPolicy> policyMap,
        List<DiscountPolicy> policyList) {
      this.policyMap = policyMap;
      this.policyList = policyList;

      System.out.println("policyMap = " + policyMap);
      System.out.println("policyList = " + policyList);
    }

    // 넘겨준 정책이 discountCode가 되어 해당 정책을 찾아 계산한 뒤 리턴한다.
    public int discount(Member member, int price, String discountCode) {
      DiscountPolicy discountPolicy = policyMap.get(discountCode);
      return discountPolicy.discount(member, price);
    }
  }

}
