# 依赖

## maven

### 版本

```xml
<properties>
    <maven.compiler.source>8</maven.compiler.source>
    <maven.compiler.target>8</maven.compiler.target>
    <slf4j.version>1.6.6</slf4j.version>
    <log4j.version>1.2.12</log4j.version>
    <activiti.version>7.0.0.Beta1</activiti.version>
</properties>
```

### 核心依赖

```xml

<dependency>
    <groupId>org.activiti</groupId>
    <artifactId>activiti-engine</artifactId>
    <version>${activiti.version}</version>
</dependency>
<dependency>
    <groupId>org.activiti</groupId>
    <artifactId>activiti-spring</artifactId>
    <version>${activiti.version}</version>
</dependency>
<!-- bpmn 模型处理 -->
<dependency>
    <groupId>org.activiti</groupId>
    <artifactId>activiti-bpmn-model</artifactId>
    <version>${activiti.version}</version>
</dependency>
<!-- bpmn 转换 -->
<dependency>
    <groupId>org.activiti</groupId>
    <artifactId>activiti-bpmn-converter</artifactId>
    <version>${activiti.version}</version>
</dependency>
<!-- bpmn json数据转换 -->
<dependency>
    <groupId>org.activiti</groupId>
    <artifactId>activiti-json-converter</artifactId>
    <version>${activiti.version}</version>
</dependency>
<!-- bpmn 布局 -->
<dependency>
    <groupId>org.activiti</groupId>
    <artifactId>activiti-bpmn-layout</artifactId>
    <version>${activiti.version}</version>
</dependency>
<!-- activiti 云支持 -->
<dependency>
    <groupId>org.activiti.cloud</groupId>
    <artifactId>activiti-cloud-services-api</artifactId>
    <version>${activiti.version}</version>
</dependency>
```

### 数据库相关

```xml
<!-- postgresql -->
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.2.23</version>
</dependency>

<!-- mybatis -->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.4.5</version>
</dependency>
<!-- 链接池 -->
<dependency>
    <groupId>commons-dbcp</groupId>
    <artifactId>commons-dbcp</artifactId>
    <version>1.4</version>
</dependency>
```

### 其他

```xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
</dependency>
<!-- log start -->
<dependency>
    <groupId>log4j</groupId>
    <artifactId>log4j</artifactId>
    <version>${log4j.version}</version>
</dependency>
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>${slf4j.version}</version>
</dependency>
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-log4j12</artifactId>
    <version>${slf4j.version}</version>
</dependency>
<dependency>
    <groupId>commons-io</groupId>
    <artifactId>commons-io</artifactId>
    <version>2.6</version>
</dependency>
```

## 插件

### actiBPM

高版本的idea已经无法使用actiBPM，为了方便暂时降低idea版本（2019.2）

使用图形化界面，可以生成如下.bpmn文件

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<definitions xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:activiti="http://activiti.org/bpmn" xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI" xmlns:dc="http://www.omg.org/spec/DD/20100524/DC" xmlns:di="http://www.omg.org/spec/DD/20100524/DI" xmlns:tns="http://www.activiti.org/testm1627960402945" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" expressionLanguage="http://www.w3.org/1999/XPath" id="m1627960402945" name="" targetNamespace="http://www.activiti.org/testm1627960402945" typeLanguage="http://www.w3.org/2001/XMLSchema">
  <process id="myEvection" isClosed="false" isExecutable="true" processType="None">
    <startEvent id="myStartEvent" name="StartEvent"/>
    <userTask activiti:assignee="zhangsan" activiti:exclusive="true" id="_3" name="创建出差申请"/>
    <userTask activiti:assignee="lisi" activiti:exclusive="true" id="_4" name="经理审批"/>
    <userTask activiti:assignee="wangwu" activiti:exclusive="true" id="_5" name="总经理审批"/>
    <userTask activiti:assignee="rose" activiti:exclusive="true" id="_6" name="财务审批"/>
    <sequenceFlow id="_7" sourceRef="myStartEvent" targetRef="_3"/>
    <sequenceFlow id="_2" sourceRef="_3" targetRef="_4"/>
    <sequenceFlow id="_8" sourceRef="_4" targetRef="_5"/>
    <sequenceFlow id="_9" sourceRef="_5" targetRef="_6"/>
  </process>
  <bpmndi:BPMNDiagram documentation="background=#3C3F41;count=1;horizontalcount=1;orientation=0;width=842.4;height=1195.2;imageableWidth=832.4;imageableHeight=1185.2;imageableX=5.0;imageableY=5.0" id="Diagram-_1" name="New Diagram">
    <bpmndi:BPMNPlane bpmnElement="myEvection">
      <bpmndi:BPMNShape bpmnElement="myStartEvent" id="Shape-myStartEvent">
        <dc:Bounds height="32.0" width="32.0" x="370.0" y="50.0"/>
        <bpmndi:BPMNLabel>
          <dc:Bounds height="32.0" width="32.0" x="0.0" y="0.0"/>
        </bpmndi:BPMNLabel>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="_3" id="Shape-_3">
        <dc:Bounds height="55.0" width="85.0" x="350.0" y="140.0"/>
        <bpmndi:BPMNLabel>
          <dc:Bounds height="55.0" width="85.0" x="0.0" y="0.0"/>
        </bpmndi:BPMNLabel>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="_4" id="Shape-_4">
        <dc:Bounds height="55.0" width="85.0" x="355.0" y="245.0"/>
        <bpmndi:BPMNLabel>
          <dc:Bounds height="55.0" width="85.0" x="0.0" y="0.0"/>
        </bpmndi:BPMNLabel>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="_5" id="Shape-_5">
        <dc:Bounds height="55.0" width="85.0" x="355.0" y="345.0"/>
        <bpmndi:BPMNLabel>
          <dc:Bounds height="55.0" width="85.0" x="0.0" y="0.0"/>
        </bpmndi:BPMNLabel>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="_6" id="Shape-_6">
        <dc:Bounds height="55.0" width="85.0" x="360.0" y="440.0"/>
        <bpmndi:BPMNLabel>
          <dc:Bounds height="55.0" width="85.0" x="0.0" y="0.0"/>
        </bpmndi:BPMNLabel>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNEdge bpmnElement="_2" id="BPMNEdge__2" sourceElement="_3" targetElement="_4">
        <di:waypoint x="395.0" y="195.0"/>
        <di:waypoint x="395.0" y="245.0"/>
        <bpmndi:BPMNLabel>
          <dc:Bounds height="0.0" width="0.0" x="0.0" y="0.0"/>
        </bpmndi:BPMNLabel>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="_7" id="BPMNEdge__7" sourceElement="myEvection" targetElement="_3">
        <di:waypoint x="386.0" y="82.0"/>
        <di:waypoint x="386.0" y="140.0"/>
        <bpmndi:BPMNLabel>
          <dc:Bounds height="0.0" width="0.0" x="0.0" y="0.0"/>
        </bpmndi:BPMNLabel>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="_8" id="BPMNEdge__8" sourceElement="_4" targetElement="_5">
        <di:waypoint x="397.5" y="300.0"/>
        <di:waypoint x="397.5" y="345.0"/>
        <bpmndi:BPMNLabel>
          <dc:Bounds height="0.0" width="0.0" x="0.0" y="0.0"/>
        </bpmndi:BPMNLabel>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="_9" id="BPMNEdge__9" sourceElement="_5" targetElement="_6">
        <di:waypoint x="400.0" y="400.0"/>
        <di:waypoint x="400.0" y="440.0"/>
        <bpmndi:BPMNLabel>
          <dc:Bounds height="0.0" width="0.0" x="0.0" y="0.0"/>
        </bpmndi:BPMNLabel>
      </bpmndi:BPMNEdge>
    </bpmndi:BPMNPlane>
  </bpmndi:BPMNDiagram>
</definitions>
```


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

## Task

```java
@Test
public void testFindPersonTaskList() {
    // 1. engine获取
    ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

    // 2. taskService获取
    TaskService taskService = engine.getTaskService();

    // 3. 根据任务负责人获取任务
    List<Task> taskList = taskService.createTaskQuery()
            .processDefinitionKey(TASK_KEY)
            .taskAssignee("rose").list();

    for (Task task : taskList) {

        System.out.println(String.format("流程实例ID: %s", task.getProcessDefinitionId()));
        System.out.println(String.format("任务ID: %s", task.getId()));
        System.out.println(String.format("任务负责人: %s", task.getAssignee()));
        System.out.println(String.format("任务名称: %s", task.getName()));

        // 完成个人任务，走到下一步
        taskService.complete(task.getId());
    }

}
```

