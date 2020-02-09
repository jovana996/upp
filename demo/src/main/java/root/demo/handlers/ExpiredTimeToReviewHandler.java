package root.demo.handlers;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;

import root.demo.entities.Paper;


public class ExpiredTimeToReviewHandler implements ExecutionListener {

    @Autowired
    RuntimeService runtimeService;


    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {

      
        System.out.println("isteklo je vreme");
       /* class RunnableTask implements Runnable {
            ProcessEngineServices processEngineServices;
            String processInstanceId;
            String executionId;

            RunnableTask(ProcessEngineServices processEngineServices, String processInstanceId, String executionId) {
                this.processEngineServices = processEngineServices;
                this.processInstanceId = processInstanceId;
                this.executionId = executionId;
            }

            public void run() {
                while(processEngineServices.getTaskService().createTaskQuery().processInstanceId(processInstanceId).executionId(executionId).singleResult() == null) {
                    // do nothing, wait for task instantiation
                }
                // get the task
                Task task = processEngineServices.getTaskService().createTaskQuery().processInstanceId(processInstanceId).executionId(executionId).singleResult();
                // just make sure that task really exists
                if(task != null) {

                    System.out.println("assign to task!!!");
                   System.out.println(task.getAssignee());
                }
            }
        }

        // since the delegateExecution instance is destroyed after Execution Listener end,
        // at least these three objects below can be useful ;)
        Thread thread = new Thread(
                            new RunnableTask(
                                delegateExecution.getProcessEngineServices(),
                                delegateExecution.getProcessInstanceId(),
                                delegateExecution.getId()));
        thread.start();*/
    }
    }


