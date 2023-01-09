package com.demo.shardingsearchdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author partick_peng
 * @since 2023-01-09
 */
@Getter
@Setter
@TableName("cart_item")
public class CartItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品的id
     */
    @TableField("product_id")
    private Long productId;

    /**
     * 商品sku的id
     */
    @TableField("product_sku_id")
    private Long productSkuId;

    /**
     * 会员id
     */
    @TableField("member_id")
    private Long memberId;

    /**
     * 购买数量
     */
    @TableField("quantity")
    private Integer quantity;

    /**
     * 添加到购物车的价格
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * 销售属性1
     */
    @TableField("sp1")
    private String sp1;

    /**
     * 销售属性2
     */
    @TableField("sp2")
    private String sp2;

    /**
     * 销售属性3
     */
    @TableField("sp3")
    private String sp3;

    /**
     * 商品主图
     */
    @TableField("product_pic")
    private String productPic;

    /**
     * 商品名称
     */
    @TableField("product_name")
    private String productName;

    /**
     * 商品品牌
     */
    @TableField("product_brand")
    private String productBrand;

    /**
     * 商品的条码
     */
    @TableField("product_sn")
    private String productSn;

    /**
     * 商品副标题（卖点）
     */
    @TableField("product_sub_title")
    private String productSubTitle;

    /**
     * 商品sku条码
     */
    @TableField("product_sku_code")
    private String productSkuCode;

    /**
     * 会员昵称
     */
    @TableField("member_nickname")
    private String memberNickname;

    /**
     * 创建时间
     */
    @TableField("create_date")
    private LocalDateTime createDate;

    /**
     * 修改时间
     */
    @TableField("modify_date")
    private LocalDateTime modifyDate;

    /**
     * 是否删除
     */
    @TableField("delete_status")
    private Integer deleteStatus;

    /**
     * 商品的分类
     */
    @TableField("product_category_id")
    private Long productCategoryId;

    /**
     * 商品销售属性:[{"key":"颜色","value":"银色"},{"key":"容量","value":"4G"}]
     */
    @TableField("product_attr")
    private String productAttr;
}
