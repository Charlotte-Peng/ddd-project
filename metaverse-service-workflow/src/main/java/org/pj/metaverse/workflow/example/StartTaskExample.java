package org.pj.metaverse.workflow.example;

import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.pj.metaverse.workflow.entity.BasePageParam;
import org.pj.metaverse.workflow.entity.DataResult;
import org.pj.metaverse.workflow.entity.param.TaskQueryParam;
import org.pj.metaverse.workflow.entity.vo.TaskVo;
import org.pj.metaverse.workflow.service.CamundaCommonService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author charlotte
 * @date 2022-05-27 12:00
 **/
@Component
public class StartTaskExample {
    @Resource
    CamundaCommonService camundaCommonService;
    public void start(){
        // 开启工作流，返回生成唯一的流程id
        ProcessInstance processInstance = camundaCommonService.startProcessInstance("这里是任务的id，例如流程图3",
                "这里是自己业务逻辑的唯一主键",
                "这里是要给流程图设置的变量参数");
        String id = processInstance.getProcessInstanceId();
        // TODO 将业务逻辑执行完成，写入数据库，并储存这个唯一id
    }
    public void task(){
        // TODO 当任务完成，需要改变中间的任务状态时,参考 class HandlerConfigurationExample
    }
    public void end(String id){
        // 假设查这个对象的值
        BasePageParam param = new BasePageParam();
        // TODO 查询流程当前的变量状态
        Map<String, Object> runtimeProcessVariables = camundaCommonService.getRuntimeProcessVariables(id);
        String aaa = camundaCommonService.getRuntimeProcessVariables(id, param.getOrderBy());
        // TODO 当根据流程完成最后的操作时，可以手动进行任务完成
        camundaCommonService.completeTask(id,"这里时完成需要的条件");
    }
    public void selectHistoryTask(){
        // TODO 根据条件查询历史任务
        DataResult<List<HistoricTaskInstance>> listDataResult = camundaCommonService.queryHistoryTasks(new TaskQueryParam());
    }
    public void selectRunTimeTask(){
        // TODO 根据条件查询历史任务
        DataResult<List<TaskVo<String>>> listDataResult = camundaCommonService.queryRuntimeTasks(new TaskQueryParam());
    }
}
