package com.shardingsearchdemo.db.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.shardingsearchdemo.db.DbApplication;
import com.shardingsearchdemo.db.entity.OmsOrder;
import com.shardingsearchdemo.db.service.IOmOrderService;
import com.shardingsearchdemo.db.utils.IdUtils;
import com.shardingsearchdemo.db.utils.MockUtil;
import com.shardingsearchdemo.db.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author patrick_peng
 * @description
 * @date 2023-01-10 21:38
 **/
@SpringBootTest(classes = DbApplication.class)
@Slf4j
class IOmOrderServiceImplTest {

    @Test
    void batchInsert() throws InstantiationException, IllegalAccessException, InterruptedException {
        IOmOrderService omOrderService = SpringUtils.getBean(IOmOrderService.class);
        List<OmsOrder> list = MockUtil.getCreatObjectList(OmsOrder.class, 100000);
        for (OmsOrder omsOrder : list) {
            omsOrder.setUserId(IdUtils.fastSimpleUUID());
            omsOrder.setOrderId(IdUtils.fastSimpleUUID());
            omsOrder.setId(null);
        }
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 30L, TimeUnit.MINUTES,new LinkedBlockingQueue<Runnable>());
        List<List<OmsOrder>> partition = Lists.partition(list, 10000);
        CountDownLatch countDownLatch = new CountDownLatch(partition.size());
        for (List<OmsOrder> omsOrders : partition) {
            threadPoolExecutor.execute(()->{
                try {
                    omOrderService.batchInsert(omsOrders);
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
    }

    @Test
    void searchCount(){
        IOmOrderService omOrderService = SpringUtils.getBean(IOmOrderService.class);
        Long count = omOrderService.searchCount();
        log.info(count.toString());
    }

    @Test
    void searchOne() {
        IOmOrderService omOrderService = SpringUtils.getBean(IOmOrderService.class);
        QueryWrapper<OmsOrder> wrapper = new QueryWrapper<>();
        //wrapper.eq("user_id", "e859ea5cc83d4b18beb64498c925952a");
        wrapper.eq("order_id", "1b0f0de5d0a341a1abdc5c4604cd7257");
        OmsOrder omsOrder = omOrderService.searchOne(wrapper);
        log.info(omsOrder.toString());
    }

    @Test
    void searchTop(){
        IOmOrderService omOrderService = SpringUtils.getBean(IOmOrderService.class);
        List<OmsOrder> omsOrders = omOrderService.searchTop(new QueryWrapper<>());
        log.info(omsOrders.toString());
    }
}