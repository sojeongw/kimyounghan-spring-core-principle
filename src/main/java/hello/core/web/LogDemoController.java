package hello.core.web;

import hello.core.common.MyLogger;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
// 생성자에 자동으로 autowired로 자동 주입된다.
@RequiredArgsConstructor
public class LogDemoController {

  private final LogDemoService logDemoService;
  // myLogger를 찾을 수 있는, DL할 수 있는 객체가 주입되어 주입 시점에 myLogger을 주입받을 수 있다.
  private final ObjectProvider<MyLogger> myLoggerProvider;

  @RequestMapping("log-demo")
  @ResponseBody
  public String logDemo(HttpServletRequest request) {
    // DL로 원하는 객체를 가져온다.
    MyLogger myLogger = myLoggerProvider.getObject();

    String requestURL = request.getRequestURL().toString();
    myLogger.setRequestURL(requestURL);

    myLogger.log("controller test");
    logDemoService.logic("testId");

    return "OK";
  }
}
