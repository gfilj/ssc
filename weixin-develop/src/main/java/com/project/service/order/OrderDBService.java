package com.project.service.order;

import com.project.common.exception.BusinessException;
import com.project.common.exception.ExceptionEnum;
import com.project.common.util.LogUtil;
import com.project.mapper.OrderMapper;
import com.project.model.order.OrderDetail;
import com.project.webmagic.model.OrderDetailDB;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by goforit on 2018/5/9.
 */
@Service
public class OrderDBService {
    @Resource
    private OrderMapper orderMapper;

    private Log logger = LogUtil.getLogger(getClass());


    public int insert(OrderDetail orderDetail) throws BusinessException {
        String funcname = "插入订单数据";
        try {
            return orderMapper.insert(orderDetail);
        } catch (Throwable e) {
            logger.error(LogUtil.logstr(funcname,"报错",orderDetail), e);
            throw new BusinessException(ExceptionEnum.DATA_CAUSE, orderDetail);
        }
    }

    public List<OrderDetailDB> selectList() throws BusinessException {
        String funcname = "选择订单列表";
        try {
            return orderMapper.selectList();
        } catch (Throwable e) {
            logger.error(LogUtil.logstr(funcname,"报错",""), e);
            throw new BusinessException(ExceptionEnum.DATA_CAUSE, "");
        }
    }

    public List<OrderDetailDB> search(Map<String,Object> map) throws BusinessException {
        String funcname = "搜索订单列表";
        try {
            return orderMapper.search(map);
        } catch (Throwable e) {
            logger.error(LogUtil.logstr(funcname,"报错",""), e);
            throw new BusinessException(ExceptionEnum.DATA_CAUSE, "");
        }
    }

    public int update(OrderDetailDB orderDetailDB) throws BusinessException {
        String funcname = "更新订单数据";
        try {
            return orderMapper.update(orderDetailDB);
        } catch (Throwable e) {
            logger.error(LogUtil.logstr(funcname,"报错",orderDetailDB), e);
            throw new BusinessException(ExceptionEnum.DATA_CAUSE, orderDetailDB);
        }
    }

}
