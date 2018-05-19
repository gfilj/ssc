package com.project.mapper;

import com.project.provider.OrderSqlProvider;
import com.project.model.order.OrderDetail;
import com.project.webmagic.model.OrderDetailDB;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Created by goforit on 2018/5/9.
 */
@Mapper
public interface OrderMapper {
    @InsertProvider(type = OrderSqlProvider.class, method = "insert")
    int insert(OrderDetail orderDetail) throws DataAccessException;

    @SelectProvider(type = OrderSqlProvider.class, method = "selectList")
    List<OrderDetailDB> selectList() throws DataAccessException;
}
