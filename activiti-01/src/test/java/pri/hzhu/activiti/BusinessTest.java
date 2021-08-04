package pri.hzhu.activiti;

import org.activiti.engine.*;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

public class BusinessTest {

    private static final String TASK_KEY = "myEvection";

    @Test
    public void addBusinessKey() {
        // 1. 获取引擎
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

        // 2. 获取runtimeService
        RuntimeService runtimeService = engine.getRuntimeService();

        // 3. 启动流程，并添加businessKey
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(TASK_KEY, "1002");
//       runtimeService.startProcessInstanceByKey(TASK_KEY, "1002");

        System.out.println(processInstance.getBusinessKey());
        System.out.println(processInstance.getProcessDefinitionKey());
    }

    @Test
    public void queryProcessInstance() {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = engine.getRuntimeService();

        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().processDefinitionKey(TASK_KEY).list();

        for (ProcessInstance processInstance : list) {
            System.out.println("----------------------------");
            System.out.println("流程实例id：" + processInstance.getProcessInstanceId());
            System.out.println("所属流程定义id：" + processInstance.getProcessDefinitionId());
            System.out.println("是否执行完成：" + processInstance.isEnded());
            System.out.println("是否暂停：" + processInstance.isSuspended());
            System.out.println("当前活动标识：" + processInstance.getActivityId());
        }

    }

//    @Test
    public void suspendAllProcessInstance() {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = engine.getRepositoryService();

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(TASK_KEY).singleResult();

        boolean suspended = processDefinition.isSuspended();
        String processDefinitionId = processDefinition.getId();

        if (suspended) {
            repositoryService.activateProcessDefinitionById(processDefinitionId);
            System.out.println(String.format("流程 %s 已激活", processDefinitionId));
        } else {
            repositoryService.suspendProcessDefinitionById(processDefinitionId, true, null);
            System.out.println(String.format("流程 %s 已挂起", processDefinitionId));
        }
    }

    @Test
    public void suspendSingleProcessInstance() {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = engine.getRuntimeService();

        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId("2501")
                .singleResult();
        String processInstanceId = processInstance.getId();

        boolean suspended = processInstance.isSuspended();
        if (suspended) {
            runtimeService.activateProcessInstanceById(processInstanceId);
            System.out.println(String.format("流程 %s 已激活", processInstanceId));
        } else {
            runtimeService.suspendProcessInstanceById(processInstanceId);
            System.out.println(String.format("流程 %s 已挂起", processInstanceId));
        }
    }

    @Test
    public void completeTask() {
        completeTask("zhangsan");
        completeTask("lisi");
        completeTask("wangwu");
        completeTask("rose");
    }


    private void completeTask(String assignee) {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = engine.getTaskService();

        Task task = taskService.createTaskQuery().processInstanceId("5004").taskAssignee(assignee).singleResult();
        if (task == null) {
            System.out.println("暂无可执行任务");
            return;
        }

        System.out.println("流程实例id="+task.getProcessInstanceId());
        System.out.println("任务Id="+task.getId());
        System.out.println("任务负责人="+task.getAssignee());
        System.out.println("任务名称="+task.getName());
        taskService.complete(task.getId());
    }
}
