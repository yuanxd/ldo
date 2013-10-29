package com.win.ldo.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * �������ù�����
 * 
 * @author Ԭ����
 * 
 */
@Component
public class WfUtils {
	/**
	 * ����������ʽregex�ָ��ַ���
	 * 
	 * @param str
	 *            ���ָ��ַ���
	 * @param regex
	 *            �ָ�������ʽ
	 * @return List<String>�ָ��ļ���
	 */
	public List<String> splitString(String str, String regex) {
		if (Utils.isEmpty(str)) {
			return new ArrayList<String>();
		}
		return Arrays.asList(str.split(regex));
	}
}
