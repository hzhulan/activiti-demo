package pri.hzhu.activiti;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

public class ActivitiTaskTest {

    private static final String TASK_KEY = "approveHolidays";

    @Test
    public void testDeploy() {
        // 1. 创建processEngine
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

        // 2. 获取repositoryService
        RepositoryService repositoryService = engine.getRepositoryService();

        // 3. 使用service进行流程部署
        Deployment deploy = repositoryService.createDeployment().name("出差申请流程")
                .addClasspathResource("bpmn/qingjia.bpmn")
                .addClasspathResource("bpmn/qingjia.png")
                .deploy();

        System.out.println(String.format("流程部署ID: %s", deploy.getId()));
        System.out.println(String.format("流程部署名字: %s", deploy.getName()));

    }

    /**
     * 启动流程实例，使用RuntimeService
     */
    @Test
    public void testStartProcess() {
        // 1. engine获取
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

        // 2. runtimeService获取
        RuntimeService runtimeService = engine.getRuntimeService();

        // 3. 根据流程id(key)启动
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(TASK_KEY);

        System.out.println(String.format("流程定义Id: %s", instance.getProcessDefinitionId()));
        System.out.println(String.format("流程实例Id: %s", instance.getId()));
        System.out.println(String.format("当前活动的Id: %s", instance.getActivityId()));

    }

    @Test
    public void completeFlow() {
        testCompletePersonTaskList("approve");
//        testCompletePersonTaskList("lisi");
//        testCompletePersonTaskList("wangwu");
//        testCompletePersonTaskList("rose");
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

    @Test
    public void findHistory() {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        HistoryService historyService = engine.getHistoryService();

        HistoricActivityInstanceQuery instanceQuery = historyService.createHistoricActivityInstanceQuery();
//        instanceQuery.processInstanceId("17501");
        instanceQuery.processDefinitionId("approveHolidays:1:4");
        instanceQuery.orderByHistoricActivityInstanceStartTime().asc();
        System.out.println("=============================================");
        List<HistoricActivityInstance> list = instanceQuery.list();
        for (HistoricActivityInstance historicActivityInstance : list) {
            System.out.println("" + historicActivityInstance.getActivityId());
            System.out.println("" + historicActivityInstance.getActivityName());
            System.out.println("" + historicActivityInstance.getProcessDefinitionId());
            System.out.println("=============================================");
        }

    }

}
