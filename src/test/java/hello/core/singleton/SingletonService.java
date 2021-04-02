package hello.core.singleton;

public class SingletonService {

  // 자기 자신을 private static으로 만들어 하나만 올라가도록 한다.
  // jvm이 뜨는 시점에 초기화하면서 new 한 번으로 생성해서 가지고 있는다.
  private static final SingletonService instance = new SingletonService();

  // public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서면 조회하도록 허용한다.
  public static SingletonService getInstance() {
    return instance;
  }

  // 외부 클래스에서 new SingletonService()로 임의로 만드는 것을 막기 위해 private 생성자를 만든다.
  private SingletonService() {
  }

  public void logic() {
    System.out.println("싱글턴 객체 로직 호출");
  }

}
