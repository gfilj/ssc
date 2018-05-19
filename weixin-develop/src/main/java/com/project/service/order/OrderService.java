package com.project.service.order;

import com.github.pagehelper.PageInfo;
import com.project.common.exception.BusinessException;
import com.project.model.vo.Page;
import com.project.webmagic.model.OrderDetailDB;

import java.util.List;

/**
 * Created by goforit on 2017/12/24.
 */
public interface OrderService {

    void read() throws BusinessException;

    void readDetail() throws BusinessException;

    List<String> getPageRequestList();

    PageInfo<OrderDetailDB> list(Page page)  throws BusinessException;

}
