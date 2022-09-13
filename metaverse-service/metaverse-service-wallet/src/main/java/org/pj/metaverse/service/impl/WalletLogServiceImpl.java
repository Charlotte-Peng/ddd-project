package org.pj.metaverse.service.impl;

import org.pj.metaverse.service.IWalletLogService;
import org.p.entity.WalletLogEntity;
import org.p.mapper.WalletLogMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 钱包支付记录 服务实现类
 * </p>
 *
 * @author pengjie
 * @since 2022-07-13 17:04:28
 */
@Service
public class WalletLogServiceImpl extends ServiceImpl<WalletLogMapper, WalletLogEntity> implements IWalletLogService {

}
