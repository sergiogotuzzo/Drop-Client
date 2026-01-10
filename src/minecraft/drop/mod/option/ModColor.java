package drop.mod.option;

import java.awt.Color;

import drop.ColorManager;

public class ModColor extends ColorManager {
	private boolean chroma;
	
	private ModColor(int red, int green, int blue, int alpha, boolean chroma) {
		super(red, green, blue, alpha);

		this.chroma = chroma;
	}
	
	private ModColor(int red, int green, int blue, boolean chroma) {
		super(red, green, blue);

		this.chroma = chroma;
	}
	
	private ModColor(int rgb, boolean chroma) {
		super(rgb);

		this.chroma = chroma;
	}
	
	private ModColor(Color color, boolean chroma) {
		this(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha(), chroma);
	}
	
	public static ModColor fromRGB(int red, int green, int blue, int alpha, boolean chroma) {
		return new ModColor(red, green, blue, alpha, chroma);
	}
	
	public static ModColor fromRGB(int red, int green, int blue, boolean chroma) {
		return new ModColor(red, green, blue, chroma);
	}
	
	public static ModColor fromRGB(int rgb, boolean chroma) {
		return new ModColor(rgb, chroma);
	}
	
	public static ModColor fromColor(Color color, boolean chroma) {
		return new ModColor(color, chroma);
	}
	
	public ModColor setRed(int red) {
		return (ModColor) super.setRed(red);
	}
	
	public ModColor setGreen(int green) {
		return (ModColor) super.setGreen(green);
	}
	
	public ModColor setBlue(int blue) {
		return (ModColor) super.setBlue(blue);
	}
	
	public ModColor setAlpha(int alpha) {
		return (ModColor) super.setAlpha(alpha);
	}
	
	public ModColor setRGB(int rgb) {
		return (ModColor) super.setRGB(rgb);
	}
	
	public ModColor setChromaToggled(boolean toggled) {
		this.chroma = toggled;
		
		return this;
	}
	
	public boolean isChromaToggled() {
		return chroma;
	}
	
	public Color getColor() {
		return new Color(getRed(), getGreen(), getBlue(), getAlpha());
	}
}
