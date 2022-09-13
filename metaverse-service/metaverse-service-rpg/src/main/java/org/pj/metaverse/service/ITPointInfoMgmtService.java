package org.pj.metaverse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.pj.metaverse.entity.TPointInfoEntity;
import org.pj.metaverse.entity.reqvo.MgmtCreatePointInfoReqVO;

/**
 * <p>
 * 关卡节点相关信息 服务类
 * </p>
 *
 * @author pengjie
 * @since 2022-08-25 14:40:06
 */
public interface ITPointInfoMgmtService extends IService<TPointInfoEntity> {

    /**
     * 创建关卡节点信息
     * @author pengjie
     * @date 2022/9/13 10:11
     * @param reqVO 创建关卡节点信息请求参数
     */
    void createPointInfo(MgmtCreatePointInfoReqVO reqVO);
}
