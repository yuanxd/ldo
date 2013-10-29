package com.win.ldo.web.index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.activiti.engine.impl.util.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.win.ldo.common.GlobalData;
import com.win.ldo.common.Utils;
import com.win.ldo.entity.identity.Role;
import com.win.ldo.entity.identity.User;
import com.win.ldo.service.identity.RoleService;
import com.win.ldo.service.identity.UserService;
import com.win.ldo.web.BaseController;

/**
 * ��¼Controller
 * 
 * @author Ԭ����
 * 
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController extends BaseController {
	/** �û����� */
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	/**
	 * ��ʼ��ϵͳ����
	 * 
	 * @return
	 */
	@RequestMapping(value = "/init")
	public String init() {
		List<Role> initRoleList = new ArrayList<Role>();
		// ��ʼ����ɫ
		Role adminRole = new Role();
		adminRole.setName("admin");
		initRoleList.add(adminRole);

		Role userRole = new Role();
		userRole.setName("user");
		initRoleList.add(userRole);

		Role leaderRole = new Role();
		leaderRole.setName("leader");
		initRoleList.add(leaderRole);

		Role hrRole = new Role();
		hrRole.setName("hr");
		initRoleList.add(hrRole);
		roleService.save(initRoleList);

		// ��ʼ���û�
		List<User> initUserList = new ArrayList<User>();
		User user = new User();
		user.setName("dev");
		user.setPassword("dev");
		List<Role> userRoles = new ArrayList<Role>();
		userRoles.add(userRole);
		user.setRoles(userRoles);
		initUserList.add(user);

		User leader = new User();
		leader.setName("leader");
		leader.setPassword("leader");
		List<Role> leaderRoles = new ArrayList<Role>();
		leaderRoles.add(leaderRole);
		leader.setRoles(leaderRoles);
		initUserList.add(leader);

		User hr = new User();
		hr.setName("hr");
		hr.setPassword("hr");
		List<Role> hrRoles = new ArrayList<Role>();
		hrRoles.add(hrRole);
		hr.setRoles(hrRoles);
		initUserList.add(hr);

		User test = new User();
		test.setName("test");
		test.setPassword("test");
		test.setRoles(initRoleList);
		initUserList.add(test);

		userService.save(initUserList);
		return "/login";
	}

	/**
	 * ���ص�¼����
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("code", "yxd");
		model.addAttribute("password", "yxd");
		return "/login";
	}
	
	/**
	 * ��¼��֤
	 * 
	 * @param userTemp
	 *            ��¼�û�
	 * @param session
	 *            HttpSession
	 * @return
	 * @throws JSONException
	 */
	@RequestMapping(value = "/doLogin")
	@ResponseBody
	public Map<String, Object> doLogin(User userTemp,@RequestParam String originURI, HttpSession session) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		if(Utils.isEmpty(originURI)) {
			originURI = GlobalData.getHomePage();
		}
		resMap.put("originURI", originURI);
		User user = userService.getByCode(userTemp.getCode());
		if (null != user) {
			if (user.getPassword().equals(userTemp.getPassword())) {
				session.setAttribute(GlobalData.USER_SESSION_KEY, user);
				resMap.put("success", true);
				return resMap;
			}
		}
		resMap.put("success", false);
		return resMap;
	}
}
