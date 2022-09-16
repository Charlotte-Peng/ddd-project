package org.pj.metaverse.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.pj.metaverse.entity.TNpcEntity;
import org.pj.metaverse.entity.reqvo.MgmtCreateNpcReqVO;

import java.util.Map;

/**
 * <p>
 * NPC信息表 服务类
 * </p>
 *
 * @author pengjie
 * @since 2022-08-25 14:40:06
 */
public interface ITNpcMgmtService extends IService<TNpcEntity> {

    /**
     * 创建npc
     * @author pengjie
     * @date 2022/9/16 11:08
     * @param vo 创建npc请求
     */
    void createNpc(MgmtCreateNpcReqVO vo);

    /**
     * 查询npc列表
     * @author pengjie
     * @date 2022/9/16 11:10
     * @param parameterMap 查询参数
     * @return com.baomidou.mybatisplus.core.metadata.IPage<org.pj.metaverse.entity.TNpcEntity>
     */
    IPage<TNpcEntity> listNpc(Map<String, String[]> parameterMap);
}
