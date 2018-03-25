package com.project.service.order.impl;

import com.project.common.exception.BusinessException;
import com.project.common.util.LogUtil;
import com.project.service.order.OrderProperty;
import com.project.service.order.OrderService;
import com.project.webdriver.PhantomjsWebDriver;
import com.project.webdriver.login.WebdriverProperty;
import com.project.webmagic.PhantomjsDriverDownloader;
import com.project.webmagic.model.Order;
import com.project.webmagic.model.OrderDetail;
import com.project.webmagic.pipeline.OrderDetailPipeline;
import com.project.webmagic.pipeline.OrderPipeline;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;

import javax.swing.plaf.nimbus.NimbusStyle;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by goforit on 2017/12/24.
 */
@Service
public class OrderServiceImpl implements OrderService {

    Log logger = LogUtil.getLogger(getClass());

    @Autowired
    private PhantomjsDriverDownloader phantomjsDriverDownloader;

    @Autowired
    private OrderPipeline orderPipeline;

    @Autowired
    private OrderProperty orderProperty;

    @Autowired
    private OrderDetailPipeline orderDetailPipeline;

    @Autowired
    private WebdriverProperty webdriverProperty;

    @Autowired
    private PhantomjsWebDriver phantomjsWebDriver;

    private Site site = Site.me().setTimeOut(3000);

    @Override
    public void read() throws BusinessException {
        for(String request:getPageRequestList()){
            logger.info("导入分页数据,请求链接为：" + request);
            phantomjsWebDriver.navigateto(request);
            OOSpider.create(site, orderPipeline, Order.class)
                    .setDownloader(phantomjsDriverDownloader)
                    .addUrl(webdriverProperty.getStartUrl())
                    .run();
        }
    }

    @Override
    public void readDetail() throws BusinessException {
        for(Order order :orderPipeline.getOrderMap().values()){
            if(order.getNo()==null){
                continue;
            }
            logger.info("导入详细数据,请求链接为：" + order.getDetailLink());
            phantomjsWebDriver.navigateto(order.getDetailLink());
            phantomjsWebDriver.takeScreenshot("orderDetail.png");
            phantomjsWebDriver.click(orderProperty.getDetailTel());
            OOSpider.create(site, orderDetailPipeline, OrderDetail.class)
                    .setDownloader(phantomjsDriverDownloader)
                    .addUrl(webdriverProperty.getStartUrl())
                    .run();
        }
    }

    @Override
    public List<String> getPageRequestList() {
        List<String> pageRequestList = new LinkedList<String>();
        phantomjsWebDriver.navigateto(String.format(orderProperty.getOrderList(),1));
        String value = phantomjsWebDriver.getAttribute(orderProperty.getTotalPage(), "value");
        int totalPage = 1;
        if(!StringUtils.isEmpty(value)){
            totalPage = Integer.parseInt(value);
        }
        logger.info("获取分页总数：" + totalPage);
        for(int i=1; i<= totalPage; i++){
            pageRequestList.add(String.format(orderProperty.getOrderList(),i));
        }
        return pageRequestList;
    }
}
