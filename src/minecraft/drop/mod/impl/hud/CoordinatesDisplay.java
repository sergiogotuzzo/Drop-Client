package drop.mod.impl.hud;

import net.minecraft.util.BlockPos;
import net.minecraft.world.chunk.Chunk;

import java.awt.Color;

import drop.gui.mod.GuiSettings;
import drop.gui.mod.hud.ScreenPosition;
import drop.mod.ModDraggable;
import drop.mod.option.ModColor;
import drop.mod.option.ParentOption;
import drop.mod.option.type.BooleanOption;
import drop.mod.option.type.ColorOption;

public class CoordinatesDisplay extends ModDraggable {
	public CoordinatesDisplay() {
		super(false, 0.5, 0.5);
				
		saveOptions(
				new ColorOption(this, "textColor", ModColor.fromColor(Color.WHITE, false), new GuiSettings(1, "Text Color", true, false)),
				new BooleanOption(this, "textShadow", true, new GuiSettings(2, "Text Shadow")),
				new BooleanOption(this, "showBackground", true, new GuiSettings(3, "Background")),
				new ColorOption(this, "backgroundColor", ModColor.fromRGB(0, 0, 0, 102, false), new ParentOption("showBackground"), new GuiSettings(4, "Background Color", false, true)),
				new BooleanOption(this, "showBorder", false, new ParentOption("showBackground"), new GuiSettings(8, "Border")),
				new ColorOption(this, "borderColor", ModColor.fromRGB(0, 0, 0, 255, false), new ParentOption("showBorder"), new GuiSettings(9, "Border Color", false, true)),
				new BooleanOption(this, "showBiome", true, new GuiSettings(5, "Show Biome")),
				new BooleanOption(this, "showFacing", true, new GuiSettings(6, "Show Facing")),
				new BooleanOption(this, "showFacingTowards", false, new GuiSettings(7, "Show Facing Towards"))
				);
	}
	
	@Override
	public int getWidth() {
		int width = 0;

		if (getBooleanOption("showBiome").isToggled()) {
			int biomeWidth = font.getStringWidth(getBiomeText());
			int coordsWidth = font.getStringWidth(getLongestCoordinateText());

			if (biomeWidth > coordsWidth) {
				width = biomeWidth;
			} else {
				width = coordsWidth;
			}

			if (getBooleanOption("showFacing").isToggled() || getBooleanOption("showFacingTowards").isToggled()) {
				width += 10 + 6;
			}

			if (getBooleanOption("showBackground").isToggled()) {
				width += 12;
			}
		} else {
			width = font.getStringWidth(getLongestCoordinateText());
			
			if (getBooleanOption("showFacing").isToggled() || getBooleanOption("showFacingTowards").isToggled()) {
				width += 10 + 6;
			}

			if (getBooleanOption("showBackground").isToggled()) {
				width += 12;
			}
		}

		return width;
	}


	@Override
	public int getHeight() {
		int height = 0;
		
		int lines = getBooleanOption("showBiome").isToggled() ? 4 : 3;
		
		if (getBooleanOption("showBackground").isToggled()) {
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
		if (getBooleanOption("showBackground").isToggled()) {
			getBounds().fill(getColorOption("backgroundColor").getColor().getRGB());
			
			if (getBooleanOption("showBorder").isToggled()) {
		    	getBounds().stroke(getColorOption("borderColor").getColor().getRGB());
	    	}
		}
		
		int i = 11;
		
		String textX = "X: " + (int) mc.getRenderViewEntity().posX;
		String textY = "Y: " + (int) mc.getRenderViewEntity().getEntityBoundingBox().minY;
		String textZ = "Z: " + (int) mc.getRenderViewEntity().posZ;
		
		int k = getBooleanOption("showBackground").isToggled() ? 6 : 0;
		int j = getBooleanOption("showBackground").isToggled() ? 6 : font.FONT_HEIGHT + 1;
		int h = getBooleanOption("showBackground").isToggled() ? 0 : 1;
		int l = getBooleanOption("showBackground").isToggled() ? 1 : 0;
		
		drawText(textX, pos.getAbsoluteX() + k + h, pos.getAbsoluteY() + i * 1 - j + l, getColorOption("textColor").getColor(), getBooleanOption("textShadow").isToggled());
		drawText(textY, pos.getAbsoluteX() + k + h, pos.getAbsoluteY() + i * 2 - j + l, getColorOption("textColor").getColor(), getBooleanOption("textShadow").isToggled());
		drawText(textZ, pos.getAbsoluteX() + k + h, pos.getAbsoluteY() + i * 3 - j + l, getColorOption("textColor").getColor(), getBooleanOption("textShadow").isToggled());
		
		if (getBooleanOption("showFacing").isToggled()) {
			drawText(getFacing(), pos.getAbsoluteX() + getWidth() - font.getStringWidth(getFacing()) - k + h + l, pos.getAbsoluteY() + i * 2 - j + l, getColorOption("textColor").getColor(), getBooleanOption("textShadow").isToggled());
		}
		
		if (getBooleanOption("showFacingTowards").isToggled()) {
			drawText(getFacingTowardsX(), pos.getAbsoluteX() + getWidth() - font.getStringWidth(getFacingTowardsX()) - k + h + l, pos.getAbsoluteY() + i * 1 - j + l, getColorOption("textColor").getColor(), getBooleanOption("textShadow").isToggled());
			drawText(getFacingTowardsZ(), pos.getAbsoluteX() + getWidth() - font.getStringWidth(getFacingTowardsZ()) - k + h + l, pos.getAbsoluteY() + i * 3 - j + l, getColorOption("textColor").getColor(), getBooleanOption("textShadow").isToggled());
		}
		
		if (getBooleanOption("showBiome").isToggled()) {
			drawText(getBiomeText(), pos.getAbsoluteX() + k, pos.getAbsoluteY() + i * 4 - j + h + l, getColorOption("textColor").getColor(), getBooleanOption("textShadow").isToggled());
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
}
