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
 * ��ȫ��֤������
 * 
 * @author Ԭ����
 * 
 */
public class LoginFilter implements Filter {
	private static final String LOGIN_URI = "login_uri";
	private static final String HOME_URI = "home_uri";

	/**
	 * ��ʼ��
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		// ��¼ҳ��
		String login_page = filterConfig.getInitParameter(LOGIN_URI);
		// ��ҳ
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
	 * ִ�й���
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if ((request instanceof HttpServletRequest)) {
			HttpServletRequest httpReq = (HttpServletRequest) request;
			HttpServletResponse httpResp = (HttpServletResponse) response;
			// �õ������uri
			String request_uri = httpReq.getRequestURI();
			// ���û���
			Utils.setNoCacheHeader(httpResp);
			// ��������˼�������Դ
			if (!GlobalData.UNCHECKURI_PATTERN.matcher(request_uri).matches()) {
				// ��֤ʧ��ʱ��ת����½ҳ�棬������ִ��
				if (!this.checkSession(httpReq, httpResp)) {
					toLoginPage(httpReq, httpResp);
					return;
				}
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * ����Ƿ��ѵ�½
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
	 * ������url���浽��½ҳ�棬��ת����½ҳ��
	 * 
	 * @param httpReq
	 * @param httpResp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void toLoginPage(HttpServletRequest httpReq,
			HttpServletResponse httpResp) throws ServletException, IOException {
		// ��ȥ������·��ʣ�ಿ��
		String request_uri = httpReq.getRequestURI();
		// ����û�û�е�¼�����û�������uri��Ϊorigin_uri���Ե�ֵ���浽���������
		String strQuery = httpReq.getQueryString();
		if (null != strQuery) {
			request_uri = request_uri + "?" + strQuery;
		}
		httpReq.setAttribute("originURI", request_uri);
		// ���û�����ת������¼ҳ��
		String url = GlobalData.getLoginPage() + "?originURI="
				+ URLEncoder.encode(request_uri, "gb2312");
		httpResp.sendRedirect(url);
	}

	public void destroy() {
	}

}
