package pri.hzhu.activiti;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class TaskListenerWithBusinessKeyTest {

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

        runtimeService.startProcessInstanceByKey(Constant.LISTENER_KEY);
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
                .processDefinitionKey(Constant.LISTENER_KEY)
                .taskAssignee(assignee).list();

        for (Task task : taskList) {
            // 完成个人任务，走到下一步
            taskService.complete(task.getId());
        }

    }

    @Test
    public void findProcessInstance() {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

        TaskService taskService = engine.getTaskService();
        RuntimeService runtimeService = engine.getRuntimeService();

        Task task = taskService.createTaskQuery().processDefinitionKey(Constant.LISTENER_KEY).taskAssignee("zhangsanListener").singleResult();
        String processInstanceId = task.getProcessInstanceId();
        ProcessInstance instance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();

        String businessKey = instance.getBusinessKey();
        System.out.println(String.format("businessKey: %s.", businessKey));
    }

}
