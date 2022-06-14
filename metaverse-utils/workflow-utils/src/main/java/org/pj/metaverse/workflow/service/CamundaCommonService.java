package org.pj.metaverse.workflow.service;

import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.history.HistoricTaskInstanceQuery;
import org.camunda.bpm.engine.history.HistoricVariableInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.VariableInstance;
import org.camunda.bpm.engine.runtime.VariableInstanceQuery;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;
import org.pj.metaverse.workflow.entity.DataResult;
import org.pj.metaverse.workflow.entity.param.ProcessVariablesQueryParam;
import org.pj.metaverse.workflow.entity.param.TaskQueryParam;
import org.pj.metaverse.workflow.entity.vo.TaskVo;
import org.pj.metaverse.workflow.utils.CamundaUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author charlotte
 * @date 2022-05-27 12:00
 **/
@SuppressWarnings({"DanglingJavadoc", "unchecked", "rawtypes"})
@Slf4j
public class CamundaCommonService {

    @Resource
    private ProcessEngine processEngine;
    @Resource
    private RuntimeService runtimeService;
    @Resource
    private TaskService taskService;
    @Resource
    private HistoryService historyService;


    /** ================================================================================== */
    /** ========================== 流程相关（RuntimeService）=============================== */
    /** ================================================================================== */

    /**
     * 启动流程实例
     *
     * @param processDefinitionKey 流程定义的Key
     * @param businessKey 业务唯一主键
     * @param processVariables 流程的变量
     * @return 流程唯一id
     */
    public ProcessInstance startProcessInstance(String processDefinitionKey, String businessKey, Object processVariables) {
        log.debug("start process instance, processDefinitionKey: {}, businessKey={}, processVariables: {}", processDefinitionKey, businessKey, processVariables);
        Map processVariablesMap = CamundaUtils.convertProcessVariablesFromEntity(processVariables);
        ProcessInstance processInstance = this.runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, processVariablesMap);
        log.debug("start process instance success, processInstanceId: {}", processInstance.getId());
        return processInstance;
    }


    /**
     * 启动流程实例
     *
     * @param processDefinitionKey 流程定义的Key
     * @param businessKey 业务唯一主键
     * @return 流程唯一id
     */
    public ProcessInstance startProcessInstance(String processDefinitionKey, String businessKey) {
        log.debug("start process instance, processDefinitionKey: {}, businessKey={}", processDefinitionKey, businessKey);
        ProcessInstance processInstance = this.runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey);
        log.debug("start process instance success, processInstanceId: {}", processInstance.getId());
        return processInstance;
    }


    /**
     * 获取流程实例对应的流程变量集合
     *
     * @param processInstanceId 流程生成的id
     * @return 流程变量集合
     */
    public Map<String, Object> getRuntimeProcessVariables(String processInstanceId) {
        //获取流程变量
        List<VariableInstance> variableInstanceList = this.runtimeService.createVariableInstanceQuery()
                .processInstanceIdIn(processInstanceId)
                //.taskIdIn()
                .list();
        return CamundaUtils.convertVariableInstances(variableInstanceList);
    }

    /**
     * 获取流程实例对应的流程变量集合
     *
     * @param processVariablesQueryParam 查询流程变量的参数
     * @return 流程变量
     */
    public Map<String, Object> getRuntimeProcessVariables(ProcessVariablesQueryParam processVariablesQueryParam) {
        //创建查询
        VariableInstanceQuery variableInstanceQuery = this.runtimeService.createVariableInstanceQuery();
        CamundaUtils.setNotNull(variableInstanceQuery::processInstanceIdIn, processVariablesQueryParam.getProcessInstanceId());
        CamundaUtils.setNotNull(variableInstanceQuery::taskIdIn, processVariablesQueryParam.getTaskId());
        CamundaUtils.setNotNull(variableInstanceQuery::variableName, processVariablesQueryParam.getVariableName());
        CamundaUtils.setNotNull(variableInstanceQuery::tenantIdIn, processVariablesQueryParam.getTenantId());

        //获取流程变量
        List<VariableInstance> variableInstanceList = variableInstanceQuery.list();
        return CamundaUtils.convertVariableInstances(variableInstanceList);
    }

    /**
     * 获取流程实例对应的流程变量集合
     *
     * @param processInstanceId 流程唯一id
     * @return 获取运行时候的变量
     */
    public <T> T getRuntimeProcessVariables(String processInstanceId, String processVariableName) {
        //获取流程变量
        VariableInstance variableInstance = this.runtimeService.createVariableInstanceQuery()
                .processInstanceIdIn(processInstanceId)
                //.taskIdIn()
                .variableName(processVariableName)
                .singleResult();
        return (T) variableInstance.getValue();
    }


    /**
     * 获取流程实例对应的流程变量集合
     *
     * @param processInstanceId 流程唯一id
     * @return 流程变量集合
     */
    public Map<String, Object> getHistoryProcessVariables(String processInstanceId) {

        //获取流程变量
        List<HistoricVariableInstance> historicVariableInstanceList = this.historyService.createHistoricVariableInstanceQuery()
                .processInstanceId(processInstanceId)
                //.taskIdIn()
                .list();
        return CamundaUtils.convertHistoricVariableInstances(historicVariableInstanceList);
    }

    /** ================================================================================== */
    /** ========================== 任务相关（TaskService、HistoryService）================== */
    /** ================================================================================== */

    /**
     * 查询代办任务分页列表
     *
     * @param taskQueryParam 任务查询条件
     * @return 代办任务分页列表
     */
    public <T> DataResult<List<TaskVo<T>>> queryRuntimeTasks(TaskQueryParam taskQueryParam) {
        return this.queryRuntimeTasks(taskQueryParam, null, null, null, null);
    }

    /**
     * 查询代办任务分页列表
     *
     * @param taskQueryParam 任务查询条件
     * @param extendTaskQuery 额外查询条件
     * @return 代办任务分页列表
     */
    public <T> DataResult<List<TaskVo<T>>> queryRuntimeTasks(TaskQueryParam taskQueryParam, Consumer<TaskQuery> extendTaskQuery) {
        return this.queryRuntimeTasks(taskQueryParam, extendTaskQuery, null, null, null);
    }


    /**
     * 查询代办任务分页列表
     *
     * @param taskQueryParam                 任务查询参数
     * @param taskBizKeyVariableName         任务对应的 用于标识任务数据的 流程变量名，
     * @param taskBizKey2BizTaskDataFunction 转换 标识任务数据的流程变量名 为 具体业务任务数据
     * @return 代办任务分页列表
     */
    public <T> DataResult<List<TaskVo<T>>> queryRuntimeTasks(TaskQueryParam taskQueryParam,
                                                       String taskBizKeyVariableName,
                                                       Function<String, T> taskBizKey2BizTaskDataFunction) {
        return this.queryRuntimeTasks(taskQueryParam, null, taskBizKeyVariableName, taskBizKey2BizTaskDataFunction, null);
    }

    /**
     * 查询代办任务分页列表
     *
     * @param taskQueryParam                任务查询参数
     * @param bizKey2BizProcessDataFunction 转换 流程BusinessKey 为 具体业务流程数据
     * @return 代办任务分页列表
     */
    public <T> DataResult<List<TaskVo<T>>> queryRuntimeTasks(TaskQueryParam taskQueryParam, Function<String, T> bizKey2BizProcessDataFunction) {
        return this.queryRuntimeTasks(taskQueryParam, null, null, null, bizKey2BizProcessDataFunction);
    }

    /**
     * 查询代办任务分页列表
     *
     * @param taskQueryParam                 任务查询参数
     * @param extendTaskQuery                自定义扩展查询参数（若无特殊扩展可为空）
     * @param taskBizKeyVariableName         任务对应的 用于标识任务数据的 流程变量名，
     * @param taskBizKey2BizTaskDataFunction 转换 标识任务数据的流程变量名 为 具体业务任务数据
     * @param bizKey2BizProcessDataFunction  转换 流程BusinessKey 为 具体业务流程数据
     * @return 代办任务分页列表
     */
    public <T> DataResult<List<TaskVo<T>>> queryRuntimeTasks(TaskQueryParam taskQueryParam,
                                                       Consumer<TaskQuery> extendTaskQuery,
                                                       String taskBizKeyVariableName,
                                                       Function<String, T> taskBizKey2BizTaskDataFunction,
                                                       Function<String, T> bizKey2BizProcessDataFunction) {
        log.debug("query runtime tasks, param: {}", taskQueryParam);

        //创建查询
        TaskQuery taskQuery = this.taskService.createTaskQuery();
        CamundaUtils.setNotNull(taskQuery::processDefinitionKey, taskQueryParam.getProcessDefinitionKey());
        CamundaUtils.setNotNull(taskQuery::taskDefinitionKey, taskQueryParam.getTaskDefinitionKey());
        CamundaUtils.setNotNull(taskQuery::taskAssignee, taskQueryParam.getAssignee());
        CamundaUtils.setNotNull(taskQuery::tenantIdIn, taskQueryParam.getTenantId());

        //添加附加条件
        Optional.ofNullable(extendTaskQuery)
                .ifPresent(addConditionConsumer -> addConditionConsumer.accept(taskQuery));
        //计算分页参数
        int curPageStartIndex = (taskQueryParam.getPageNo() - 1) * taskQueryParam.getPageSize();
        //设置排序参数
        CamundaUtils.setTaskQueryOrderBy(taskQuery, taskQueryParam);
        //查询任务列表
        List<Task> taskList = taskQuery.listPage(curPageStartIndex, taskQueryParam.getPageSize());
        //查询任务总数
        Long totalCount = taskQuery.count();


        //直接返回Task对象Json序列化异常，转换为VO对象
        List<TaskVo<T>> taskVoList = taskList.stream()
                .map(CamundaUtils::<T>convertTask)
                .peek(taskVo -> {
                    //设置流程变量
                    this.setTaskProcessVariables(taskVo, taskQueryParam);
                    //设置businessKey对应的流程业务数据
                    this.setTaskVoProcessData(taskVo, bizKey2BizProcessDataFunction);
                    //设置task变量（例如paymentId）对应的task业务数据
                    this.setTaskVoTaskData(taskVo, taskBizKeyVariableName, taskBizKey2BizTaskDataFunction);
                })
                .collect(Collectors.toList());

        DataResult<List<TaskVo<T>>> respResult = DataResult.successRows(totalCount,taskVoList);
        log.debug("query runtime tasks, result: {}", respResult);
        return respResult;
    }


    /**
     * 设置task对应流程变量
     *
     * @param taskVo 要设置的对象
     * @param taskQueryParam 变量值
     */
    private void setTaskProcessVariables(TaskVo taskVo, TaskQueryParam taskQueryParam) {
        //设置流程变量
        if (taskQueryParam.getWithProcessVariables()) {
            taskVo.setProcessVariables(this.getRuntimeProcessVariables(taskVo.getProcessInstanceId()));
        }
    }

    /**
     * 设置task对应的业务流程数据<br/>
     * task -> task.processInstanceId -> processInstance -> processInstance.businessKey -> bizProcessData
     *
     * @param taskVo 任务对象
     * @param bizKey2BizProcessDataFunction 任务流程函数
     * @param <T> 类型T
     */
    private <T> void setTaskVoProcessData(TaskVo taskVo, Function<String, T> bizKey2BizProcessDataFunction) {
        if (null == bizKey2BizProcessDataFunction) {
            return;
        }
        ProcessInstance processInstance = this.runtimeService.createProcessInstanceQuery()
                .processInstanceId(taskVo.getProcessInstanceId())
                .singleResult();
        if (null != processInstance && null != processInstance.getBusinessKey()) {
            taskVo.setBizProcessData(bizKey2BizProcessDataFunction.apply(processInstance.getBusinessKey()));
        }
    }

    /**
     * 设置task对应的业务任务数据
     * task -> task.processVariables[taskBizDataVariableName] -> bizTaskData
     *
     * @param taskVo 任务vo
     * @param taskBizDataVariableName 任务变量名
     * @param processVariable2BizTaskDataFunction 任务函数
     */
    private <M> void setTaskVoTaskData(TaskVo taskVo, String taskBizDataVariableName,
                                       Function<String, M> processVariable2BizTaskDataFunction) {
        if (null == taskBizDataVariableName || null == processVariable2BizTaskDataFunction) {
            return;
        }
        String taskDataIdValue = null;
        if (null != taskVo.getProcessVariables() && taskVo.getProcessVariables().containsKey(taskBizDataVariableName)) {
            taskDataIdValue = String.valueOf(taskVo.getProcessVariables().get(taskBizDataVariableName));
        } else {
            taskDataIdValue = this.getRuntimeProcessVariables(taskVo.getProcessInstanceId(), taskBizDataVariableName);
        }
        taskVo.setBizTaskData(processVariable2BizTaskDataFunction.apply(taskDataIdValue));
    }


    /**
     * 查询已完成任务分页列表
     *
     * @param taskQueryParam 任务条件
     * @param addCondition 已完成任务条件
     * @return 已完成任务分页列表
     */
    public DataResult<List<HistoricTaskInstance>> queryHistoryTasks(TaskQueryParam taskQueryParam, Consumer<HistoricTaskInstanceQuery> addCondition) {
        //创建查询
        HistoricTaskInstanceQuery historicTaskInstanceQuery = this.historyService.createHistoricTaskInstanceQuery();
        if (Boolean.TRUE.equals(taskQueryParam.getFinished())) {
            historicTaskInstanceQuery.finished();
        }
        if (Boolean.FALSE.equals(taskQueryParam.getFinished())) {
            historicTaskInstanceQuery.unfinished();
        }
        CamundaUtils.setNotNull(historicTaskInstanceQuery::processDefinitionKey, taskQueryParam.getProcessDefinitionKey());
        CamundaUtils.setNotNull(historicTaskInstanceQuery::taskDefinitionKey, taskQueryParam.getTaskDefinitionKey());
        CamundaUtils.setNotNull(historicTaskInstanceQuery::taskAssignee, taskQueryParam.getAssignee());
        CamundaUtils.setNotNull(historicTaskInstanceQuery::tenantIdIn, taskQueryParam.getTenantId());
        CamundaUtils.setNotNull(historicTaskInstanceQuery::startedAfter, taskQueryParam.getStartedAfter());
        CamundaUtils.setNotNull(historicTaskInstanceQuery::startedBefore, taskQueryParam.getStartedBefore());
        //添加附加条件
        Optional.ofNullable(addCondition)
                .ifPresent(addConditionConsumer -> addConditionConsumer.accept(historicTaskInstanceQuery));
        //计算分页参数
        Integer curPageStartIndex = CamundaUtils.getPageStartIndex(taskQueryParam);
        //设置排序参数
        CamundaUtils.setHistoricTaskInstanceQueryOrderBy(historicTaskInstanceQuery, taskQueryParam);
        //查询任务列表
        List<HistoricTaskInstance> taskList = historicTaskInstanceQuery.listPage(curPageStartIndex, taskQueryParam.getPageSize());
        //查询任务总数
        Long totalCount = historicTaskInstanceQuery.count();

        ////直接返回Task对象Json序列化异常，转换为VO对象
        //List<TaskVo> taskVoList = taskList.stream()
        //        .map(taskVo -> {
        //            if (taskQueryParam.getWithProcessVariables()) {
        //                taskVo.setProcessVariables(this.getProcessVariables(taskVo.getProcessInstanceId()));
        //            }
        //            return taskVo;
        //        })
        //        .collect(Collectors.toList());
        return DataResult.successRows(totalCount, taskList);
    }

    /**
     * 查询已完成任务分页列表
     *
     * @param taskQueryParam 任务条件
     * @return 已完成任务分页列表
     */
    public DataResult<List<HistoricTaskInstance>> queryHistoryTasks(TaskQueryParam taskQueryParam) {
        return this.queryHistoryTasks(taskQueryParam, null);
    }


    /**
     * 完成任务
     *
     * @param taskId 任务ID
     */
    public void completeTask(String taskId) {
        log.debug("complete task, taskId: {}", taskId);
        this.taskService.complete(taskId);
    }

    /**
     * 完成任务
     *
     * @param taskId           任务ID
     * @param processVariables 流程变量（支持Map和POJO）
     */
    public void completeTask(String taskId, Object processVariables) {
        log.debug("complete task, taskId: {}, processVariables: {}", taskId, processVariables);
        Map processVariablesMap = CamundaUtils.convertProcessVariablesFromEntity(processVariables);
        this.taskService.complete(taskId, processVariablesMap);
    }
}
