package pri.hzhu.activiti;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AssigneeTest {

    private static final String TASK_KEY = "evection";

    Map<String, Object> assignMap;

    @Test
    public void deploy() {
        // 1. 创建processEngine
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

        // 2. 获取repositoryService
        RepositoryService repositoryService = engine.getRepositoryService();

        // 3. 使用service进行流程部署
        Deployment deploy = repositoryService.createDeployment().name("出差申请流程")
                .addClasspathResource("bpmn/evection.bpmn")
                .addClasspathResource("bpmn/evection.png")
                .deploy();

        System.out.println(String.format("流程部署ID: %s", deploy.getId()));
        System.out.println(String.format("流程部署名字: %s", deploy.getName()));

    }

    @Test
    public void testAssignee() {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = engine.getRuntimeService();

        assignMap = new LinkedHashMap<>();
//        assignMap.put("assignee0", "zhangsan5");
        assignMap.put("assignee1", "lisi5");
        assignMap.put("assignee2", "wangwu5");

        runtimeService.startProcessInstanceByKey(TASK_KEY, assignMap);
        System.out.println(engine.getName());
    }

    @Test
    public void completeFlow() {

        // 迭代依次通过
        for (Map.Entry<String, Object> entry : assignMap.entrySet()) {
            testCompletePersonTaskList(String.valueOf(entry.getValue()));
        }
    }

    private void testCompletePersonTaskList(String assignee) {
        // 1. engine获取
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

        // 2. taskService获取
        TaskService taskService = engine.getTaskService();

        // 3. 根据任务负责人获取任务
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey(TASK_KEY)
                .taskAssignee(assignee).list();

        for (Task task : taskList) {

            System.out.println(String.format("流程定义ID: %s", task.getProcessDefinitionId()));
            System.out.println(String.format("流程实例ID: %s", task.getProcessInstanceId()));
            System.out.println(String.format("任务ID: %s", task.getId()));
            System.out.println(String.format("任务负责人: %s", task.getAssignee()));
            System.out.println(String.format("任务名称: %s", task.getName()));


            // 完成个人任务，走到下一步
            taskService.complete(task.getId());
        }

    }
}
