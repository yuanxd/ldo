package com.win.ldo.service.impl.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.win.ldo.dao.BaseDAO;
import com.win.ldo.dao.index.TestDAO;
import com.win.ldo.entity.Leave;
import com.win.ldo.service.impl.BaseWorkflowServiceImpl;
import com.win.ldo.service.index.MainService;

@Component
public class MainServiceImpl extends BaseWorkflowServiceImpl<Leave, String>
		implements MainService {
	@Autowired
	private TestDAO testDAO;

	@Override
	public BaseDAO<Leave, String> getBaseDAO() {
		return testDAO;
	}

	@Override
	public String getProcessDefinitionKey() {
		return "TestProcess";
	}
}
