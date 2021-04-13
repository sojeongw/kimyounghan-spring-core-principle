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
    // 빈 등록은 해줘야 돌아간다.
    @Bean
    public NetworkClient networkClient() {
      NetworkClient networkClient = new NetworkClient();
      networkClient.setUrl("http://hello-spring.dev");
      return networkClient;
    }
  }
}
