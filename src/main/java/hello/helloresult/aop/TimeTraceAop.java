package hello.helloresult.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component //대신 @Bean에 등록해줌. 둘 중 아무거나 해줘도 상관없.
@Aspect
public class TimeTraceAop {

    @Around("execution(* hello.helloresult..*(..))")
//    @Around("execution(* hello.helloresult.service..*(..))") //service 하위 파일만 찍히도록
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());

        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString()+ " " + timeMs +
                    "ms");
        }
    }
}