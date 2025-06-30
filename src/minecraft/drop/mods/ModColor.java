package drop.mods;

import java.awt.Color;

public class ModColor {
	private int red;
	private int green;
	private int blue;
	private int alpha;
	private boolean chroma;
	
	private ModColor(Color color, boolean chroma) {
		this(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha(), chroma);
	}
	
	private ModColor(int rgb, boolean chroma) {
		this((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, (rgb >> 0) & 0xFF, (rgb >> 24) & 0xFF, chroma);
	}
	
	private ModColor(int red, int green, int blue, boolean chroma) {
		this(red, green, blue, 255, chroma);
	}
	
	private ModColor(int red, int green, int blue, int alpha, boolean chroma) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
		this.chroma = chroma;
	}
	
	public static ModColor fromColor(Color color, boolean chroma) {
		return new ModColor(color, chroma);
	}
	
	public static ModColor fromRGB(int rgb, boolean chroma) {
		return new ModColor(rgb, chroma);
	}
	
	public static ModColor fromRGB(int red, int green, int blue, boolean chroma) {
		return new ModColor(red, green, blue, chroma);
	}
	
	public static ModColor fromRGB(int red, int green, int blue, int alpha, boolean chroma) {
		return new ModColor(red, green, blue, alpha, chroma);
	}
	
	public ModColor setRed(int red) {
		this.red = red;
		
		return this;
	}
	
	public int getRed() {
		return red;
	}
	
	public ModColor setGreen(int green) {
		this.green = green;
		
		return this;
	}
	
	public int getGreen() {
		return green;
	}
	
	public ModColor setBlue(int blue) {
		this.blue = blue;
		
		return this;
	}
	
	public int getBlue() {
		return blue;
	}
	
	public ModColor setAlpha(int alpha) {
		this.alpha = alpha;
		
		return this;
	}
	
	public int getAlpha() {
		return alpha;
	}
	
	public ModColor setRGB(int rgb) {
		this.red = (rgb >> 16) & 0xFF;
		this.green = (rgb >> 8) & 0xFF;
		this.blue = (rgb >> 0) & 0xFF;
		this.alpha = (rgb >> 24) & 0xFF;
		
		return this;
	}
	
	public int getRGB() {
		return getColor().getRGB();
	}
	
	public ModColor setChromaToggled(boolean toggled) {
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
