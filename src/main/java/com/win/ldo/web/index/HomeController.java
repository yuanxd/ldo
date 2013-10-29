package com.win.ldo.web.index;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.win.ldo.common.GlobalData;
import com.win.ldo.entity.Leave;
import com.win.ldo.entity.identity.Role;
import com.win.ldo.entity.identity.User;
import com.win.ldo.service.identity.RoleService;
import com.win.ldo.service.index.MainService;

/**
 * Ê×Ò³Controller
 * 
 * @author Ô¬Ïþ¶¬
 * 
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController {
	// @Autowired
	// private MainService testService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private MainService mainService;

	@RequestMapping(value = "start", method = RequestMethod.POST)
	public String start(Leave test, RedirectAttributes redirectAttributes,
			HttpSession session) {
		Map<String, Object> variables = new HashMap<String, Object>();
		Role role = roleService.getByName("leader");
		variables.put("approveRole", role.getId());
		User user = (User) session.getAttribute(GlobalData.USER_SESSION_KEY);
		mainService.create(test, variables, user.getId());
		return "/index";
	}

	/**
	 * Ê×Ò³»­Ãæ
	 * 
	 * @return
	 */
	@RequestMapping(value = "")
	public String home() {
		return "/home";
	}
}
