package com.win.ldo.common;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 * �ü�ͼƬ
 * 
 * @author Ԭ����
 * 
 */
public class OperateImage {

	// ===ԴͼƬ·��������:c:/1.jpg
	private String srcpath;

	// ===����ͼƬ���·������.��:c:/2.jpg
	private String subpath;

	// ===���е�x����
	private int x;

	private int y;

	// ===���е���
	private int w;

	private int h;

	public OperateImage() {

	}

	public OperateImage(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.w = width;
		this.h = height;
	}

	/**
	 * ��ͼƬ�ü������Ѳü��군��ͼƬ���� ��
	 */
	public void cut(OutputStream stream) throws IOException {

		FileInputStream is = null;
		ImageInputStream iis = null;

		try {
			// ��ȡͼƬ�ļ�
			is = new FileInputStream(srcpath);

			/*
			 * ���ذ������е�ǰ��ע�� ImageReader �� Iterator����Щ ImageReader �����ܹ�����ָ����ʽ��
			 * ������formatName - ��������ʽ��ʽ���� .������ "jpeg" �� "tiff"���� ��
			 */
			Iterator<ImageReader> it = ImageIO
					.getImageReadersByFormatName("jpg");
			ImageReader reader = it.next();
			// ��ȡͼƬ��
			iis = ImageIO.createImageInputStream(is);

			/*
			 * <p>iis:��ȡԴ.true:ֻ��ǰ���� </p>.�������Ϊ ��ֻ��ǰ��������
			 * ��������ζ�Ű���������Դ�е�ͼ��ֻ��˳���ȡ���������� reader ���⻺���������ǰ�Ѿ���ȡ��ͼ����������ݵ���Щ���벿�֡�
			 */
			reader.setInput(iis, true);

			/*
			 * <p>������ζ������н������<p>.����ָ�����������ʱ�� Java Image I/O
			 * ��ܵ��������е���ת��һ��ͼ���һ��ͼ�������ض�ͼ���ʽ�Ĳ�� ������ ImageReader ʵ�ֵ�
			 * getDefaultReadParam �����з��� ImageReadParam ��ʵ����
			 */
			ImageReadParam param = reader.getDefaultReadParam();

			/*
			 * ͼƬ�ü�����Rectangle ָ��������ռ��е�һ������ͨ�� Rectangle ����
			 * �����϶�������꣨x��y������Ⱥ͸߶ȿ��Զ����������
			 */
			Rectangle rect = new Rectangle(x, y, w, h);

			// �ṩһ�� BufferedImage���������������������ݵ�Ŀ�ꡣ
			param.setSourceRegion(rect);

			/*
			 * ʹ�����ṩ�� ImageReadParam ��ȡͨ������ imageIndex ָ���Ķ��󣬲��� ����Ϊһ��������
			 * BufferedImage ���ء�
			 */
			BufferedImage bi = reader.read(0, param);
			// ������ͼƬ
			ImageIO.write(bi, "jpg", stream);
		}

		finally {
			if (is != null)
				is.close();
			if (iis != null)
				iis.close();
		}

	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getHeight() {
		return h;
	}

	public void setHeight(int height) {
		this.w = height;
	}


	public String getSrcpath() {
		return srcpath;
	}

	public void setSrcpath(String srcpath) {
		this.srcpath = srcpath;
	}

	public String getSubpath() {
		return subpath;
	}

	public void setSubpath(String subpath) {
		this.subpath = subpath;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}