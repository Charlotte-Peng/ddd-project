package org.pj.metaverse.common.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.entity.repvo.TPointMapDetailRepVO;
import org.pj.metaverse.entity.reqvo.MgmtCreateMapPointInfoReqVO;
import org.pj.metaverse.entity.vo.MapPointInfoVO;
import org.pj.metaverse.entity.vo.PointInfoExplainVO;
import org.pj.metaverse.entity.vo.TPointMapVO;
import org.pj.metaverse.entity.vo.TUserLogVO;
import org.pj.metaverse.feign.RpgFeign;
import org.pj.metaverse.utils.NvlUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author pengjie
 * @date 17:06 2022/9/13
 **/
@Service
@RequiredArgsConstructor
public class CommonService {
    private final RpgFeign rpgFeign;

    /**
     * 获取用户指定类型操作记录
     * @author pengjie
     * @date 2022/9/13 16:22
     * @param userId 用户id
     * @param logType 日志类型
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    public TUserLogVO getLogByUserIdAndLogType(String userId, Integer logType) {
        TUserLogVO data = rpgFeign.getLogByUserIdAndLogType(userId, logType);
        if (NvlUtils.isNull(data)) {
            return null;
        }else {
            return data;
        }
    }

    /**
     * 写入用户指定类型操作记录
     * @param userLogEntity 用户操作记录
     */
    public void writeLogByUserIdAndLogType(TUserLogVO userLogEntity) {
        rpgFeign.writeLogByUserIdAndLogType(userLogEntity);
    }

    /**
     * 查询地图详情
     * @author pengjie
     * @date 2022/9/13 16:54
     * @param code 地图id
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    public TPointMapDetailRepVO queryMapDetail(String code){
        TPointMapVO vo = rpgFeign.queryMapDetail(code);
        if (NvlUtils.isNull(vo)) {
            return null;
        }else {
            String pointInfoJson = vo.getMapPointInfo();
            JSONArray mapPointInfo = JSON.parseArray(pointInfoJson);
            List<MapPointInfoVO> collect = mapPointInfo.stream()
                    .map(item -> {
                        JSONObject jsonObject1 = JSONObject.parseObject(item.toString());
                        JSONArray pointInfoExplain = jsonObject1.getJSONArray("explain");
                        List<PointInfoExplainVO> collect1 = pointInfoExplain.stream().map(item1 -> JSON.parseObject(item1.toString(), PointInfoExplainVO.class)).collect(Collectors.toList());
                        jsonObject1.remove("explain");
                        MapPointInfoVO mapPointInfoVO = JSON.parseObject(jsonObject1.toString(), MapPointInfoVO.class);
                        mapPointInfoVO.setExplain(collect1);
                        return mapPointInfoVO;
                    })
                    .collect(Collectors.toList());
            String mapPointJson = vo.getMapPoint();
            JSONArray mapPoint = JSON.parseArray(mapPointJson);
            int[][] ints = new int[mapPoint.size()][];
            for (int i = 0; i < mapPoint.size(); i++) {
                JSONArray jsonArray = mapPoint.getJSONArray(i);
                int[] ints1 = new int[jsonArray.size()];
                for (int j = 0; j < jsonArray.size(); j++) {
                    ints1[j] = jsonArray.getInteger(j);
                }
                ints[i] = ints1;
            }
            TPointMapDetailRepVO data = new TPointMapDetailRepVO();
            BeanUtils.copyProperties(vo, data);
            data.setMapPoint(ints);
            data.setMapPointInfo(collect);
            return data;
        }
    }
}
