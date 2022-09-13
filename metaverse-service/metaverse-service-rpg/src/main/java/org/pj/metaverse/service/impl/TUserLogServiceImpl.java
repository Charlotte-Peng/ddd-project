package org.pj.metaverse.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.entity.*;
import org.pj.metaverse.mapper.TUserLogMapper;
import org.pj.metaverse.service.ITUserLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author pengjie
 * @since 2022-08-25 14:40:06
 */
@Service
@RequiredArgsConstructor
public class TUserLogServiceImpl extends ServiceImpl<TUserLogMapper, TUserLogEntity> implements ITUserLogService {

}
