package com.shardingsearchdemo.db.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shardingsearchdemo.db.entity.PmsProduct;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 商品信息 Mapper 接口
 * </p>
 *
 * @author partick_peng
 * @since 2023-01-10
 */
@Mapper
public interface PmsProductMapper extends BaseMapper<PmsProduct> {

}
