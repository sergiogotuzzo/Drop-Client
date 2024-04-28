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
	private ColorManager color = new ColorManager(Color.WHITE);
	private boolean shadow = true;
	private boolean biome = true;
	
	@Override
	public int getWidth() {
		return 60;
	}

	@Override
	public int getHeight() {
		return biome ? 42 : 32;
	}

	@Override
	public void render(ScreenPosition pos) {
		int paddingX = 6;
        int paddingY = 4;
        int width = biome ? font.getStringWidth(getBiomeText()) : getWidth();
        
		Gui.drawRect(
				pos.getAbsoluteX() - paddingX,
				pos.getAbsoluteY() - paddingY,
				pos.getAbsoluteX() + width + paddingX,
				pos.getAbsoluteY() + getHeight() + paddingY,
				new Color(0, 0, 0, 102).getRGB()
				);
		
		if (shadow) {
			font.drawStringWithShadow(getCoordinatesXText(), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color.getRGB());
			font.drawStringWithShadow(getCoordinatesYText(), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 12, color.getRGB());
			font.drawStringWithShadow(getCoordinatesZText(), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 23, color.getRGB());
			
			font.drawStringWithShadow(getFacingTowardsX(), pos.getAbsoluteX() + width - 5, pos.getAbsoluteY() + 1, color.getRGB());
			font.drawStringWithShadow(getFacing(), pos.getAbsoluteX() + width - 5, pos.getAbsoluteY() + 12, color.getRGB());
			font.drawStringWithShadow(getFacingTowardsZ(), pos.getAbsoluteX() + width - 5, pos.getAbsoluteY() + 23, color.getRGB());
			
			if (biome) {
				font.drawStringWithShadow(getBiomeText(), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 34, color.getRGB());
			}
		} else {
			font.drawString(getCoordinatesXText(), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color.getRGB());
			font.drawString(getCoordinatesYText(), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 12, color.getRGB());
			font.drawString(getCoordinatesZText(), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 23, color.getRGB());
			
			font.drawString(getFacingTowardsX(), pos.getAbsoluteX() + width - 5, pos.getAbsoluteY() + 1, color.getRGB());
			font.drawString(getFacing(), pos.getAbsoluteX() + width - 5, pos.getAbsoluteY() + 12, color.getRGB());
			font.drawString(getFacingTowardsZ(), pos.getAbsoluteX() + width - 5, pos.getAbsoluteY() + 23, color.getRGB());
			
			if (biome) {
				font.drawString(getBiomeText(), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 34, color.getRGB());
			}
		}
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
	
	public ColorManager getColorManager() {
		return color;
	}
	
	public Color getColor() {
		return color.getColor();
	}
	
	public void setShadowEnabled(boolean enabled) {
		shadow = enabled;
	}
	
	public boolean isShadowEnabled() {
		return shadow;
	}
	
	public void setBiomeEnabled(boolean enabled) {
		biome = enabled;
	}
	
	public boolean isBiomeEnabled() {
		return biome;
	}
}
