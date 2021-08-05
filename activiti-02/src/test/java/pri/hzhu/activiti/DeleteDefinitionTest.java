package pri.hzhu.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.junit.Test;

import java.util.List;

public class DeleteDefinitionTest {

    private static final String TASK_KEY = "myProcess_1";

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

        runtimeService.startProcessInstanceByKey(Constant.LISTENER_KEY, "10086");
        System.out.println(engine.getName());

    }

}
