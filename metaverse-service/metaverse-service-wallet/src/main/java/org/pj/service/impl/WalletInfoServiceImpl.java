package org.pj.service.impl;

import org.pj.mapper.WalletInfoMapper;
import org.pj.meaterse.entity.WalletInfoEntity;
import org.pj.service.IWalletInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户账户表 服务实现类
 * </p>
 *
 * @author pengjie
 * @since 2022-07-13 17:04:28
 */
@Service
public class WalletInfoServiceImpl extends ServiceImpl<WalletInfoMapper, WalletInfoEntity> implements IWalletInfoService {

}
