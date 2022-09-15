package org.pj.metaverse.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.pj.metaverse.entity.TCityEntity;
import org.pj.metaverse.entity.reqvo.MgmtCreateCityReqVO;

import java.util.Map;

/**
 * <p>
 * 城镇地图信息 服务类
 * </p>
 *
 * @author pengjie
 * @since 2022-08-25 14:40:06
 */
public interface ITCityMgmtService extends IService<TCityEntity> {

    /**
     * 创建城镇地图信息
     * @author pengjie
     * @date 2022/9/15 11:44
     * @param vo 创建城镇地图信息请求类
     */
    void createCity(MgmtCreateCityReqVO vo);

    /**
     * 查询城镇地图列表
     * @author pengjie
     * @date 2022/9/15 11:54
     * @param parameterMap 查询参数
     * @return com.baomidou.mybatisplus.core.metadata.IPage<org.pj.metaverse.entity.TCityEntity>
     */
    IPage<TCityEntity> queryCityList(Map<String, String[]> parameterMap);
}
