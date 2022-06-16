package org.pj.metaverse.entity;


import org.pj.metaverse.enums.EventEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

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
