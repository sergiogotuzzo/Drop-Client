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
		return (showBiome ? 38 : 28) + 12;
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
		
		int i = 4;
		int j = 6;
		
		drawText(getCoordinatesXText(), pos.getAbsoluteX() + j, pos.getAbsoluteY() + 1 * 11 - 10 + i, textColor.getRGB(), textShadow, textChroma);
		drawText(getCoordinatesYText(), pos.getAbsoluteX() + j, pos.getAbsoluteY() + 2 * 11 - 10 + i, textColor.getRGB(), textShadow, textChroma);
		drawText(getCoordinatesZText(), pos.getAbsoluteX() + j, pos.getAbsoluteY() + 3 * 11 - 10 + i, textColor.getRGB(), textShadow, textChroma);
		
		if (showTowards) {
			drawText(getFacingTowardsX(), pos.getAbsoluteX() + getWidth() - font.getStringWidth(getFacingTowardsX()) - j, pos.getAbsoluteY() + 1 * 11 - 10 + i, textColor.getRGB(), textShadow, textChroma);
			drawText(getFacing(), pos.getAbsoluteX() + getWidth() - font.getStringWidth(getFacing()) - j, pos.getAbsoluteY() + 2 * 11 - 10 + i, textColor.getRGB(), textShadow, textChroma);
			drawText(getFacingTowardsZ(), pos.getAbsoluteX() + getWidth() - font.getStringWidth(getFacingTowardsZ()) - j, pos.getAbsoluteY() + 3 * 11 - 10 + i, textColor.getRGB(), textShadow, textChroma);
		}
		
		if (showBiome) {
			drawText(getBiomeText(), pos.getAbsoluteX() + j, pos.getAbsoluteY() + 4 * 11 - 10 + i, textColor.getRGB(), textShadow, textChroma);
		}
	}
	
	private String getLongestCoordinateText() {
		int x = (int) mc.getRenderViewEntity().posX;
		int y = (int) mc.getRenderViewEntity().getEntityBoundingBox().minY;
		int z = (int) mc.getRenderViewEntity().posZ;
		
		int max = x;
		String text = String.valueOf(x);
		
		if (Math.abs(y) > max) {
			max = Math.abs(y);
			text = String.valueOf(y);
		}
		
		if (Math.abs(z) > max) {
			max = Math.abs(z);
			text = String.valueOf(z);
		}
		
		return "C: " + text;
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
	
	private int getDirectionFacing() {
        int yaw = (int) mc.getRenderViewEntity().rotationYaw;
        
        yaw += 360;
        yaw += 22;
        yaw %= 360;
        
        return yaw / 45;
    }
	
	private String getFacingTowardsX() {
        switch (getDirectionFacing()) {
	        case 0:
	            return "";
	        case 1:
	            return "-";
	        case 2:
	            return "-";
	        case 3:
	        	return "-";
	        case 4:
	        	return "";
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
	        case 2:
	            return "";
	        case 3:
	        	return "-";
	        case 4:
	        	return "-";
	        case 5:
	        	return "-";
	        case 6:
	        	return "";
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
