package rubik.mods.impl;

import java.awt.Color;

import net.minecraft.client.gui.Gui;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.chunk.Chunk;
import rubik.ColorManager;
import rubik.gui.hud.ScreenPosition;
import rubik.mods.ModDraggable;

public class CoordinatesDisplay extends ModDraggable {
	private boolean showBackground = true;
	private boolean textShadow = true;
	private ColorManager color = new ColorManager(Color.WHITE);
	private boolean showBiome = true;
	private boolean showTowards = true;
	
	private final int padding = 12;
	
	@Override
	public int getWidth() {
		return showBiome ? font.getStringWidth(getBiomeText()) + padding : 70;
	}

	@Override
	public int getHeight() {
		return (showBiome ? 38 : 28) + padding;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (showBackground) {
			Gui.drawRect(
					pos.getAbsoluteX(),
					pos.getAbsoluteY(),
					pos.getAbsoluteX() + getWidth(),
					pos.getAbsoluteY() + getHeight(),
					new Color(0, 0, 0, 102).getRGB()
					);
		}
		
		final int i = showBiome ? padding / 4 : padding / 3;
		final int j = padding / 2;
		
		if (textShadow) {
			font.drawStringWithShadow(getCoordinatesXText(), pos.getAbsoluteX() + j, pos.getAbsoluteY() + getLineY(1) + i, color.getRGB());
			font.drawStringWithShadow(getCoordinatesYText(), pos.getAbsoluteX() + j, pos.getAbsoluteY() + getLineY(2) + i, color.getRGB());
			font.drawStringWithShadow(getCoordinatesZText(), pos.getAbsoluteX() + j, pos.getAbsoluteY() + getLineY(3) + i, color.getRGB());
			
			if (showTowards) {
				font.drawStringWithShadow(getFacingTowardsX(), pos.getAbsoluteX() + getWidth() - padding, pos.getAbsoluteY() + getLineY(1) + i, color.getRGB());
				font.drawStringWithShadow(getFacing(), pos.getAbsoluteX() + getWidth() - padding, pos.getAbsoluteY() + getLineY(2) + i, color.getRGB());
				font.drawStringWithShadow(getFacingTowardsZ(), pos.getAbsoluteX() + getWidth() - padding, pos.getAbsoluteY() + getLineY(3) + i, color.getRGB());
			}
			
			if (showBiome) {
				font.drawStringWithShadow(getBiomeText(), pos.getAbsoluteX() + j, pos.getAbsoluteY() + getLineY(4) + i, color.getRGB());
			}
		} else {
			font.drawString(getCoordinatesXText(), pos.getAbsoluteX() + j, pos.getAbsoluteY() + getLineY(1) + i, color.getRGB());
			font.drawString(getCoordinatesYText(), pos.getAbsoluteX() + j, pos.getAbsoluteY() + getLineY(2) + i, color.getRGB());
			font.drawString(getCoordinatesZText(), pos.getAbsoluteX() + j, pos.getAbsoluteY() + getLineY(3) + i, color.getRGB());
			
			if (showTowards) {
				font.drawString(getFacingTowardsX(), pos.getAbsoluteX() + getWidth() - padding, pos.getAbsoluteY() + getLineY(1) + i, color.getRGB());
				font.drawString(getFacing(), pos.getAbsoluteX() + getWidth() - padding, pos.getAbsoluteY() + getLineY(2) + i, color.getRGB());
				font.drawString(getFacingTowardsZ(), pos.getAbsoluteX() + getWidth() - padding, pos.getAbsoluteY() + getLineY(3) + i, color.getRGB());
			}
			
			if (showBiome) {
				font.drawString(getBiomeText(), pos.getAbsoluteX() + j, pos.getAbsoluteY() + getLineY(4) + i, color.getRGB());
			}
		}
	}
	
	private int getLineY(int line) {
		return line * 11 - 10; 
	}
	
	private String getCoordinatesXText() {
		return "X: " + (int) mc.getRenderViewEntity().posX;
	}
	
	private String getCoordinatesYText() {
		return "Y: " + (int) mc.getRenderViewEntity().getEntityBoundingBox().minY;
	}
	
	private String getCoordinatesZText() {
		return "Z: " + (int) mc.getRenderViewEntity().posZ;
	}
	
	private String getBiomeText() {
		BlockPos playerPos = new BlockPos(mc.getRenderViewEntity().posX, mc.getRenderViewEntity().getEntityBoundingBox().minY, mc.getRenderViewEntity().posZ);
		Chunk chunk = this.mc.theWorld.getChunkFromBlockCoords(playerPos);
		
		return "Biome: " + chunk.getBiome(playerPos, mc.theWorld.getWorldChunkManager()).biomeName;
	}
	
	private String getFacingTowardsX() {
        switch (mc.getRenderViewEntity().getHorizontalFacing())
        {
            case WEST:
            	return "-";
            case EAST:
            	return "+";
            default:
            	return "";
        }
	}

	private String getFacing() {
        switch (mc.getRenderViewEntity().getHorizontalFacing())
        {
            case NORTH:
            	return "N";
            case SOUTH:
            	return "S";
            case WEST:
            	return "W";
            case EAST:
            	return "E";
            default:
            	return "/";
        }
	}
	
	private String getFacingTowardsZ() {
        switch (mc.getRenderViewEntity().getHorizontalFacing())
        {
            case NORTH:
            	return "-";
            case SOUTH:
            	return "+";
            default:
            	return "";
        }
	}
	
	public void setShowBackground(boolean enabled) {
		showBackground = enabled;
	}
	
	public boolean isShowBackgroundEnabled() {
		return showBackground;
	}
	
	public void setTextShadow(boolean enabled) {
		textShadow = enabled;
	}
	
	public boolean isTextShadowEnabled() {
		return textShadow;
	}
	
	public ColorManager getColor() {
		return color;
	}
	
	public void setShowBiome(boolean enabled) {
		showBiome = enabled;
	}
	
	public boolean isShowBiomeEnabled() {
		return showBiome;
	}
	
	public void setShowTowards(boolean enabled) {
		showTowards = enabled;
	}
	
	public boolean isShowTowardsEnabled() {
		return showTowards;
	}
}
