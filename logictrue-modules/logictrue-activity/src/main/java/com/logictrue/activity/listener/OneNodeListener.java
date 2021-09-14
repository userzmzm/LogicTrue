package com.logictrue.activity.listener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;


public class OneNodeListener implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {
		Map<String, Object> variables = delegateTask.getVariables();
		List<String> assigneeList = new ArrayList<String>();
		String[] users = variables.get("OneUsers").toString().split(",");
		for (int i = 0; i < users.length; i++) {
			assigneeList.add(users[i]);
		}
		delegateTask.setVariable("OneList", assigneeList);
	}

}
