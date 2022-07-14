package org.pj.metaverse.constant;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author pengjie
 * @date 11:24 2022/6/16
 **/
@Component
@Data
public class ProjectDefaultProperties {
    @Value("${project.name}")
    private String projectName;
    @Value("${project.tenantId}")
    private String projectTenantId;
    @Value("${project.debug}")
    private boolean debug;
}
