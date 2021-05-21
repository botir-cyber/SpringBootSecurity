package uz.pdp.project.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;

@Component
public class ControllerInterceptor extends HandlerInterceptorAdapter {

    public ControllerInterceptor() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String test = request.getRequestURI();
        System.out.println(test);
//        if ("POST".equalsIgnoreCase(request.getMethod()))
//        {
//            test = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
//            ReqCart o = (ReqCart)CommonUtils.parseStringToObject(test, ReqCart.class);
//        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        String test = request.getRequestURI();
        if (test.equals("/api/userOrder")){
            response.getHeaderNames().stream().map(request::getHeaders).collect(Collectors.toList());
            request.getHeaderNames();
        }

        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String test = request.getRequestURI();
        if (test.equals("/api/userOrder")){
            request.getHeaderNames();
            response.getHeaderNames().stream().map(request::getHeaders).collect(Collectors.toList());
//            response.setHeader("TOKEN",request);
        }
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String test = request.getRequestURI();
        if (test.equals("/api/userOrder")){
            response.getHeaderNames().stream().map(request::getHeaders).collect(Collectors.toList());
        }
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
