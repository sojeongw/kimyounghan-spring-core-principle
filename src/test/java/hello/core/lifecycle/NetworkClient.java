package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient implements InitializingBean, DisposableBean {

  private String url;

  public NetworkClient() {
    System.out.println("생성자 호출, url = " + url);
  }

  public void setUrl(String url) {
    this.url = url;
  }

  // 서비스를 시작할 때 호출하는 메서드
  public void connect() {
    System.out.println("connected url = " + url);
  }

  // 메시지를 보내는 메서드
  public void call(String message) {
    System.out.println("call = " + url + ", message = " + message);
  }

  // 서비스 종료 시 호출하는 메서드
  public void disconnect() {
    System.out.println("closed url = " + url);
  }

  // InitializingBean
  // 프로퍼티가 세팅이 끝나면 = 의존 관게 주입이 끝나면 호출한다.
  @Override
  public void afterPropertiesSet() throws Exception {
    connect();
    call("초기화 연결 메시지");
  }

  // DisposableBean
  // 빈이 종료될 때 호출된다.
  @Override
  public void destroy() throws Exception {
    disconnect();
  }
}
