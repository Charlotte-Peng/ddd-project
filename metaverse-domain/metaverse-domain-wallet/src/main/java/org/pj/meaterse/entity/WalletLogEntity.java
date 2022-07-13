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
 * 钱包支付记录
 * </p>
 *
 * @author pengjie
 * @since 2022-07-13 17:04:28
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("wallet_log")
@ApiModel(value = "WalletLogEntity对象", description = "钱包支付记录")
public class WalletLogEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("支付类型 0:系统支付 1:微信 2:支付宝 3:----")
    private Integer payType;

    @ApiModelProperty("消费or支付类型 1:金币支付 2:金币收入")
    private Integer type;

    @ApiModelProperty("支付金额(不同类型应对不同类型)")
    private BigDecimal num;


}
