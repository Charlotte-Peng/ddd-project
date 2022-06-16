package org.pj.metaverse.entity.user;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author pengjie
 * @date 16:38 2022/5/25
 **/
@Data
public class LoginEvent {
    private String userId;
    private String ip;
    private String type;
    private LocalDateTime nowTime;
    private String mobileInfo;
}
