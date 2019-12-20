package com.yu.service;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: TestService
 * @author: yu.liu
 * @date: 2019/8/15 13:49
 * @description:
 */
@Service
public class TestService {

    /**
     * 获取工作流引擎
     * @return
     */
    public ProcessEngine getEngine(){
        return ProcessEngines.getDefaultProcessEngine();
    }

    /**
     * 部署工作流
     */
    public void deploy() {
        RepositoryService repositoryService = getEngine().getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("ceshi.bpmn")
                .name("请假单流程")
                .category("办公室类别")
                .deploy();

        System.out.println(deploy.getId());
        System.out.println(deploy.getName());
    }

    /**
     * 执行工作流
     */
    public void startProcess(){
        String  processDefiKey = "myProcess_1";
        RuntimeService runtimeService =getEngine().getRuntimeService();
        //取得流程实例
        ProcessInstance pi = runtimeService.startProcessInstanceById(processDefiKey);
        System.out.println("流程实例id:"+pi.getId());
        System.out.println("流程定义id:"+pi.getProcessDefinitionId());
    }

    /**
     * 根据代理人查询当前任务信息
     */
    public void queryTask(){
        //任务的办理人
        String assignee="qingj";
        //取得任务服务
        TaskService taskService = getEngine().getTaskService();
        //创建一个任务查询对象
        TaskQuery taskQuery = taskService.createTaskQuery();
        //办理人的任务列表
        List<Task> list = taskQuery.taskAssignee(assignee)//指定办理人
                .list();
        //遍历任务列表
        if(list!=null&&list.size()>0){
            for(Task task:list){
                System.out.println("任务的办理人："+task.getAssignee());
                System.out.println("任务的id："+task.getId());
                System.out.println("任务的名称："+task.getName());
            }
        }
    }

    /**
     * 处理任务
     */
    public void compileTask(){
        String taskId="304";
        //taskId：任务id
        getEngine().getTaskService().complete(taskId);
        System.out.println("当前任务执行完毕");
    }

}
