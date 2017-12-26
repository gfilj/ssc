package com.project.webmagic.pipeline;

import com.project.common.util.LogUtil;
import com.project.webmagic.model.Order;
import com.project.webmagic.model.OrderDetail;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by goforit on 2017/12/24.
 */
@Service
public class OrderDetailPipeline implements PageModelPipeline<OrderDetail> {

    Logger logger = LogUtil.getLogger(getClass());

    Map<String,OrderDetail> orderDetailMap = new HashMap<>();

    @Override
    public void process(OrderDetail orderDetail, Task task) {
        orderDetailMap.put(orderDetail.getNo(),orderDetail);
        logger.info(orderDetail.toString());

    }

    public Map<String,OrderDetail> getOrderDetailMap(){
        return orderDetailMap;
    }


}
