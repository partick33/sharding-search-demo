package com.shardingsearchdemo.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shardingsearchdemo.db.entity.OmsOrder;

import java.util.List;

/**
 * @author patrick_peng
 * @description 订单服务
 * @date 2023-01-10 21:19
 **/
public interface IOmOrderService {

    void batchInsert(List<OmsOrder> omsOrderList);

    Long searchCount();

    OmsOrder searchOne(QueryWrapper<OmsOrder> queryWrapper);

    List<OmsOrder> searchTop(QueryWrapper<OmsOrder> queryWrapper);
}
