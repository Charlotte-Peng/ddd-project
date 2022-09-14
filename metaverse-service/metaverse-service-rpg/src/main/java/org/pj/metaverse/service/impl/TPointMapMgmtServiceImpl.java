package org.pj.metaverse.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.ejlchina.searcher.util.MapUtils;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.entity.TPointInfoEntity;
import org.pj.metaverse.entity.TPointMapEntity;
import org.pj.metaverse.entity.reqvo.MgmtCreateMapPointInfoReqVO;
import org.pj.metaverse.mapper.TPointMapMapper;
import org.pj.metaverse.entity.reqvo.MgmtCreateMapReqVO;
import org.pj.metaverse.service.ITPointMapMgmtService;
import org.pj.metaverse.utils.SearchResultToIPageUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 游戏关卡信息 服务实现类
 * </p>
 *
 * @author pengjie
 * @since 2022-08-25 14:40:06
 */
@Service
@RequiredArgsConstructor
public class TPointMapMgmtServiceImpl extends ServiceImpl<TPointMapMapper, TPointMapEntity> implements ITPointMapMgmtService {
    private final BeanSearcher beanSearcher;

    @Override
    public void createMap(MgmtCreateMapReqVO vo) {
        TPointMapEntity mapEntity = new TPointMapEntity();
        List<TPointInfoEntity> pointInfoEntityList = new ArrayList<>(vo.getPointInfoListJson().size());
        for (MgmtCreateMapPointInfoReqVO mgmtCreateMapPointInfoReqVO : vo.getPointInfoListJson()) {
            TPointInfoEntity pointInfoEntity = new TPointInfoEntity();
            pointInfoEntity.setExplain(JSON.toJSONString(mgmtCreateMapPointInfoReqVO.getExplainData()));
            BeanUtils.copyProperties(mgmtCreateMapPointInfoReqVO, pointInfoEntity,"explain");
            pointInfoEntityList.add(pointInfoEntity);
        }
        BeanUtils.copyProperties(vo, mapEntity, "mapPoint");
        mapEntity.setMapPoint(JSON.toJSONString(vo.getMapPoint()));
        mapEntity.setMapPointInfo(JSON.toJSONString(pointInfoEntityList));
        this.save(mapEntity);
    }

    @Override
    public IPage<TPointMapEntity> queryMapList(Map<String, String[]> parameterMap) {
        Map<String, Object> flat = MapUtils.flat(parameterMap);
        SearchResult<TPointMapEntity> search = beanSearcher.search(TPointMapEntity.class, flat);
        IPage<TPointMapEntity> convert = SearchResultToIPageUtil.convert(search);
        return convert;
    }
}
