package com.project.dispatcher;

import com.project.BaseTest;
import com.project.common.extension.SpringUtil;
import com.project.processor.event.EventProcessor;
import com.project.processor.event.impl.SubscribeProcessor;
import org.junit.Test;

/**
 * Created by goforit on 2017/11/25.
 */
public class DipatcherTest extends BaseTest{

    @Test
    public void testDisPathcher(){
        String eventProcessorStr = "subscribProcessor";
        EventProcessor eventProcessor= (EventProcessor) SpringUtil.getBean(eventProcessorStr);
        System.out.println(eventProcessor instanceof SubscribeProcessor);
    }
}
