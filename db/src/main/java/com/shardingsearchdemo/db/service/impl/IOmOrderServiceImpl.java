package com.shardingsearchdemo.db.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shardingsearchdemo.db.constant.BasConstant;
import com.shardingsearchdemo.db.entity.OmsOrder;
import com.shardingsearchdemo.db.mapper.OmsOrderMapper;
import com.shardingsearchdemo.db.service.IOmOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author patrick_peng
 * @description
 * @date 2023-01-10 21:19
 **/
@Service
public class IOmOrderServiceImpl extends ServiceImpl<OmsOrderMapper, OmsOrder> implements IOmOrderService {

    @Autowired
    private OmsOrderMapper omsOrderMapper;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void batchInsert(List<OmsOrder> omsOrderList) {
        saveBatch(omsOrderList, BasConstant.FIVE_HUNDRED);
    }

    @Override
    public Long searchCount() {
        return omsOrderMapper.selectCount(new QueryWrapper<>());
    }

    @Override
    public OmsOrder searchOne(QueryWrapper<OmsOrder> queryWrapper) {
        return omsOrderMapper.selectOne(queryWrapper);
    }
}
