# 流程部署

## Java代码部署

```java
// 1. 创建processEngine
ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

// 2. 获取repositoryService
RepositoryService repositoryService = engine.getRepositoryService();

// 3. 使用service进行流程部署
Deployment deploy = repositoryService.createDeployment().name("出差申请流程")
        .addClasspathResource("bpmn/evection.bpmn")
        .addClasspathResource("bpmn/evection.png")
        .deploy();
```

## Zip包部署

```java
ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

RepositoryService repositoryService = engine.getRepositoryService();

try(ZipInputStream zis = new ZipInputStream(ZipDeployDemo.class.getClassLoader().getResourceAsStream("bpmn/evection.zip"))) {

    // 使用压缩包的流进行流程部署
    Deployment deploy = repositoryService.createDeployment().addZipInputStream(zis).deploy();
    System.out.println(String.format("流程ID: %s", deploy.getId()));
    System.out.println(String.format("流程名称: %s", deploy.getName()));

} catch (Exception e) {
    System.out.println(e);
}
```

