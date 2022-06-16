package org.pj.metaverse.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.constant.redis.UserRedisConstant;
import org.pj.metaverse.entity.UserEntity;
import org.pj.metaverse.mapper.UserMapper;
import org.pj.metaverse.service.IUserService;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pengjie
 * @since 2022-05-08 13:54:53
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {
    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public Long getLineCount() {
        // 从redis中获取在线用户数量
        return stringRedisTemplate.execute((RedisCallback<Long>) connection -> connection.bitCount(UserRedisConstant.USER_ONLINE_NUM.getBytes()));
    }
}
