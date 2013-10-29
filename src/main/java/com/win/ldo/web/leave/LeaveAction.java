package com.win.ldo.web.leave;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.win.ldo.service.leave.LeaveService;

@Controller
@RequestMapping(value = "/leave")
public class LeaveAction {
	@Autowired
	private LeaveService leaveService;
	
	@RequestMapping(value = { "apply" })
	public String startProcess(String form) {
		System.err.println(form.toString());
		return "/oa/leave/leaveApply";
	}
}
