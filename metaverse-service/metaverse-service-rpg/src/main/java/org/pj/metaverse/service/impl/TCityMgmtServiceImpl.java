package org.pj.metaverse.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.ejlchina.searcher.util.MapUtils;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.entity.TCityEntity;
import org.pj.metaverse.entity.TNpcEntity;
import org.pj.metaverse.entity.reqvo.MgmtCreateCityPointInfoReqVO;
import org.pj.metaverse.entity.reqvo.MgmtCreateCityReqVO;
import org.pj.metaverse.entity.reqvo.MgmtCreateMapPointInfoReqVO;
import org.pj.metaverse.entity.vo.CityNpcInfoVO;
import org.pj.metaverse.exception.ServerException;
import org.pj.metaverse.mapper.TCityMapper;
import org.pj.metaverse.service.ITCityMgmtService;
import org.pj.metaverse.service.ITCityService;
import org.pj.metaverse.service.ITNpcService;
import org.pj.metaverse.utils.NvlUtils;
import org.pj.metaverse.utils.SearchResultToIPageUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 城镇地图信息 服务实现类
 * </p>
 *
 * @author pengjie
 * @since 2022-08-25 14:40:06
 */
@Service
@RequiredArgsConstructor
public class TCityMgmtServiceImpl extends ServiceImpl<TCityMapper, TCityEntity> implements ITCityMgmtService {
    private final ITNpcService npcService;
    private final BeanSearcher beanSearcher;

    @Override
    public void createCity(MgmtCreateCityReqVO vo) {
        TCityEntity entity = new TCityEntity();
        BeanUtils.copyProperties(vo, entity);
        List<CityNpcInfoVO> npcInfoList = new ArrayList<>();
        for (MgmtCreateCityPointInfoReqVO mgmtCreateCityPointInfoReqVO : vo.getPointInfoListJson()) {
            // 根据npcId查询npc信息
            TNpcEntity byId = npcService.getById(mgmtCreateCityPointInfoReqVO.getNpcId());
            if (NvlUtils.isNull(byId)) {
                throw new ServerException("npcId不存在");
            }
            CityNpcInfoVO cityNpcInfoVO = new CityNpcInfoVO();
            BeanUtils.copyProperties(byId, cityNpcInfoVO);
            cityNpcInfoVO.setNpcMessageListJson(mgmtCreateCityPointInfoReqVO.getNpcMessageListJson());
            // 将npc信息添加到城镇地图信息中
            npcInfoList.add(cityNpcInfoVO);
        }
        String npcInfoJson = JSON.toJSONString(npcInfoList);
        entity.setNpcInfo(npcInfoJson);
        this.save(entity);
    }

    @Override
    public IPage<TCityEntity> queryCityList(Map<String, String[]> parameterMap) {
        Map<String, Object> flat = MapUtils.flat(parameterMap);
        SearchResult<TCityEntity> search = beanSearcher.search(TCityEntity.class, flat);
        IPage<TCityEntity> convert = SearchResultToIPageUtil.convert(search);
        return convert;
    }
}
