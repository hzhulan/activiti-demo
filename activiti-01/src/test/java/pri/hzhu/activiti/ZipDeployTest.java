package pri.hzhu.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

import java.util.zip.ZipInputStream;

public class ZipDeployTest {

    @Test
    public void deployProcessByZip() {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

        RepositoryService repositoryService = engine.getRepositoryService();

        try(ZipInputStream zis = new ZipInputStream(ZipDeployTest.class.getClassLoader().getResourceAsStream("bpmn/evection.zip"))) {

            // 使用压缩包的流进行流程部署
            Deployment deploy = repositoryService.createDeployment().addZipInputStream(zis).deploy();
            System.out.println(String.format("流程ID: %s", deploy.getId()));
            System.out.println(String.format("流程名称: %s", deploy.getName()));

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
