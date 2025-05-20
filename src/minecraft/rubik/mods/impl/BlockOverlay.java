package rubik.mods.impl;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.AxisAlignedBB;
import rubik.ColorManager;
import rubik.mods.Mod;

public class BlockOverlay extends Mod {
	private boolean outline = true;
	private float outlineWidth = 2.0F;
	private ColorManager outlineColor = ColorManager.fromColor(Color.BLACK);
	private boolean outlineChroma = false;
	private boolean overlay = false;
	private ColorManager overlayColor = ColorManager.fromColor(Color.WHITE).setAlpha(50);
	private boolean overlayChroma = true;
	
	public BlockOverlay() {
		setOutline((boolean) getFromFile("outline", outline));
		setOutlineWidth((float) ((double) getFromFile("outlineWidth", outlineWidth)));
		setOutlineColor((int) ((long) getFromFile("outlineColor", outlineColor.getRGB())));
		setOutlineChroma((boolean) getFromFile("outlineChroma", outlineChroma));
		setOverlay((boolean) getFromFile("overlay", overlay));
		setOverlayColor((int) ((long) getFromFile("overlayColor", overlayColor.getRGB())));
		setOverlayChroma((boolean) getFromFile("overlayChroma", overlayChroma));
	}
    
    public static void drawSelectionOverlay(AxisAlignedBB axisAlignedBBIn, int red, int green, int blue, int alpha) {
    	Tessellator tessellator = Tessellator.getInstance();
    	WorldRenderer worldRenderer = tessellator.getWorldRenderer();

    	worldRenderer.begin(GL11.GL_TRIANGLE_STRIP, DefaultVertexFormats.POSITION_COLOR);
    	worldRenderer.pos(axisAlignedBBIn.minX, axisAlignedBBIn.minY, axisAlignedBBIn.minZ).color(red, green, blue, alpha).endVertex();
    	worldRenderer.pos(axisAlignedBBIn.maxX, axisAlignedBBIn.minY, axisAlignedBBIn.minZ).color(red, green, blue, alpha).endVertex();
    	worldRenderer.pos(axisAlignedBBIn.minX, axisAlignedBBIn.minY, axisAlignedBBIn.maxZ).color(red, green, blue, alpha).endVertex();
    	worldRenderer.pos(axisAlignedBBIn.maxX, axisAlignedBBIn.minY, axisAlignedBBIn.maxZ).color(red, green, blue, alpha).endVertex();
    	tessellator.draw();
    	
    	worldRenderer.begin(GL11.GL_TRIANGLE_STRIP, DefaultVertexFormats.POSITION_COLOR);
    	worldRenderer.pos(axisAlignedBBIn.minX, axisAlignedBBIn.maxY, axisAlignedBBIn.minZ).color(red, green, blue, alpha).endVertex();
    	worldRenderer.pos(axisAlignedBBIn.minX, axisAlignedBBIn.maxY, axisAlignedBBIn.maxZ).color(red, green, blue, alpha).endVertex();
    	worldRenderer.pos(axisAlignedBBIn.maxX, axisAlignedBBIn.maxY, axisAlignedBBIn.minZ).color(red, green, blue, alpha).endVertex();
    	worldRenderer.pos(axisAlignedBBIn.maxX, axisAlignedBBIn.maxY, axisAlignedBBIn.maxZ).color(red, green, blue, alpha).endVertex();
    	tessellator.draw();
    	
    	worldRenderer.begin(GL11.GL_TRIANGLE_STRIP, DefaultVertexFormats.POSITION_COLOR);
    	worldRenderer.pos(axisAlignedBBIn.minX, axisAlignedBBIn.minY, axisAlignedBBIn.minZ).color(red, green, blue, alpha).endVertex();
    	worldRenderer.pos(axisAlignedBBIn.minX, axisAlignedBBIn.maxY, axisAlignedBBIn.minZ).color(red, green, blue, alpha).endVertex();
    	worldRenderer.pos(axisAlignedBBIn.maxX, axisAlignedBBIn.minY, axisAlignedBBIn.minZ).color(red, green, blue, alpha).endVertex();
    	worldRenderer.pos(axisAlignedBBIn.maxX, axisAlignedBBIn.maxY, axisAlignedBBIn.minZ).color(red, green, blue, alpha).endVertex();
    	worldRenderer.pos(axisAlignedBBIn.maxX, axisAlignedBBIn.minY, axisAlignedBBIn.maxZ).color(red, green, blue, alpha).endVertex();
    	worldRenderer.pos(axisAlignedBBIn.maxX, axisAlignedBBIn.maxY, axisAlignedBBIn.maxZ).color(red, green, blue, alpha).endVertex();
    	worldRenderer.pos(axisAlignedBBIn.minX, axisAlignedBBIn.minY, axisAlignedBBIn.maxZ).color(red, green, blue, alpha).endVertex();
    	worldRenderer.pos(axisAlignedBBIn.minX, axisAlignedBBIn.maxY, axisAlignedBBIn.maxZ).color(red, green, blue, alpha).endVertex();
    	worldRenderer.pos(axisAlignedBBIn.minX, axisAlignedBBIn.minY, axisAlignedBBIn.minZ).color(red, green, blue, alpha).endVertex();
    	worldRenderer.pos(axisAlignedBBIn.minX, axisAlignedBBIn.maxY, axisAlignedBBIn.minZ).color(red, green, blue, alpha).endVertex();
    	tessellator.draw();
    }
	
	public void setOutline(boolean enabled) {
		this.outline = enabled;
		
		setToFile("outline", enabled);
	}
	
	public boolean isOutlineEnabled() {
		return outline;
	}
	
	public void setOutlineWidth(float width) {
		this.outlineWidth = width;
		
		setToFile("outlineWidth", width);
	}
	
	public float getOutlineWidth() {
		return outlineWidth;
	}
	
	public void setOutlineColor(int rgb) {
		this.outlineColor = ColorManager.fromRGB(rgb);
		
		setToFile("outlineColor", rgb);
	}
	
	public ColorManager getOutlineColor() {
		return outlineColor;
	}
	
	public void setOutlineChroma(boolean enabled) {
		this.outlineChroma = enabled;
		
		setToFile("outlineChroma", enabled);
	}
	
	public boolean isOutlineChromaEnabled() {
		return outlineChroma;
	}
	
	public void setOverlay(boolean enabled) {
		this.overlay = enabled;
		
		setToFile("overlay", enabled);
	}
	
	public boolean isOverlayEnabled() {
		return overlay;
	}
	
	public void setOverlayColor(int rgb) {
		this.overlayColor = ColorManager.fromRGB(rgb);
		
		setToFile("overlayColor", rgb);
	}
	
	public ColorManager getOverlayColor() {
		return overlayColor;
	}
	
	public void setOverlayChroma(boolean enabled) {
		this.overlayChroma = enabled;
		
		setToFile("overlayChroma", enabled);
	}
	
	public boolean isOverlayChromaEnabled() {
		return overlayChroma;
	}
}
