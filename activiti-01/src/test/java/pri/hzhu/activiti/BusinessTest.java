package pri.hzhu.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

public class BusinessTest {

    private static final String TASK_KEY = "myEvection";

    @Test
    public void addBusinessKey() {
        // 1. 获取引擎
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

        // 2. 获取runtimeService
        RuntimeService runtimeService = engine.getRuntimeService();

        // 3. 启动流程，并添加businessKey
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(TASK_KEY, "1001");
//       runtimeService.startProcessInstanceByKey(TASK_KEY, "1002");

        System.out.println(processInstance.getBusinessKey());
        System.out.println(processInstance.getProcessDefinitionKey());
    }
}
