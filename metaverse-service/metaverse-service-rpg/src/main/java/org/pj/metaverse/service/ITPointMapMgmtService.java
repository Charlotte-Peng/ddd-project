package org.pj.metaverse.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.pj.metaverse.entity.TPointMapEntity;
import org.pj.metaverse.entity.reqvo.MgmtCreateMapReqVO;

import java.util.Map;

/**
 * <p>
 * 游戏关卡信息 服务类
 * </p>
 *
 * @author pengjie
 * @since 2022-08-25 14:40:06
 */
public interface ITPointMapMgmtService extends IService<TPointMapEntity> {

    /**
     * 创建地图
     * @author pengjie
     * @date 2022/9/13 9:46
     * @param vo 创建地图请求参数
     */
    void createMap(MgmtCreateMapReqVO vo);

    /**
     * 查询地图列表
     * @author pengjie
     * @date 2022/9/13 15:14
     * @param parameterMap 查询参数
     * @return com.baomidou.mybatisplus.core.metadata.IPage<org.pj.metaverse.entity.TPointMapEntity>
     */
    IPage<TPointMapEntity> queryMapList(Map<String, String[]> parameterMap);
}
