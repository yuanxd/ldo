package com.win.ldo.web.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.win.ldo.web.BaseController;

@Controller
@RequestMapping(value = "/system/menu")
public class MenuController extends BaseController {
	/**
	 * Ê×Ò³»­Ãæ
	 * 
	 * @return
	 */
	@RequestMapping(value = "")
	public String mainPage() {
		return "/system/menu";
	}
}
