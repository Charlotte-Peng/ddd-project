package org.pj.metaverse.service.impl;

import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.util.MapUtils;
import de.huxhorn.sulky.ulid.ULID;
import org.pj.metaverse.entity.UserEntity;
import org.pj.metaverse.entity.reqvo.UserLoginReqVO;
import org.pj.metaverse.entity.reqvo.UserRegisterReqVO;
import org.pj.metaverse.exception.ServerException;
import org.pj.metaverse.repository.redis.UserRepositoryRedis;
import org.pj.metaverse.result.DataResult;
import org.pj.metaverse.service.UserService;
import org.pj.metaverse.utils.NvlUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author pengjie
 * @date 17:51 2022/8/23
 **/
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepositoryRedis userRepositoryRedis;

    @Resource
    private ULID ulid;

    @Resource
    private BeanSearcher beanSearcher;

    @Override
    public UserEntity register(UserRegisterReqVO vo) {
        // 根据账号查询是否存在该用户
        UserEntity user = userRepositoryRedis.findUserEntityByUserName(vo.getUserName());
        if(NvlUtils.isNotNull(user)){
            throw new ServerException("账号已存在");
        }
        // 查询 最后一个人的userNo
        Map<String, Object> build = MapUtils.builder()
                .orderBy(UserEntity::getUserNo, "desc")
                .build();
        UserEntity user1 = beanSearcher.searchFirst(UserEntity.class, build);
        UserEntity userEntity = new UserEntity();
        if (NvlUtils.isNotNull(user1)) {
            userEntity.setUserNo(user1.getUserNo() + 1);
        } else {
            userEntity.setUserNo(10000);
        }
        String userId = ulid.nextULID();
        userEntity.setUserId(userId);
        BeanUtils.copyProperties(vo, userEntity);
        if (NvlUtils.isNull(vo.getSex())) {
            userEntity.setSex("2");
        }
        userEntity.setCreateTime(LocalDateTime.now());
        userRepositoryRedis.save(userEntity);
        UserEntity userEntityByUserId = userRepositoryRedis.findUserEntityByUserId(userId);
        return userEntityByUserId;
    }

    @Override
    public UserEntity login(UserLoginReqVO vo) {
        // 根据账号查询是否存在该用户
        UserEntity user = userRepositoryRedis.findUserEntityByUserNameAndUserPassword(vo.getUserName(), vo.getUserPassword());
        if(NvlUtils.isNull(user)){
            throw new ServerException("账号不存在");
        }
        return user;
    }
}
