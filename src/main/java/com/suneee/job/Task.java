package com.suneee.job;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Task  {
    Logger log = LoggerFactory.getLogger(Task.class);


    @XxlJob("demoJobHandler")
    public ReturnT<String> demoJobHandler(String s) throws Exception {
        Config config = ConfigService.getConfig("eportal");
        String s1 = config.getProperty("xiuwen", "haowuliao");
        log.info("s1:"+s1);


        System.out.printf("");
        return ReturnT.SUCCESS;
    }
}
