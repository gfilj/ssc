package com.project.mapper;

import com.project.provider.OrderSqlProvider;
import com.project.model.order.OrderDetail;
import com.project.webmagic.model.OrderDetailDB;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

/**
 * Created by goforit on 2018/5/9.
 */
@Mapper
public interface OrderMapper {
    @InsertProvider(type = OrderSqlProvider.class, method = "insert")
    int insert(OrderDetail orderDetail) throws DataAccessException;

    @SelectProvider(type = OrderSqlProvider.class, method = "selectList")
    List<OrderDetailDB> selectList() throws DataAccessException;


    @SelectProvider(type = OrderSqlProvider.class, method = "search")
    List<OrderDetailDB> search(Map<String,Object> map) throws DataAccessException;

    @UpdateProvider(type = OrderSqlProvider.class, method = "update")
    int update(OrderDetailDB orderDetailDB) throws DataAccessException;
}
