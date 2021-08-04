package pri.hzhu.activiti;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.task.Task;
import org.junit.After;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TaskListenerTest {

    private static final String TASK_KEY = "myProcess_1";

    Map<String, Object> assignMap;

    @Test
    public void deploy() {
        // 1. 创建processEngine
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

        // 2. 获取repositoryService
        RepositoryService repositoryService = engine.getRepositoryService();

        // 3. 使用service进行流程部署
        Deployment deploy = repositoryService.createDeployment().name("测试监听")
                .addClasspathResource("bpmn/testListener.bpmn")
                .deploy();

        System.out.println(String.format("流程部署ID: %s", deploy.getId()));
        System.out.println(String.format("流程部署名字: %s", deploy.getName()));


        // 4. 启动流程
        startProcess();
    }

    private void startProcess() {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        // 启动
        RuntimeService runtimeService = engine.getRuntimeService();

        runtimeService.startProcessInstanceByKey(TASK_KEY);
        System.out.println(engine.getName());

    }

    @Test
    public void completeTask() {
//        completeTask("zhangsanListener");
        completeTask("lisiListener");
    }

    private void completeTask(String assignee) {
        // 1. engine获取
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

        // 2. taskService获取
        TaskService taskService = engine.getTaskService();

        // 3. 根据任务负责人获取任务
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey(TASK_KEY)
                .taskAssignee(assignee).list();

        for (Task task : taskList) {
            // 完成个人任务，走到下一步
            taskService.complete(task.getId());
        }

    }

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
        List<ProcessDefinition> definitionList = processDefinitionQuery.processDefinitionKey(TASK_KEY).list();


        for (ProcessDefinition processDefinition : definitionList) {
            // 开启级联删除
            repositoryService.deleteDeployment(processDefinition.getDeploymentId(), true);
        }
    }

}
