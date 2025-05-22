package rubik.mods.impl;

import java.awt.Color;

import net.minecraft.util.BlockPos;
import net.minecraft.world.chunk.Chunk;
import rubik.ColorManager;
import rubik.gui.hud.ScreenPosition;
import rubik.mods.ModDraggableText;

public class CoordinatesDisplay extends ModDraggableText {
	private boolean showBiome = true;
	private boolean showTowards = true;
	
	public CoordinatesDisplay() {
		setShowBiome((boolean) getFromFile("showBiome", showBiome));
		setShowTowards((boolean) getFromFile("showTowards", showTowards));
	}
	
	@Override
	public int getWidth() {
		int width = 0;

		if (showBiome) {
			int biomeWidth = font.getStringWidth(getBiomeText());
			int coordsWidth = font.getStringWidth(getLongestCoordinateText());
			
			if (showTowards) {
				coordsWidth += 10 + 6;
			}

			if (biomeWidth > coordsWidth) {
				width = biomeWidth;
			} else {
				width = coordsWidth;
			}

			width += 12;
		} else {
			width = font.getStringWidth(getLongestCoordinateText());

			if (showTowards) {
				width += 10 + 6;
			}

			width += 12;
		}

		return width;
	}


	@Override
	public int getHeight() {
		int height = 12;
		
		if (showBiome) {
			height += 38;
		} else {
			height += 28;
		}
		
		return height;
	}

	@Override
	public void render(ScreenPosition pos) {
		drawRect(
				pos.getAbsoluteX(),
				pos.getAbsoluteY(),
				pos.getAbsoluteX() + getWidth(),
				pos.getAbsoluteY() + getHeight(),
				ColorManager.fromColor(Color.BLACK).setAlpha(102).getRGB()
				);
		
		int i = 11;
		
		String textX = "X: " + (int) mc.getRenderViewEntity().posX;
		String textY = "Y: " + (int) mc.getRenderViewEntity().getEntityBoundingBox().minY;
		String textZ = "Z: " + (int) mc.getRenderViewEntity().posZ;
		
		drawText(textX, pos.getAbsoluteX() + 6, pos.getAbsoluteY() + i * 1 - 6, textColor.getRGB(), textShadow, textChroma);
		drawText(textY, pos.getAbsoluteX() + 6, pos.getAbsoluteY() + i * 2 - 6, textColor.getRGB(), textShadow, textChroma);
		drawText(textZ, pos.getAbsoluteX() + 6, pos.getAbsoluteY() + i * 3 - 6, textColor.getRGB(), textShadow, textChroma);
		
		if (showTowards) {
			drawText(getFacingTowardsX(), pos.getAbsoluteX() + getWidth() - font.getStringWidth(getFacingTowardsX()) - 6, pos.getAbsoluteY() + i * 1 - 6, textColor.getRGB(), textShadow, textChroma);
			drawText(getFacing(), pos.getAbsoluteX() + getWidth() - font.getStringWidth(getFacing()) - 6, pos.getAbsoluteY() + i * 2 - 6, textColor.getRGB(), textShadow, textChroma);
			drawText(getFacingTowardsZ(), pos.getAbsoluteX() + getWidth() - font.getStringWidth(getFacingTowardsZ()) - 6, pos.getAbsoluteY() + i * 3 - 6, textColor.getRGB(), textShadow, textChroma);
		}
		
		if (showBiome) {
			drawText(getBiomeText(), pos.getAbsoluteX() + 6, pos.getAbsoluteY() + 4 * 11 - 6, textColor.getRGB(), textShadow, textChroma);
		}
	}
	
	private String getLongestCoordinateText() {
		String longestText = "";
		
		String textX = "X: " + (int) mc.getRenderViewEntity().posX;
		String textY = "Y: " + (int) mc.getRenderViewEntity().getEntityBoundingBox().minY;
		String textZ = "Z: " + (int) mc.getRenderViewEntity().posZ;
		
		if (font.getStringWidth(textX) > font.getStringWidth(longestText)) {
			longestText = textX;
		}
		
		if (font.getStringWidth(textY) > font.getStringWidth(longestText)) {
			longestText = textY;
		}
		
		if (font.getStringWidth(textZ) > font.getStringWidth(longestText)) {
			longestText = textZ;
		}
		
		return longestText;
	}
	
	private String getBiomeText() {
		BlockPos playerPos = new BlockPos(mc.getRenderViewEntity().posX, mc.getRenderViewEntity().getEntityBoundingBox().minY, mc.getRenderViewEntity().posZ);
		Chunk chunk = this.mc.theWorld.getChunkFromBlockCoords(playerPos);
		
		return "Biome: " + chunk.getBiome(playerPos, mc.theWorld.getWorldChunkManager()).biomeName;
	}
	
	private int getDirectionFacing() {
        int yaw = (int) mc.getRenderViewEntity().rotationYaw;
        
        yaw += 360;
        yaw += 22;
        yaw %= 360;
        
        return yaw / 45;
    }
	
	private String getFacingTowardsX() {
        switch (getDirectionFacing()) {
	        case 1:
	            return "-";
	        case 2:
	            return "-";
	        case 3:
	        	return "-";
	        case 5:
	        	return "+";
	        case 6:
	        	return "+";
	        case 7:
	        	return "+";
	        default:
	        	return "";
        }
	}

	private String getFacing() {
        switch (getDirectionFacing()) {
            case 0:
                return "S";
            case 1:
                return "SW";
            case 2:
                return "W";
            case 3:
            	return "NW";
            case 4:
            	return "N";
            case 5:
            	return "NE";
            case 6:
            	return "E";
            case 7:
            	return "SE";
            default:
            	return "/";
        }
	}
	
	private String getFacingTowardsZ() {
		switch (getDirectionFacing()) {
	        case 0:
	            return "+";
	        case 1:
	            return "+";
	        case 3:
	        	return "-";
	        case 4:
	        	return "-";
	        case 5:
	        	return "-";
	        case 7:
	        	return "+";
	        default:
	        	return "";
	    }
	}
	
	public void setShowBiome(boolean enabled) {
		showBiome = enabled;
		
		setToFile("showBiome", enabled);
	}
	
	public boolean isShowBiomeEnabled() {
		return showBiome;
	}
	
	public void setShowTowards(boolean enabled) {
		showTowards = enabled;
		
		setToFile("showTowards", enabled);
	}
	
	public boolean isShowTowardsEnabled() {
		return showTowards;
	}
}
