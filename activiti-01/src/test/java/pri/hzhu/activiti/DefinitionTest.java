package pri.hzhu.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.junit.Test;

import java.util.List;

/**
 * 流程定义相关操作
 */
public class DefinitionTest {

    private static final String TASK_KEY = "myEvection";

    @Test
    public void queryProcessDefinition() {
        // 1. 获取repository
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = engine.getRepositoryService();

        // 2. 查询当前所有的流程定义
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        List<ProcessDefinition> definitionList = processDefinitionQuery.processDefinitionKey(TASK_KEY)
                .orderByProcessDefinitionVersion()
                .desc()
                .list();

        for (ProcessDefinition processDefinition : definitionList) {
            System.out.println(String.format("流程定义Id: %s", processDefinition.getId()));
            System.out.println(String.format("流程定义名称: %s", processDefinition.getName()));
            System.out.println(String.format("流程定义Key: %s", processDefinition.getKey()));
            System.out.println(String.format("流程定义版本: %s", processDefinition.getVersion()));
        }
    }


}
