package uz.pdp.project.aop.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import uz.pdp.project.aop.anatation.CheckUser;



import java.util.Optional;

@Aspect
@Component
public class CheckUserAspect {

    @Before(value = "@annotation(checkUser)")
    public void checkAuthAspectExecutor(CheckUser checkUser) {
        ///security contect holderdan userni olib console
        new Thread(() -> {
            System.out.println("");
        }).start();
        MyRunnable myRunnable = new MyRunnable();
        myRunnable.run();

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        System.out.println( Optional.ofNullable(servletRequestAttributes).map(servletRequestAttributes1 -> servletRequestAttributes1.getRequest().getRequestURI()).orElse(null));
    }
    public class MyRunnable implements Runnable {

        public void run(){
            System.out.println("MyRunnable running");
        }
    }

    @After(value = "@annotation(checkUser)")
    public void checkAuthAspectExecutorAfter(CheckUser checkUser) {

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        System.out.println( Optional.ofNullable(servletRequestAttributes).map(servletRequestAttributes1 -> servletRequestAttributes1.getRequest().getRequestURI()).orElse(null));
    }
//    private String currentRequest() {
//        // Use getRequestAttributes because of its return null if none bound
//        }

//    private String getAuthorizationToken() {
//        return currentRequest().getHeader("Authorization");
//    }

//    private void checkTokenIsValid(String token, String roles) {
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//        if (requestAttributes instanceof ServletRequestAttributes) {
//            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
//            request.getHeaders("user");
//            ResUser resUser = (ResUser) CommonUtils.parseStringToObject(request.getHeader("user"), ResUser.class);
//            if (resUser.getRoles().stream().noneMatch(role -> role.getName().toString().equals(roles))) {
//                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, null, null);
//            }
//        } else {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, null, null);
//        }
//    }
}