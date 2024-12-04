package rubik.mods.impl;

import java.awt.Color;

import net.minecraft.util.BlockPos;
import net.minecraft.world.chunk.Chunk;
import rubik.ColorManager;
import rubik.gui.hud.ScreenPosition;
import rubik.mods.ModDraggable;

public class CoordinatesDisplay extends ModDraggable {
	private boolean textShadow = true;
	private ColorManager textColor = ColorManager.fromColor(Color.WHITE);
	private boolean showBiome = true;
	private boolean showTowards = true;
	private boolean textChroma = false;
	
	private final int padding = 12;
	private final int gap = 10;
	
	public CoordinatesDisplay() {
		setTextShadow((boolean) getFromFile("textShadow", textShadow));
		setTextColor((int) ((long) getFromFile("textColor", textColor.getRGB())));
		setShowBiome((boolean) getFromFile("showBiome", showBiome));
		setShowTowards((boolean) getFromFile("showTowards", showTowards));
		setTextChroma((boolean) getFromFile("textChroma", textChroma));
	}
	
	@Override
	public int getWidth() {
		return showBiome ? (font.getStringWidth(getBiomeText()) > getLongestCoordinateText() + (showTowards ? gap : 0) ? font.getStringWidth(getBiomeText()) : getLongestCoordinateText() + (showTowards ? gap : 0)) + padding : getLongestCoordinateText() + (showTowards ? gap : 0) + padding;
	}

	@Override
	public int getHeight() {
		return (showBiome ? 38 : 28) + padding;
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
		
		final int i = 4;
		final int j = padding / 2;
		
		drawText(getCoordinatesXText(), pos.getAbsoluteX() + j, pos.getAbsoluteY() + getLineY(1) + i, textColor.getRGB(), textShadow, textChroma);
		drawText(getCoordinatesYText(), pos.getAbsoluteX() + j, pos.getAbsoluteY() + getLineY(2) + i, textColor.getRGB(), textShadow, textChroma);
		drawText(getCoordinatesZText(), pos.getAbsoluteX() + j, pos.getAbsoluteY() + getLineY(3) + i, textColor.getRGB(), textShadow, textChroma);
		
		if (showTowards) {
			drawText(getFacingTowardsX(), pos.getAbsoluteX() + getWidth() - font.getStringWidth(getFacingTowardsX()) - padding / 2, pos.getAbsoluteY() + getLineY(1) + i, textColor.getRGB(), textShadow, textChroma);
			drawText(getFacing(), pos.getAbsoluteX() + getWidth() - font.getStringWidth(getFacing()) - padding / 2, pos.getAbsoluteY() + getLineY(2) + i, textColor.getRGB(), textShadow, textChroma);
			drawText(getFacingTowardsZ(), pos.getAbsoluteX() + getWidth() - font.getStringWidth(getFacingTowardsZ()) - padding / 2, pos.getAbsoluteY() + getLineY(3) + i, textColor.getRGB(), textShadow, textChroma);
		}
		
		if (showBiome) {
			drawText(getBiomeText(), pos.getAbsoluteX() + j, pos.getAbsoluteY() + getLineY(4) + i, textColor.getRGB(), textShadow, textChroma);
		}
	}
	
	private int getLineY(int line) {
		return line * 11 - 10; 
	}
	
	private int getLongestCoordinate() {
		int max = Math.abs((int) mc.getRenderViewEntity().posX);
		
		if (Math.abs((int) mc.getRenderViewEntity().getEntityBoundingBox().minY) > max) {
			max = Math.abs((int) mc.getRenderViewEntity().getEntityBoundingBox().minY);
		}
		
		if (Math.abs((int) mc.getRenderViewEntity().posZ) > max) {
			max = Math.abs((int) mc.getRenderViewEntity().posZ);
		}
		
		return max;
	}
	
	private int getLongestCoordinateText() {
		return font.getStringWidth("C: -" + getLongestCoordinate());
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
	
	public void setTextShadow(boolean enabled) {
		textShadow = enabled;
		
		setToFile("textShadow", enabled);
	}
	
	public boolean isTextShadowEnabled() {
		return textShadow;
	}
	
	public void setTextColor(int rgb) {
		this.textColor = ColorManager.fromRGB(rgb);
		
		setToFile("textColor", rgb);
	}
	
	public ColorManager getTextColor() {
		return textColor;
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
	
	public void setTextChroma(boolean enabled) {
		this.textChroma = enabled;
		
		setToFile("textChroma", enabled);
	}
	
	public boolean isTextChromaEnabled() {
		return textChroma;
	}
}
