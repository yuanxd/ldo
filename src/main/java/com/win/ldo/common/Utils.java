package com.win.ldo.common;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.win.ldo.entity.identity.User;

public class Utils {
	/**
	 * ��Session��ȡ��ǰ�û���Ϣ
	 * 
	 * @param session
	 * @return
	 */
	public static User getUserFromSession(HttpSession session) {
		Object attribute = session.getAttribute(GlobalData.USER_SESSION_KEY);
		return attribute == null ? null : (User) attribute;
	}

	/**
	 * �ж��ַ����Ƿ�Ϊ��
	 * 
	 * @param s
	 *            s
	 * @return boolean
	 */
	public static boolean isEmpty(String s) {
		if (null != s && !"".equals(s.trim()) && !"null".equals(s.trim())) {
			return false;
		}
		return true;
	}

	/**
	 * ���ö�������
	 * 
	 * @param obj
	 * @param params
	 * @param name
	 * @param clazz
	 */
	public static void setField(Object obj, Object params, String name,
			Class<?> clazz) {
		try {
			Field fld = clazz.getDeclaredField(name);
			int mod = fld.getModifiers();
			if (null == obj && Modifier.isStatic(mod)) {
				if (!Modifier.isFinal(mod)) {
					if (!fld.isAccessible())
						fld.setAccessible(true);
					fld.set(null, params);
				}
			} else {
				if (!fld.isAccessible())
					fld.setAccessible(true);
				fld.set(obj, params);
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ������Ӧ������
	 * 
	 * @param httpResp
	 */
	public static void setNoCacheHeader(HttpServletResponse httpResp) {
		// Set standard HTTP/1.1 no-cache headers.
		// HTTP 1.1 header: "no-cache" is the standard value,
		// "no-store" is necessary to prevent caching on FireFox.
		httpResp.setHeader("Cache-Control", "no-cache, no-store");
		// Set standard HTTP/1.0 no-cache header.
		httpResp.setHeader("Pragma", "no-cache");
		// Set to expire far in the past. Prevents caching at the proxy server
		httpResp.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
	}

	/**
	 * �ж������Ƿ�Ϊajax
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		String requestType = request.getHeader("X-Requested-With");
		return "XMLHttpRequest".equals(requestType);
	}
}
