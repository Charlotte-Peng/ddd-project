package org.pj.service.impl;

import org.p.entity.WalletLogEntity;
import org.p.mapper.WalletLogMapper;
import org.pj.service.IWalletLogService;
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
