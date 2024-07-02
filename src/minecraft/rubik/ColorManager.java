package rubik;

import java.awt.Color;

public class ColorManager {
	private int red;
	private int green;
	private int blue;
	private int alpha;
	
	private ColorManager(Color color) {
		this.red = color.getRed();
		this.green = color.getGreen();
		this.blue = color.getBlue();
		this.alpha = color.getAlpha();
	}
	
	private ColorManager(int rgb) {
		this.red = (rgb >> 16) & 0xFF;
		this.green = (rgb >> 8) & 0xFF;
		this.blue = (rgb >> 0) & 0xFF;
		this.alpha = (rgb >> 24) & 0xFF;
	}
	
	public static ColorManager fromColor(Color color) {
		return new ColorManager(color);
	}
	
	public static ColorManager fromRGB(int rgb) {
		return new ColorManager(rgb);
	}
	
	public ColorManager setRed(int red) {
		this.red = red;
		
		return this;
	}
	
	public int getRed() {
		return red;
	}
	
	public ColorManager setGreen(int green) {
		this.green = green;
		
		return this;
	}
	
	public int getGreen() {
		return green;
	}
	
	public ColorManager setBlue(int blue) {
		this.blue = blue;
		
		return this;
	}
	
	public int getBlue() {
		return blue;
	}
	
	public ColorManager setAlpha(int alpha) {
		this.alpha = alpha;
		
		return this;
	}
	
	public int getAlpha() {
		return alpha;
	}
	
	public Color getColor() {
		return new Color(red, green, blue, alpha);
	}
	
	public int getRGB() {
		return getColor().getRGB();
	}
}
