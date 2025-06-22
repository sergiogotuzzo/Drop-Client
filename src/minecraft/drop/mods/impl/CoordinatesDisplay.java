package drop.mods.impl;

import net.minecraft.util.BlockPos;
import net.minecraft.world.chunk.Chunk;
import drop.ColorManager;
import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiCoordinatesDisplay;
import drop.mods.hud.ScreenPosition;
import drop.mods.ModDraggableText;

public class CoordinatesDisplay extends ModDraggableText {
	private boolean showBackground = false;
	protected ColorManager backgroundColor = ColorManager.fromRGB(0, 0, 0, 102, false);
	private boolean showBiome = true;
	private boolean showFacing = true;
	private boolean showFacingTowards = true;
	
	public CoordinatesDisplay() {
		super(false, 0.5, 0.5);
		
		setShowBackground(getBooleanFromFile("showBackground", showBackground));
		setBackgroundColor(getIntFromFile("backgroundColor", backgroundColor.getRGB()));
		setShowBiome(getBooleanFromFile("showBiome", showBiome));
		setShowFacing(getBooleanFromFile("showFacing", showFacing));
		setShowFacingTowards(getBooleanFromFile("showFacingTowards", showFacingTowards));
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiCoordinatesDisplay(previousGuiScreen);
	}
	
	@Override
	public int getWidth() {
		int width = 0;

		if (showBiome) {
			int biomeWidth = font.getStringWidth(getBiomeText());
			int coordsWidth = font.getStringWidth(getLongestCoordinateText());

			if (biomeWidth > coordsWidth) {
				width = biomeWidth;
			} else {
				width = coordsWidth;
			}

			if (showFacing || showFacingTowards) {
				width += 10 + 6;
			}

			if (showBackground) {
				width += 12;
			}
		} else {
			width = font.getStringWidth(getLongestCoordinateText());
			
			if (showFacing || showFacingTowards) {
				width += 10 + 6;
			}

			if (showBackground) {
				width += 12;
			}
		}

		return width;
	}


	@Override
	public int getHeight() {
		int height = 0;
		
		int lines = showBiome ? 4 : 3;
		
		if (showBackground) {
			height += 12;
						
			height += 10 * lines;
		} else {
			height += 11 * lines;
			height -= 2;
		}
		
		return height;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (showBackground) {
			getBounds().fill(backgroundColor.getRGB());
		}
		
		int i = 11;
		
		String textX = "X: " + (int) mc.getRenderViewEntity().posX;
		String textY = "Y: " + (int) mc.getRenderViewEntity().getEntityBoundingBox().minY;
		String textZ = "Z: " + (int) mc.getRenderViewEntity().posZ;
		
		int k = showBackground ? 6 : 0;
		int j = showBackground ? 6 : font.FONT_HEIGHT + 1;
		int h = showBackground ? 0 : 1;
		
		drawText(textX, pos.getAbsoluteX() + k + h, pos.getAbsoluteY() + i * 1 - j, textColor, textShadow);
		drawText(textY, pos.getAbsoluteX() + k + h, pos.getAbsoluteY() + i * 2 - j, textColor, textShadow);
		drawText(textZ, pos.getAbsoluteX() + k + h, pos.getAbsoluteY() + i * 3 - j, textColor, textShadow);
		
		if (showFacing) {
			drawText(getFacing(), pos.getAbsoluteX() + getWidth() - font.getStringWidth(getFacing()) - k + h, pos.getAbsoluteY() + i * 2 - j, textColor, textShadow);
		}
		
		if (showFacingTowards) {
			drawText(getFacingTowardsX(), pos.getAbsoluteX() + getWidth() - font.getStringWidth(getFacingTowardsX()) - k + h, pos.getAbsoluteY() + i * 1 - j, textColor, textShadow);
			drawText(getFacingTowardsZ(), pos.getAbsoluteX() + getWidth() - font.getStringWidth(getFacingTowardsZ()) - k + h, pos.getAbsoluteY() + i * 3 - j, textColor, textShadow);
		}
		
		if (showBiome) {
			drawText(getBiomeText(), pos.getAbsoluteX() + k, pos.getAbsoluteY() + i * 4 - j + h, textColor, textShadow);
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
	
	public void setShowBackground(boolean toggled) {
		showBackground = toggled;
		
		setToFile("showBackground", toggled);
	}
	
	public boolean isShowBackgroundToggled() {
		return showBackground;
	}
	
	public void setBackgroundColor(int rgb) {
		this.backgroundColor = ColorManager.fromRGB(rgb, false);
		
		setToFile("backgroundColor", rgb);
	}
	
	public ColorManager getBackgroundColor() {
		return backgroundColor;
	}
	
	public void setShowBiome(boolean toggled) {
		showBiome = toggled;
		
		setToFile("showBiome", toggled);
	}
	
	public boolean isShowBiomeToggled() {
		return showBiome;
	}
	
	public void setShowFacing(boolean toggled) {
		showFacing = toggled;
		
		setToFile("showFacing", toggled);
	}
	
	public boolean isShowFacingToggled() {
		return showFacing;
	}
	
	public void setShowFacingTowards(boolean toggled) {
		showFacingTowards = toggled;
		
		setToFile("showFacingTowards", toggled);
	}
	
	public boolean isShowFacingTowardsToggled() {
		return showFacingTowards;
	}
}
