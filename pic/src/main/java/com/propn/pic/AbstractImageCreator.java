package com.propn.pic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

/**
 * 模板方法模式应用：图片生成器
 */
public abstract class AbstractImageCreator {
	private static Random rnd = new Random(new Date().getTime());
	private Drawer drawer;

	// 图片宽度
	private int width = 200;

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	// 图片高度
	private int height = 80;

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	// 外框颜色
	private Color rectColor;

	// 背景色
	private Color bgColor;

	// 干扰线数目
	private int lineNum = 0;

	public int getLineNum() {
		return lineNum;
	}

	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}

	// 图片格式
	private String formatName = "JPEG";

	// 字体颜色
	private Color fontColor = new Color(0, 0, 0);

	// 字体名称
	private String fontName = "宋体";

	// 字体大小
	private int fontSize = 15;

	// 文字旋转的弧度数
	private double radian = 0;
	private double rotateX = 0;
	private double rotateY = 0;

	// 缩放
	private double scale = 1;

	// ##### 此处省略getter、setter方法 #####

	public AbstractImageCreator(Drawer drawer) {
		this.drawer = drawer;
	}

	/**
	 * 画干扰线
	 */
	private void drawRandomLine(Graphics graph) {
		for (int i = 0; i < lineNum; i++) {
			// 线条的颜色
			graph.setColor(getRandomColor(100, 155));

			// 线条两端坐标值
			int x1 = rnd.nextInt(width);
			int y1 = rnd.nextInt(height);

			int x2 = rnd.nextInt(width);
			int y2 = rnd.nextInt(height);

			// 画线条
			graph.drawLine(x1, y1, x2, y2);
		}
	}

	/**
	 * 随机获取颜色对象
	 */
	private Color getRandomColor(int base, int range) {
		if ((base + range) > 255)
			range = 255 - base;

		int red = base + rnd.nextInt(range);
		int green = base + rnd.nextInt(range);
		int blue = base + rnd.nextInt(range);

		return new Color(red, green, blue);
	}

	public void generateImage(String text) throws IOException {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		if (rectColor == null)
			rectColor = new Color(0, 0, 0);
		if (bgColor == null)
			bgColor = new Color(240, 251, 200);

		// 获取画布
		Graphics2D g = (Graphics2D) image.getGraphics();

		// 画长方形
		g.setColor(bgColor);
		g.fillRect(0, 0, width, height);

		// 外框
		g.setColor(rectColor);
		g.drawRect(0, 0, width - 1, height - 1);

		// 画干扰线
		drawRandomLine(g);

		// 画字符串
		drawer.draw(this, g, text);

		// 执行
		g.dispose();

		// 输出图片结果
		saveImage(image);
	}

	protected abstract void saveImage(BufferedImage image) throws IOException;

	public String getFormatName() {
		return formatName;
	}

	public void setFormatName(String formatName) {
		this.formatName = formatName;
	}

	public String getFontName() {
		return fontName;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	public Color getFontColor() {
		return fontColor;
	}

	public void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public double getRadian() {
		return radian;
	}

	public void setRadian(double radian) {
		this.radian = radian;
	}

	public double getRotateX() {
		return rotateX;
	}

	public void setRotateX(double rotateX) {
		this.rotateX = rotateX;
	}

	public double getRotateY() {
		return rotateY;
	}

	public void setRotateY(double rotateY) {
		this.rotateY = rotateY;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

}