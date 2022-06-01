package org.pj.metaverse.workflow.entity.vo;

import lombok.Data;
import org.camunda.bpm.engine.impl.persistence.entity.SuspensionState;
import org.camunda.bpm.engine.impl.persistence.entity.TaskEntity;
import org.camunda.bpm.engine.task.DelegationState;
import org.camunda.bpm.engine.task.Task;

import java.util.Date;
import java.util.Map;

/**
 * Task - 展示对象
 *
 * @author charlotte
 * @date 2022-05-27 12:00
 */
@Data
public class TaskVo<T> {

    private String id;
    private int revision;

    private String owner;
    private String assignee;
    private DelegationState delegationState;

    private String parentTaskId;

    private String name;
    private String description;
    private int priority = Task.PRIORITY_NORMAL;
    // The time when the task has been created
    private Date createTime;
    private Date dueDate;
    private Date followUpDate;
    private int suspensionState = SuspensionState.ACTIVE.getStateCode();
    private TaskEntity.TaskState lifecycleState = TaskEntity.TaskState.STATE_INIT;
    private String tenantId;


    // execution
    protected String executionId;

    protected String processInstanceId;

    protected String processDefinitionId;

    // caseExecution
    protected String caseExecutionId;

    protected String caseInstanceId;
    protected String caseDefinitionId;

    // taskDefinition
    protected String taskDefinitionKey;

    protected boolean isDeleted;
    protected String deleteReason;

    protected String eventName;
    protected boolean isFormKeyInitialized = false;
    //protected String formKey;

    /**
     * 当前流程实例对应的流程变量
     */
    private Map<String, Object> processVariables;
    /**
     * 流程业务数据
     */
    private T bizProcessData;
    /**
     * 任务业务数据
     */
    private T bizTaskData;
}
