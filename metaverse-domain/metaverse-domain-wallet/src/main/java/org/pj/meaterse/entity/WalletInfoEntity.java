package org.pj.meaterse.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.pj.metaverse.entity.BaseEntity;

/**
 * <p>
 * 用户账户表
 * </p>
 *
 * @author pengjie
 * @since 2022-07-13 17:04:28
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("wallet_info")
@ApiModel(value = "WalletInfoEntity对象", description = "用户账户表")
public class WalletInfoEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("支付密码")
    private String payPassword;

    @ApiModelProperty("金币数量")
    private BigDecimal coin;

    @ApiModelProperty("钱数量")
    private BigDecimal money;

    @ApiModelProperty("积分")
    private BigDecimal integral;


}
