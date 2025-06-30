package drop.mods.impl;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import drop.gui.GuiDropClientScreen;
import drop.gui.GuiSettings;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.AxisAlignedBB;
import drop.mods.Mod;
import drop.mods.ModColor;
import drop.mods.ModOptions;
import drop.mods.option.Brackets;
import drop.mods.option.ParentOption;
import drop.mods.option.type.BooleanOption;
import drop.mods.option.type.BracketsOption;
import drop.mods.option.type.ColorOption;
import drop.mods.option.type.FloatOption;

public class BlockOverlay extends Mod {
	public BlockOverlay() {
		super(false);
		
		this.options = new ModOptions(
				new BooleanOption(this, "outline", true, new GuiSettings(1, "Outline")),
				new FloatOption(this, "outlineWidth", 0.4F, 5.0F, 2.0F, new ParentOption("outline"), new GuiSettings(2, "Outline Width")),
				new ColorOption(this, "outlineColor", ModColor.fromColor(Color.BLACK, false), new ParentOption("outline"), new GuiSettings(3, "Outline Color", true, false)),
				new BooleanOption(this, "overlay", false, new GuiSettings(4, "Overlay")),
				new ColorOption(this, "overlayColor", ModColor.fromColor(Color.WHITE, false).setAlpha(80), new ParentOption("overlay"), new GuiSettings(5, "Overlay Color", true, true))
				);
				
		saveOptions();
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
}
