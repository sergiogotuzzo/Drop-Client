package rubik.mods.impl;

import java.awt.Color;

import net.minecraft.client.gui.Gui;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.BiomeGenBase;
import rubik.gui.hud.ScreenPosition;
import rubik.mods.ModDraggable;

public class CoordinatesDisplay extends ModDraggable {
	private int color = 0xFFFFFFFF;
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
			font.drawStringWithShadow(getCoordinatesXText(), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color);
			font.drawStringWithShadow(getCoordinatesYText(), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 12, color);
			font.drawStringWithShadow(getCoordinatesZText(), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 23, color);
			
			font.drawStringWithShadow(getFacingTowardsX(), pos.getAbsoluteX() + width - 5, pos.getAbsoluteY() + 1, color);
			font.drawStringWithShadow(getFacing(), pos.getAbsoluteX() + width - 5, pos.getAbsoluteY() + 12, color);
			font.drawStringWithShadow(getFacingTowardsZ(), pos.getAbsoluteX() + width - 5, pos.getAbsoluteY() + 23, color);
			
			if (biome) {
				font.drawStringWithShadow(getBiomeText(), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 34, color);
			}
		} else {
			font.drawString(getCoordinatesXText(), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color);
			font.drawString(getCoordinatesYText(), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 12, color);
			font.drawString(getCoordinatesZText(), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 23, color);
			
			font.drawString(getFacingTowardsX(), pos.getAbsoluteX() + width - 5, pos.getAbsoluteY() + 1, color);
			font.drawString(getFacing(), pos.getAbsoluteX() + width - 5, pos.getAbsoluteY() + 12, color);
			font.drawString(getFacingTowardsZ(), pos.getAbsoluteX() + width - 5, pos.getAbsoluteY() + 23, color);
			
			if (biome) {
				font.drawString(getBiomeText(), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 34, color);
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
		BiomeGenBase biomeGen = mc.theWorld.getBiomeGenForCoords(playerPos);
		
		return "Biome: " + biomeGen.biomeName;
	}
	
	private String getFacingTowardsX() {
		return mc.thePlayer.getLookVec().xCoord > 0 ? "+" : "-";
	}

	private String getFacing() {
		float yaw = mc.thePlayer.rotationYaw;
        
        yaw = MathHelper.wrapAngleTo180_float(yaw);
        
        if (yaw >= -135 && yaw < -45) {
            return "E";
        } else if (yaw >= -45 && yaw < 45) {
            return "S";
        } else if (yaw >= 45 && yaw < 135) {
            return "W";
        } else {
            return "N";
        }
	}
	
	private String getFacingTowardsZ() {
		return mc.thePlayer.getLookVec().zCoord > 0 ? "+" : "-";
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
