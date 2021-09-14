package com.logictrue.activity.util;

import java.util.ArrayList;
import java.util.List;

import org.activiti.bpmn.model.ActivitiListener;
import org.activiti.bpmn.model.EndEvent;
import org.activiti.bpmn.model.ExclusiveGateway;
import org.activiti.bpmn.model.ImplementationType;
import org.activiti.bpmn.model.MultiInstanceLoopCharacteristics;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.delegate.TaskListener;


public class BpmnUitl {


    /**
     * 创建用户任务
     *
     * @param Assignee            处理人 ${one}
     * @param Id                  任务id
     * @param Name                任务名称
     * @param Data                审批人集合 OneList
     * @param Item                遍历参数 one
     * @param CompletionCondition 完成条件 ${nrOfCompletedInstances/nrOfInstances >=
     *                            OneIs}
     * @param Implementation      任务监听类 com.lz.ht.listener.OneNodeListener
     * @return
     */
    public static UserTask createUserTask(String Assignee, String Id, String Name, String Data, String Item,
                                          String CompletionCondition,String Implementation) {
        UserTask userTask = new UserTask();

        if (Data != null && Item != null && CompletionCondition != null) {
            // 多实例属性
            MultiInstanceLoopCharacteristics multiInstanceLoopCharacteristics = new MultiInstanceLoopCharacteristics();
            // 审批人集合参数
            multiInstanceLoopCharacteristics.setInputDataItem(Data);
            // 遍历参数
            multiInstanceLoopCharacteristics.setElementVariable(Item);
            // 完成条件 已完成数等于实例数
            multiInstanceLoopCharacteristics.setCompletionCondition(CompletionCondition);
            // 并行
            multiInstanceLoopCharacteristics.setSequential(false);
            // 设置多实例属性
            userTask.setLoopCharacteristics(multiInstanceLoopCharacteristics);
        }

        if (Implementation != null) {
            List<ActivitiListener> list = new ArrayList<ActivitiListener>();
            ActivitiListener listener = new ActivitiListener();
            listener.setEvent(TaskListener.EVENTNAME_CREATE);
            listener.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_CLASS);
            listener.setImplementation(Implementation);
            list.add(listener);
            //设置监听
            userTask.setTaskListeners(list);
        }

        userTask.setAssignee(Assignee);
        userTask.setId(Id);
        userTask.setName(Name);
        return userTask;
    }

    /**
     * 创建连线
     *
     * @param id                  连线id
     * @param name                连线名称
     * @param from                连接起点id
     * @param to                  连接终点id
     * @param ConditionExpression 条件表达式
     * @return
     */
    public static SequenceFlow creatSequenceFlow(String id, String name, String from, String to,
                                                 String ConditionExpression) {
        SequenceFlow sequenceFlow = new SequenceFlow();
        sequenceFlow.setId(id);
        if (name != null) {
            sequenceFlow.setName(name);
        }
        sequenceFlow.setSourceRef(from);
        sequenceFlow.setTargetRef(to);
        if (ConditionExpression != null) {
            sequenceFlow.setConditionExpression(ConditionExpression);
        }
        return sequenceFlow;
    }

    /**
     * 创建排他网关
     *
     * @param Id   网关id
     * @param Name 网关名称
     * @return
     */
    public static ExclusiveGateway creatExclusiveGateway(String Id, String Name) {
        ExclusiveGateway exclusiveGateway = new ExclusiveGateway();
        exclusiveGateway.setId(Id);
        exclusiveGateway.setName(Name);
        return exclusiveGateway;
    }

    public static StartEvent createStartEvent() {
        StartEvent startEvent = new StartEvent();
        startEvent.setId("start");
        return startEvent;
    }

    public static EndEvent createEndEvent() {
        EndEvent endEvent = new EndEvent();
        endEvent.setId("end");
        return endEvent;
    }





}
