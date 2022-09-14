package org.pj.metaverse.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.pj.metaverse.entity.TPointInfoEntity;
import org.pj.metaverse.entity.reqvo.MgmtCreatePointInfoReqVO;
import org.pj.metaverse.mapper.TPointInfoMapper;
import org.pj.metaverse.service.ITPointInfoMgmtService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 关卡节点相关信息 服务实现类
 * </p>
 *
 * @author pengjie
 * @since 2022-08-25 14:40:06
 */
@Service
public class TPointInfoMgmtServiceImpl extends ServiceImpl<TPointInfoMapper, TPointInfoEntity> implements ITPointInfoMgmtService {

    @Override
    public void createPointInfo(MgmtCreatePointInfoReqVO reqVO) {
        TPointInfoEntity entity = new TPointInfoEntity();
        BeanUtils.copyProperties(reqVO, entity);
        this.save(entity);
    }
}
