package com.project.service.order;

import com.project.common.exception.BusinessException;

import java.util.List;

/**
 * Created by goforit on 2017/12/24.
 */
public interface OrderService {

    void read() throws BusinessException;

    void readDetail() throws BusinessException;

    List<String> getPageRequestList();
}
