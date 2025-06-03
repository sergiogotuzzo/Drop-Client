package drop;

import java.awt.Color;

public class ColorManager {
	private int red;
	private int green;
	private int blue;
	private int alpha;
	private boolean chroma;
	
	private ColorManager(Color color, boolean chroma) {
		this.red = color.getRed();
		this.green = color.getGreen();
		this.blue = color.getBlue();
		this.alpha = color.getAlpha();
		this.chroma = chroma;
	}
	
	private ColorManager(int rgb, boolean chroma) {
		this.red = (rgb >> 16) & 0xFF;
		this.green = (rgb >> 8) & 0xFF;
		this.blue = (rgb >> 0) & 0xFF;
		this.alpha = (rgb >> 24) & 0xFF;
		this.chroma = chroma;
	}
	
	public static ColorManager fromColor(Color color, boolean chroma) {
		return new ColorManager(color, chroma);
	}
	
	public static ColorManager fromRGB(int rgb, boolean chroma) {
		return new ColorManager(rgb, chroma);
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
	
	public ColorManager setRGB(int rgb) {
		this.red = (rgb >> 16) & 0xFF;
		this.green = (rgb >> 8) & 0xFF;
		this.blue = (rgb >> 0) & 0xFF;
		this.alpha = (rgb >> 24) & 0xFF;
		
		return this;
	}
	
	public int getRGB() {
		return getColor().getRGB();
	}
	
	public ColorManager setChromaToggled(boolean toggled) {
		this.chroma = toggled;
		
		return this;
	}
	
	public boolean isChromaToggled() {
		return chroma;
	}
	
	public Color getColor() {
		return new Color(red, green, blue, alpha);
	}
}
