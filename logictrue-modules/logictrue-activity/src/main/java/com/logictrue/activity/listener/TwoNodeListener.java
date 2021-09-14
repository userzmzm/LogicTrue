package com.logictrue.activity.listener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;


public class TwoNodeListener implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {
		Map<String, Object> variables = delegateTask.getVariables();
		List<String> assigneeList = new ArrayList<String>();
		String[] users = {};
		if (variables.get("TwoUsers")!=null) {
			users =variables.get("TwoUsers").toString().split(",");
		}
		for (int i = 0; i < users.length; i++) {
			assigneeList.add(users[i]);
		}
		delegateTask.setVariable("TwoList", assigneeList);
	}

}
