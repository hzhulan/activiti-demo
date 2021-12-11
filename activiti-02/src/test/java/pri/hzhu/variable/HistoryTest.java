package pri.hzhu.variable;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.junit.Test;

import java.util.List;

/**
 * @description: 历史任务
 * @author: pp_lan
 * @date: 2021/8/7 10:44
 */
public class HistoryTest {

    @Test
    public void testQueryVariables() {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        HistoryService historyService = engine.getHistoryService();


        HistoricTaskInstanceQuery taskInstanceQuery = historyService.createHistoricTaskInstanceQuery();
        HistoricTaskInstanceQuery historicTaskInstanceQuery = taskInstanceQuery.includeTaskLocalVariables();
        List<HistoricTaskInstance> list = historicTaskInstanceQuery.list();
        for (HistoricTaskInstance historicTaskInstance : list) {
            System.out.println("任务local变量" + historicTaskInstance.getTaskLocalVariables());
        }
    }
}
