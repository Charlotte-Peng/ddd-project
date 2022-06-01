package org.pj.metaverse.workflow.entity.param;

import lombok.Builder;
import lombok.Data;

/**
 * 流程查询参数
 *
 * @author charlotte
 * @date 2022-05-27 12:00
 */
@Data
@Builder
public class ProcessVariablesQueryParam{
    /**
     * 流程实例ID
     */
    private String processInstanceId;
    /**
     * 任务ID
     */
    private String taskId;
    /**
     * 流程变量名称
     */
    private String variableName;

    /**
     * 租户ID
     */
    private String tenantId;

}
