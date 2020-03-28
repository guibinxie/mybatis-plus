package com.suneee.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
//@EnableScheduling
public class JobTask {
    Logger logger = LoggerFactory.getLogger(JobTask.class);
    @Scheduled(cron = "0 0/2 * * * *")
    public void task(){
        try {
            logger.info(Thread.currentThread().getName()+"开始执行业务代码");
            Integer sum = 0;
            Thread.sleep(30000);

            for (;  ; ) {

            }
           // logger.info(Thread.currentThread().getName()+"执行任务结束:"+sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
