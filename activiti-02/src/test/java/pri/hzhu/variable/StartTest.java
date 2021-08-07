package pri.hzhu.variable;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.util.CollectionUtils;
import pri.hzhu.model.Evection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StartTest {

    /**
     * 流程删除
     */
    @Test
    public void deleteDefinition() {
        // 1. 获取repositoryService
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = engine.getRepositoryService();

        // 2. 查询流程
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        List<ProcessDefinition> definitionList = processDefinitionQuery.processDefinitionKey(pri.hzhu.variable.Constant.TASK_KEY).list();


        for (ProcessDefinition processDefinition : definitionList) {
            // 开启级联删除
            repositoryService.deleteDeployment(processDefinition.getDeploymentId(), true);
        }
    }

    @Test
    public void deploy() {
        // 1. 创建processEngine
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

        // 2. 获取repositoryService
        RepositoryService repositoryService = engine.getRepositoryService();

        // 3. 使用service进行流程部署
        Deployment deploy = repositoryService.createDeployment().name("测试监听")
                .addClasspathResource("bpmn/dynamicEve-global.bpmn")
                .deploy();

        System.out.println(String.format("流程部署ID: %s", deploy.getId()));
        System.out.println(String.format("流程部署名字: %s", deploy.getName()));

    }

    @Test
    public void startProcess() {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        // 启动
        RuntimeService runtimeService = engine.getRuntimeService();

        Evection evection = new Evection();
        evection.setNum(10d);
        Map<String, Object> variables = new HashMap<>();
        variables.put("eve", evection);
        variables.put("assigne0", "xiaoli");
        variables.put("assigne1", "hr");
        variables.put("assigne2", "yangzong");

        runtimeService.startProcessInstanceByKey(Constant.TASK_KEY, "10086", variables);
        System.out.println(engine.getName());

        complete("xiaoli");
    }

    @Test
    public void complete() {
//        queryTask("hr");
//        queryTask("yangzong");
        complete("yangzong");
        complete("hr");
    }

    public void complete(String assignee) {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = engine.getTaskService();

        List<Task> taskList = taskService.createTaskQuery().processDefinitionKey(Constant.TASK_KEY).taskAssignee(assignee).list();
        if (CollectionUtils.isEmpty(taskList)) {
            System.out.println(String.format("【%s】暂无可执行任务", assignee));
            return;
        }
        for (Task task : taskList) {
            String id = task.getId();
            taskService.complete(id);
        }
    }

    public void queryTask(String assignee) {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = engine.getTaskService();

        List<Task> taskList = taskService.createTaskQuery().processDefinitionKey(Constant.TASK_KEY).taskAssignee(assignee).list();
        if (CollectionUtils.isEmpty(taskList)) {
            System.out.println(String.format("【%s】暂无可执行任务", assignee));
            return;
        }
        for (Task task : taskList) {
            String id = task.getId();
//            taskService.complete(id);
            System.out.println(String.format("%s的任务%s", assignee, task.getName()));
        }
    }

}
