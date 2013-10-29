package com.win.ldo.service.impl.leave;

import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.win.ldo.dao.BaseDAO;
import com.win.ldo.dao.leave.LeaveDAO;
import com.win.ldo.entity.Leave;
import com.win.ldo.service.impl.BaseWorkflowServiceImpl;
import com.win.ldo.service.leave.LeaveService;

/**
 * Çë¼Ùservice
 * 
 * @author Ô¬Ïþ¶¬
 * 
 */
@Service("leaveService")
public class LeaveServiceImpl extends BaseWorkflowServiceImpl<Leave, String>
		implements LeaveService {
	@Autowired
	private LeaveDAO leaveDAO;

	@Override
	public BaseDAO<Leave, String> getBaseDAO() {
		return leaveDAO;
	}

	@Override
	public String getProcessDefinitionKey() {
		return "leaveProcess";
	}

	@Override
	public ProcessInstance create(Leave entity, Map<String, Object> variables,
			String user) {
		// ±£´æÇë¼Ù¼ÇÂ¼
		save(entity);
		return super.create(entity, variables, user);
	}

}
