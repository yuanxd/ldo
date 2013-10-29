package com.win.ldo.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * 工作流用工具类
 * 
 * @author 袁晓冬
 * 
 */
@Component
public class WfUtils {
	/**
	 * 根据正则表达式regex分割字符串
	 * 
	 * @param str
	 *            待分割字符串
	 * @param regex
	 *            分割正则表达式
	 * @return List<String>分割后的集合
	 */
	public List<String> splitString(String str, String regex) {
		if (Utils.isEmpty(str)) {
			return new ArrayList<String>();
		}
		return Arrays.asList(str.split(regex));
	}
}
