package com.project.webmagic.pipeline;

import com.project.common.util.LogUtil;
import com.project.service.order.OrderDBService;
import com.project.model.order.OrderDetail;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
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

    Log logger = LogUtil.getLogger(getClass());

    Map<String,OrderDetail> orderDetailMap = new HashMap<>();
    @Autowired
    private OrderDBService orderDBService;
    @Override
    public void process(OrderDetail orderDetail, Task task) {
        orderDetailMap.put(orderDetail.getNo(),orderDetail);
        logger.info(orderDetail.toString());
        try {
            orderDBService.insert(orderDetail);
        } catch (Throwable e) {
            logger.info(LogUtil.logstr("订单插入数据", "报错", e.getMessage()));
        }
    }

    public Map<String,OrderDetail> getOrderDetailMap(){
        return orderDetailMap;
    }


}
