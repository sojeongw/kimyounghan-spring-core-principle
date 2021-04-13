package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest{

  @Test
  public void lifeCycleTest() {
    ConfigurableApplicationContext ac =  new AnnotationConfigApplicationContext(LifeCycleConfig.class);
    NetworkClient client = ac.getBean(NetworkClient.class);

    // ApplicatoinContext 대신, 이를 상속하는
    // AnnotationConfigApplicationContext이나
    // ConfigurableApplicationContext를 사용하면 close()를 제공한다.
    ac.close();
  }

  @Configuration
  static class LifeCycleConfig {
    // 각 단계에서 호출할 메서드의 이름을 넣어준다.
    @Bean(initMethod = "init", destroyMethod = "close")
    public NetworkClient networkClient() {
      NetworkClient networkClient = new NetworkClient();
      networkClient.setUrl("http://hello-spring.dev");
      return networkClient;
    }
  }
}
