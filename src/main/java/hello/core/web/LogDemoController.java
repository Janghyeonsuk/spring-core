package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger; //CGLIB이라는 라비르러리로 실제 myLogger가 아닌 가짜 프록시 객체를 주입
    //private final ObjectProvider<MyLogger> myLoggerProvider;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestURL = request.getRequestURL().toString();

        System.out.println("myLogger = " + myLogger.getClass());
//        지연 호출을 통해서 Http requst 요청이 들어온 상태에서 myLogger 호출하므로 사용이 가능해짐
//        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.setRequestURL(requestURL); //myLogger의 기능을 사용할때 진짜 myLogger 값을 찾아서 동작

        myLogger.log("controller test");
        Thread.sleep(1000);
        logDemoService.logic("testId");
        return "OK";
    }
}
