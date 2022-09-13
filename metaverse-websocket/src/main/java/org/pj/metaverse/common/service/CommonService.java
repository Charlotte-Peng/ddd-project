package org.pj.metaverse.common.service;

import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.entity.repvo.TPointMapDetailRepVO;
import org.pj.metaverse.entity.reqvo.MgmtCreateMapPointInfoReqVO;
import org.pj.metaverse.entity.vo.TUserLogVO;
import org.pj.metaverse.feign.RpgFeign;
import org.pj.metaverse.utils.NvlUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

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
        Map<String, Object> logByUserIdAndLogType = rpgFeign.getLogByUserIdAndLogType(userId, logType);
        if (NvlUtils.isNull(logByUserIdAndLogType)) {
            return null;
        }else {
            String s = JSON.toJSONString(logByUserIdAndLogType);
            TUserLogVO data = new TUserLogVO();
            BeanUtils.copyProperties(JSON.parseObject(s, TUserLogVO.class),data);
            return data;
        }
    }

    /**
     * 查询地图详情
     * @author pengjie
     * @date 2022/9/13 16:54
     * @param id 地图id
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    public TPointMapDetailRepVO queryMapDetail(String code){
        Map<String, Object> map = rpgFeign.queryMapDetail(code);
        if (NvlUtils.isNull(map)) {
            return null;
        }else {
            String s = JSON.toJSONString(map);
            TPointMapDetailRepVO data = JSON.parseObject(s, TPointMapDetailRepVO.class);
            return data;
        }
    }
}
