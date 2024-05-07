package ru.gb_spring.homeworkspring_05.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

@Aspect
@Component
public class UserActionTrackingAspect {
    private final Map<String, String> mapAction = Map.of(
            "getAllTask", "Запрос всех записей",
            "saveTask",  "Сохранение звписей",
            "addTask",  "Добавление записей",
            "delTask",  "удаления записей по ID",
            "getTaskByStatus",  "Получение списка задачь по статусу"
    );

    @Around("@annotation(TrackUserAction)")
    public Object TrackUserAction(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        String methodName = joinPoint.getSignature().getName();
        String arguments = Arrays.toString(args);

        System.out.println("Starting method execution at " + LocalDateTime.now() + " in " + methodName + " with arguments " + arguments);
        long runtime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        runtime = System.currentTimeMillis() - runtime;

        System.out.println("Method " + methodName + " executed in " + runtime + "ms");
        System.out.println("Ending method execution at " + LocalDateTime.now() );

        return result;
    }

    public static void println(String message) {
        System.out.println( message );
    }

    public static void print(String message) {
        System.out.print( message);
    }

    public static void printf(String message, Object object) {
        System.out.printf( message, object);
    }

}
