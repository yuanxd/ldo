package com.win.ldo.service.index;

import com.win.ldo.entity.Leave;
import com.win.ldo.service.BaseService;
import com.win.ldo.service.BaseWorkflowService;

public interface MainService extends BaseService<Leave, String>,
		BaseWorkflowService<Leave, String> {
}
