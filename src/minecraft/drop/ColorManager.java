package drop;

public class ColorManager {
	private int red;
	private int green;
	private int blue;
	private int alpha;
	
	public ColorManager(int red, int green, int blue, int alpha) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
	}
	
	public ColorManager(int red, int green, int blue) {
		this(red, green, blue, 255);
	}
	
	public ColorManager(int rgb) {
		this((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, (rgb >> 0) & 0xFF, (rgb >> 24) & 0xFF);
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
		return (alpha << 24) | (red << 16) | (green << 8) | (blue << 0);
	}
}
