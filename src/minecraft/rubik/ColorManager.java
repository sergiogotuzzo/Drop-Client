package rubik;

import java.awt.Color;

public class ColorManager {
	private int red;
	private int green;
	private int blue;
	private int alpha;
	
	public ColorManager(Color color) {
		this.red = color.getRed();
		this.green = color.getGreen();
		this.blue = color.getBlue();
		this.alpha = color.getAlpha();
	}
	
	public void setRed(int red) {
		this.red = red;
	}
	
	public void setGreen(int green) {
		this.green = green;
	}
	
	public void setBlue(int blue) {
		this.blue = blue;
	}
	
	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}
	
	public int getRed() {
		return red;
	}
	
	public int getGreen() {
		return green;
	}
	
	public int getBlue() {
		return blue;
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
