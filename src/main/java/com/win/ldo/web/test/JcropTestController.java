package com.win.ldo.web.test;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.win.ldo.common.OperateImage;
import com.win.ldo.common.Utils;
import com.win.ldo.web.BaseController;

@Controller
@RequestMapping(value = "/test/jcrop")
public class JcropTestController extends BaseController {
	/**
	 * 首页画面
	 * 
	 * @return
	 */
	@RequestMapping(value = "")
	public String mainPage() {
		return "/test/testJcrop";
	}

	/**
	 * 返回裁剪后的图片
	 */
	@RequestMapping("crop")
	public void getCropImage(OperateImage omg, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String path = omg.getSrcpath();
		if (Utils.isEmpty(path)) {
			return;
		}
		if (path.startsWith(request.getContextPath())) {
			path = path.substring(request.getContextPath().length());
		}
		omg.setSrcpath(request.getSession().getServletContext()
				.getRealPath(path));
		OutputStream stream = response.getOutputStream();
		omg.cut(stream);
		stream.flush();
		stream.close();
	}
}
