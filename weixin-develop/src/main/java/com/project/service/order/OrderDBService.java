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

import java.util.List;

/**
 * Created by goforit on 2018/5/9.
 */
@Service
public class OrderDBService {
    @Autowired
    private OrderMapper orderMapper;

    public int insert(OrderDetail orderDetail) throws BusinessException {
        String funcname = "插入用户数据";
        Log logger = LogUtil.getLogger(getClass());
        try {
            return orderMapper.insert(orderDetail);
        } catch (Throwable e) {
            logger.error(LogUtil.logstr(funcname,"报错",orderDetail), e);
            throw new BusinessException(ExceptionEnum.DATA_CAUSE, orderDetail);
        }
    }

    public List<OrderDetailDB> selectList() throws BusinessException {
        String funcname = "选择用户列表";
        Log logger = LogUtil.getLogger(getClass());
        try {
            return orderMapper.selectList();
        } catch (Throwable e) {
            logger.error(LogUtil.logstr(funcname,"报错",""), e);
            throw new BusinessException(ExceptionEnum.DATA_CAUSE, "");
        }
    }

}
