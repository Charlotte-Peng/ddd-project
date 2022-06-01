package org.pj.metaverse.workflow.entity.param;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.pj.metaverse.workflow.entity.BasePageParam;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * task查询参数
 *
 * @author charlotte
 * @date 2022-05-27 12:00
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TaskQueryParam extends BasePageParam {
    /**
     * 任务处理者
     */
    private String assignee;
    /**
     * 流程定义ID
     */
    private String processDefinitionKey;
    /**
     * 任务定义ID
     */
    private String taskDefinitionKey;
    /**
     * 租户ID
     */
    private String tenantId;
    /**
     * 是否附加查询流程变量
     */
    private Boolean withProcessVariables;

    /**
     * 开始时间 - 起始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startedAfter;
    /**
     * 开始时间 - 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startedBefore;
    /**
     * 是否已完成
     */
    private Boolean finished;
}
