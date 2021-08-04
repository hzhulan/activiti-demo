package pri.hzhu.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipDeployTest {

    private static final String TASK_KEY = "myEvection";

    @Test
    public void deployProcessByZip() {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

        RepositoryService repositoryService = engine.getRepositoryService();

        try(ZipInputStream zis = new ZipInputStream(ZipDeployTest.class.getClassLoader().getResourceAsStream("bpmn/evection.zip"))) {

            // 使用压缩包的流进行流程部署
            Deployment deploy = repositoryService.createDeployment().addZipInputStream(zis).name("请假流程").deploy();
            System.out.println(String.format("流程ID: %s", deploy.getId()));
            System.out.println(String.format("流程名称: %s", deploy.getName()));

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * 下载 资源文件
     */
    @Test
    public void getDeployment() {
        // 1. 查询deploymentId
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = engine.getRepositoryService();
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        List<ProcessDefinition> definitionList = processDefinitionQuery.processDefinitionKey(TASK_KEY).list();

        if (!CollectionUtils.isEmpty(definitionList)) {
            ProcessDefinition processDefinition = definitionList.get(0);
            // 2. 根据deploymentId获取资源
            try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream("evection.zip"));) {

                // 3. 获取png的输入流及文件路径
                String diagramResourceName = processDefinition.getDiagramResourceName();
                InputStream diagramResourceStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), diagramResourceName);
                writeToZip(zos, diagramResourceStream, diagramResourceName);

                // 4. 获取bpmn的输入流及文件路径
                String resourceName = processDefinition.getResourceName();
                InputStream resourceStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
                writeToZip(zos, resourceStream, resourceName);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    private String getFileName(String path) {
        String[] split = path.split("/");
        return split[split.length - 1];
    }

    private void writeToZip(ZipOutputStream zos, InputStream is, String path) throws IOException {
        zos.putNextEntry(new ZipEntry(getFileName(path)));
        int len;
        byte[] buffer = new byte[1024];
        while ((len = is.read(buffer)) != -1) {
            zos.write(buffer, 0, len);
        }
        zos.closeEntry();
    }
}
