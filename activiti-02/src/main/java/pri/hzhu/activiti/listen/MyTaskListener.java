package pri.hzhu.activiti.listen;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class MyTaskListener implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("================ notify ================");
        if (delegateTask.getEventName().equals("create")) {
            if (delegateTask.getName().equals("发起申请")) {
                delegateTask.setAssignee("zhangsanListener");
                return;
            }
            if (delegateTask.getName().equals("领导审批")) {
                delegateTask.setAssignee("lisiListener");
                return;
            }
        }
    }
}
