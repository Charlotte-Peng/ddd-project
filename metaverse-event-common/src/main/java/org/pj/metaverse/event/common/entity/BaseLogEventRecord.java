package org.pj.metaverse.event.common.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pj.metaverse.event.common.enums.EventEnum;

/**
 * @author pengjie
 * @date 15:13 2022/5/25
 **/
@Data
@AllArgsConstructor
public class BaseLogEventRecord {
    private EventEnum eventEnum;
    private String serverName;
    private String requestIp;
    private String message;
    private String userId;

}
