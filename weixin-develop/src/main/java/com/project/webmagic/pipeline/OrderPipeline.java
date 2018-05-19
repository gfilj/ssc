package com.project.webmagic.pipeline;

import com.project.common.util.LogUtil;
import com.project.model.order.Order;
import org.apache.commons.logging.Log;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by goforit on 2017/12/24.
 */
@Service
public class OrderPipeline implements PageModelPipeline<Order> {

    Log logger = LogUtil.getLogger(getClass());

    Map<String,Order> orderMap = new HashMap<>();

    @Override
    public void process(Order order, Task task) {
        orderMap.put(order.getNo(),order);
        logger.info(order.toString());
    }

    public Map<String,Order> getOrderMap(){
        return orderMap;
    }
}
