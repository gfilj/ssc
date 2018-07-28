package com.project.service.order.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.common.exception.BusinessException;
import com.project.common.util.LogUtil;
import com.project.common.util.SwitchUtil;
import com.project.model.sql.User;
import com.project.model.vo.OrderSearchVO;
import com.project.model.vo.Page;
import com.project.service.order.OrderDBService;
import com.project.service.order.OrderProperty;
import com.project.service.order.OrderService;
import com.project.webdriver.PhantomjsWebDriver;
import com.project.webdriver.login.WebdriverProperty;
import com.project.webmagic.PhantomjsDriverDownloader;
import com.project.model.order.Order;
import com.project.model.order.OrderDetail;
import com.project.webmagic.model.OrderDetailDB;
import com.project.webmagic.pipeline.OrderDetailPipeline;
import com.project.webmagic.pipeline.OrderPipeline;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.project.common.util.LogUtil.logstr;

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

    @Autowired
    private OrderDBService orderDBService;

    private Site site = Site.me().setTimeOut(3000);

    @Override
    public void read() throws BusinessException {
        for (String request : getPageRequestList()) {
            logger.info("导入分页数据,请求链接为：" + request);
            phantomjsWebDriver.navigateto(request);
            OOSpider.create(site, orderPipeline, Order.class)
                    .setIsExtractLinks(false)
                    .setDownloader(phantomjsDriverDownloader)
                    .addUrl(webdriverProperty.getStartUrl())
                    .run();
        }
        readDetail();
    }

    @Override
    public void readDetail() throws BusinessException {
        for (Order order : orderPipeline.getOrderMap().values()) {
            if (order.getNo() == null) {
                continue;
            }
            logger.info("导入详细数据,请求链接为：" + order.getDetailLink());
            phantomjsWebDriver.navigateto(order.getDetailLink());
            phantomjsWebDriver.click(orderProperty.getDetailTel());
            OOSpider.create(site, orderDetailPipeline, OrderDetail.class)
                    .setIsExtractLinks(false)
                    .setDownloader(phantomjsDriverDownloader)
                    .addUrl(order.getDetailLink())
                    .run();
        }
    }

    @Override
    public List<String> getPageRequestList() {
        List<String> pageRequestList = new LinkedList<String>();
        phantomjsWebDriver.navigateto(String.format(orderProperty.getOrderList(), 1));
        String value = phantomjsWebDriver.getAttribute(orderProperty.getTotalPage(), "value");
        int totalPage = 1;
        if (!StringUtils.isEmpty(value)) {
            totalPage = Integer.parseInt(value);
        }
        //TODO测试
        totalPage = 1;
        logger.info("获取分页总数：" + totalPage);
        for (int i = 1; i <= totalPage; i++) {
            pageRequestList.add(String.format(orderProperty.getOrderList(), i));
        }
        return pageRequestList;
    }

    @Override
    public PageInfo<OrderDetailDB> list(Page page) throws BusinessException {
        String funcname = "订单列表";
        PageHelper.offsetPage(page.getOffset(), page.getSize());
        logger.info(logstr(funcname, "偏移量", page.getOffset(), "页面大小", page.getSize()));
        List<OrderDetailDB> orderDetails = orderDBService.selectList();
        int i = 1;
        for (OrderDetailDB orderDetailDB : orderDetails) {
            orderDetailDB.setKey("" + page.getOffset() + i);
            i++;
        }
        return new PageInfo<OrderDetailDB>(orderDetails);
    }

    @Override
    public PageInfo<OrderDetailDB> search(OrderSearchVO searchVO) throws BusinessException {
        String funcname = "订单模糊查询";
        Map<String, Object> map = SwitchUtil.objectToMap(searchVO);
        map.forEach((k, v) -> {
            map.put(k, "%" + v + "%");
        });

        logger.info(logstr(funcname, "map", map));
        List<OrderDetailDB> orders = orderDBService.search(map);
        int i = 1;
        for (OrderDetailDB orderDetailDB : orders) {
            orderDetailDB.setKey("" + i);
            i++;
        }
        return new PageInfo<OrderDetailDB>(orders);
    }
}
