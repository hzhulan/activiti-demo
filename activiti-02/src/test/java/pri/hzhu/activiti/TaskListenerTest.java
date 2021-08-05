package pri.hzhu.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

public class TaskListenerTest {

    private static final String TASK_KEY = "myProcess_1";


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
