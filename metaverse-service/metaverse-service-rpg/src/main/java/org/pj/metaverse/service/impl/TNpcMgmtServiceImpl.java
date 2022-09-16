package org.pj.metaverse.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.ejlchina.searcher.util.MapUtils;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.entity.TNpcEntity;
import org.pj.metaverse.entity.reqvo.MgmtCreateNpcReqVO;
import org.pj.metaverse.mapper.TNpcMapper;
import org.pj.metaverse.service.ITNpcMgmtService;
import org.pj.metaverse.service.ITNpcService;
import org.pj.metaverse.utils.SearchResultToIPageUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * NPC信息表 服务实现类
 * </p>
 *
 * @author pengjie
 * @since 2022-08-25 14:40:06
 */
@Service
@RequiredArgsConstructor
public class TNpcMgmtServiceImpl extends ServiceImpl<TNpcMapper, TNpcEntity> implements ITNpcMgmtService {
    private final BeanSearcher beanSearcher;

    @Override
    public void createNpc(MgmtCreateNpcReqVO vo) {
        TNpcEntity entity = new TNpcEntity();
        BeanUtils.copyProperties(vo, entity);
        this.save(entity);
    }

    @Override
    public IPage<TNpcEntity> listNpc(Map<String, String[]> parameterMap) {
        Map<String, Object> flat = MapUtils.flat(parameterMap);
        SearchResult<TNpcEntity> search = beanSearcher.search(TNpcEntity.class, flat);
        IPage<TNpcEntity> convert = SearchResultToIPageUtil.convert(search);
        return convert;
    }
}
