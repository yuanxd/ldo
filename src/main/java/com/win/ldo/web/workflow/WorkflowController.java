package com.win.ldo.web.workflow;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.win.ldo.service.workflow.WorkflowService;

/**
 * 流程管理控制器<br>
 * 处理不区分具体流程定义的功能。如待办列表、流程定义列表、流程发布等。<br>
 * 与具体流程相关的功能放{@link WorkflowService}基类中定义
 * 
 * @author 袁晓冬
 */
@Controller
@RequestMapping(value = "/workflow")
public class WorkflowController {

//	@Autowired
//	private WorkflowService workflowService;
}
