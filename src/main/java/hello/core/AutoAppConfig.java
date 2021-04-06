package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// @Component가 붙은 객체를 찾아서 스프링 빈으로 등록해준다.
@ComponentScan(
    // AppConfig.java와의 충돌을 피하기 위해
    // Configuration 타입의 애너테이션이 달린 것은 제외한다.
    // @Configuration에 들어가보면 @Component가 달려있어 스캔 대상이 되기 때문이다.
    excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class),
    // 스캔할 기준이 되는 위치를 설정할 수 있다.
    basePackages = "hello.core"
)
public class AutoAppConfig {
  @Bean(name = "memoryMemberRepository")
  public MemberRepository memberRepository() {
    return new MemoryMemberRepository();
  }
}
