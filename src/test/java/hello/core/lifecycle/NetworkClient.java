package hello.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {

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

  @PostConstruct
  public void init() throws Exception {
    connect();
    call("초기화 연결 메시지");
  }

  @PreDestroy
  public void close() throws Exception {
    disconnect();
  }
}
