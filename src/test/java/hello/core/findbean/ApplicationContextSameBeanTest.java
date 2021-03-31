package hello.core.findbean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ApplicationContextSameBeanTest {

  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
      SameBeanConfig.class);

  @Test
  @DisplayName("타입으로 조회할 때 둘 이상이 있으면 중복 오류가 발생한다.")
  void findBeanByTypeDuplicate() {
    assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(MemberRepository.class));
  }

  @Test
  @DisplayName("타입으로 조회할 때 같은 타입이 둘 이상이면 빈 이름을 지정해서 불러온다.")
  void findBeanByName() {
    MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
    assertThat(memberRepository).isInstanceOf(MemberRepository.class);
  }

  @Test
  @DisplayName("특정 타입을 모두 조회한다.")
  void findAllBeanByType() {
    Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);

    for (String key : beansOfType.keySet()) {
      System.out.println("key = " + key + " value = " + beansOfType.get(key));
    }

    System.out.println("beansOfType = " + beansOfType);
    assertThat(beansOfType.size()).isEqualTo(2);
  }

  // 테스트용 설정 클래스
  @Configuration
  // static: 클래스 안에 클래스를 만들어 여기서만 쓸 때 사용한다.
  static class SameBeanConfig {

    @Bean
    public MemberRepository memberRepository1() {
      // MemoryMemberRepository("10")처럼 다른 파라미터를 넘겨 빈이 생성될 수 있으므로
      // 이렇게 여러 개로 정의하는 건 틀린 게 아니다.
      return new MemoryMemberRepository();
    }

    @Bean
    public MemberRepository memberRepository2() {
      return new MemoryMemberRepository();
    }
  }
}
