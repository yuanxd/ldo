package com.win.ldo.common;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * 安全认证过滤器
 * 
 * @author 袁晓冬
 * 
 */
public class LoginFilter implements Filter {
	private static final String LOGIN_URI = "login_uri";
	private static final String HOME_URI = "home_uri";

	/**
	 * 初始化
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		// 登录页面
		String login_page = filterConfig.getInitParameter(LOGIN_URI);
		// 首页
		String home_page = filterConfig.getInitParameter(HOME_URI);
		String contextPath = filterConfig.getServletContext().getContextPath();
		if (!login_page.startsWith(contextPath)) {
			login_page = contextPath + login_page;
		}
		if (!home_page.startsWith(contextPath)) {
			home_page = contextPath + home_page;
		}

		Assert.notNull(login_page, "login page not set!");
		if (StringUtils.hasLength(login_page)) {
			Utils.setField(null, login_page, "loginPage", GlobalData.class);
		}
		if (StringUtils.hasLength(home_page)) {
			Utils.setField(null, home_page, "homePage", GlobalData.class);
		}
	}

	/**
	 * 执行过滤
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if ((request instanceof HttpServletRequest)) {
			HttpServletRequest httpReq = (HttpServletRequest) request;
			HttpServletResponse httpResp = (HttpServletResponse) response;
			// 得到请求的uri
			String request_uri = httpReq.getRequestURI();
			// 设置缓存
			Utils.setNoCacheHeader(httpResp);
			// 如果访问了检查类的资源
			if (!GlobalData.UNCHECKURI_PATTERN.matcher(request_uri).matches()) {
				// 认证失败时跳转到登陆页面，并结束执行
				if (!this.checkSession(httpReq, httpResp)) {
					toLoginPage(httpReq, httpResp);
					return;
				}
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * 检查是否已登陆
	 * 
	 * @param httpReq
	 * @param httpResp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	private boolean checkSession(HttpServletRequest httpReq,
			HttpServletResponse httpResp) throws ServletException, IOException {
		return null != Utils.getUserFromSession(httpReq.getSession());
	}

	/**
	 * 把请求url保存到登陆页面，跳转至登陆页面
	 * 
	 * @param httpReq
	 * @param httpResp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void toLoginPage(HttpServletRequest httpReq,
			HttpServletResponse httpResp) throws ServletException, IOException {
		// 除去上下文路径剩余部分
		String request_uri = httpReq.getRequestURI();
		// 如果用户没有登录，则将用户的请求uri作为origin_uri属性的值保存到请求对象中
		String strQuery = httpReq.getQueryString();
		if (null != strQuery) {
			request_uri = request_uri + "?" + strQuery;
		}
		httpReq.setAttribute("originURI", request_uri);
		// 将用户请求转发给登录页面
		String url = GlobalData.getLoginPage() + "?originURI="
				+ URLEncoder.encode(request_uri, "gb2312");
		httpResp.sendRedirect(url);
	}

	public void destroy() {
	}

}
