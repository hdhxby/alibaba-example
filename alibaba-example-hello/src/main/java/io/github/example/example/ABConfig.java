package io.github.example.example;

import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Executor;

//@Component
public class ABConfig {//implements SchedulingConfigurer {
//
//    private Executor executor;
//
//
//    /**
//     * 每隔半分钟执行一次
//     */
//    private static final String CRON = "*/3 * * * * ?";
//
//    public ABConfig(Executor executor) {
//        this.executor = executor;
//    }
//
//    private volatile int i =0;
//    @Override
//    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
//        scheduledTaskRegistrar.setScheduler(executor);
//        scheduledTaskRegistrar.addTriggerTask(
//                () -> {
//                    if(i++ % 10 ==0) {
//                        System.out.println(i);
//                        throw new RuntimeException();
//                    }
//                    System.out.println(i);
//                },
//                triggerContext -> {
//                    CronTrigger trigger = new CronTrigger(CRON);
//                    return trigger.nextExecutionTime(triggerContext);
//                }
//        );
//    }
}
