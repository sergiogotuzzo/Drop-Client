package drop.mods.impl;

import java.awt.Color;
import java.util.List;

import drop.ColorManager;
import drop.gui.GuiDropClientScreen;
import drop.gui.mod.packdisplay.GuiPackDisplay;
import drop.mods.hud.ScreenPosition;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.util.ResourceLocation;
import drop.mods.ModDraggable;

public class PackDisplay extends ModDraggable {
	private static class DefaultPack {
		public static ResourceLocation getResourceLocation() {
			return new ResourceLocation("drop/pack.png");
		}
		
		public static String getPackName() {
			return "Default";
		}
		
		public static String getPackDescription() {
			return "The default look of Minecraft";
		}
	}
	
	private boolean showBackground = true;
	private boolean nameTextShadow = true;
	private ColorManager nameTextColor = ColorManager.fromColor(Color.WHITE);
	private boolean nameTextChroma = false;
	private boolean descriptionTextShadow = true;
	private ColorManager descriptionTextColor = ColorManager.fromColor(Color.GRAY);
	private boolean descriptionTextChroma = false;
	private boolean showIcon = true;
	private boolean showDescription = false;
	
	public PackDisplay() {
		setShowBackground((boolean) getFromFile("showBackground", showBackground));
		setNameTextColor((int) ((long) getFromFile("nameTextColor", nameTextColor.getRGB())));
		setNameTextShadow((boolean) getFromFile("nameTextShadow", nameTextShadow));
		setNameTextChroma((boolean) getFromFile("nameTextChroma", nameTextChroma));
		setDescriptionTextColor((int) ((long) getFromFile("descriptionTextColor", descriptionTextColor.getRGB())));
		setDescriptionTextShadow((boolean) getFromFile("descriptionTextShadow", descriptionTextShadow));
		setDescriptionTextChroma((boolean) getFromFile("descriptionTextChroma", descriptionTextChroma));
		setShowIcon((boolean) getFromFile("showIcon", showIcon));
		setShowDescription((boolean) getFromFile("showDescription", showDescription));
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiPackDisplay(previousGuiScreen);
	}
	
	@Override
	public int getWidth() {
		int width = 8;
		
		if (showIcon) {
			width += 28;
		}
		
		if (showDescription) {
			width += font.getStringWidth(mc.getResourcePackRepository().getRepositoryEntries().isEmpty() ? DefaultPack.getPackDescription() : getLongestSelectedPackDescription());
		} else {
			width += font.getStringWidth(mc.getResourcePackRepository().getRepositoryEntries().isEmpty() ? DefaultPack.getPackName() : getLongestSelectedPackName());
		}
		
		return width;
	}

	@Override
	public int getHeight() {
		return mc.getResourcePackRepository().getRepositoryEntries().isEmpty() ? 28 : 28 * mc.getResourcePackRepository().getRepositoryEntries().size();
	}

	@Override
	public void render(ScreenPosition pos) {		
		List<ResourcePackRepository.Entry> selectedPacks = mc.getResourcePackRepository().getRepositoryEntries();
		
		if (!selectedPacks.isEmpty()) {
			if (showBackground) {
				drawRect(pos.getAbsoluteX() + (showIcon ? 28 : 0), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() + getHeight());
			}
			
			int offsetY = 0;
			
			for (int i = 0; i < selectedPacks.size(); i++) {
				ResourcePackRepository.Entry selectedPack = selectedPacks.get(i);
				
				drawSelectedPack(selectedPack, offsetY);
				
				offsetY += 28;
			}
		}
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		List<ResourcePackRepository.Entry> selectedPacks = mc.getResourcePackRepository().getRepositoryEntries();
		
		if (!selectedPacks.isEmpty()) {
			if (showBackground) {
				drawRect(pos.getAbsoluteX() + (showIcon ? 28 : 0), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() + getHeight());
			}
			
			int offsetY = 0;
			
			for (int i = 0; i < selectedPacks.size(); i++) {
				ResourcePackRepository.Entry selectedPack = selectedPacks.get(i);
				
				drawSelectedPack(selectedPack, offsetY);
				
				offsetY += 28;
			}
		} else {
			if (showBackground) {
				drawRect(pos.getAbsoluteX() + (showIcon ? 28 : 0), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() + getHeight());
			}
			
			drawDefaultPack();
		}
	}
	
	private void drawSelectedPack(ResourcePackRepository.Entry selectedPack, int offsetY) {		
		if (showIcon) {
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			
			selectedPack.bindTexturePackIcon(mc.getTextureManager());
			
			Gui.drawModalRectWithCustomSizedTexture(pos.getAbsoluteX(), pos.getAbsoluteY() + offsetY, 0.0F, 0.0F, 28, 28, 28, 28);
		}

		int packX = pos.getAbsoluteX() + 4 + (showIcon ? 28 : 0);
		int packNameY = pos.getAbsoluteY() + offsetY + (showDescription ? 4 : 28 / 2 - 4);
		
		String packName = selectedPack.getResourcePackName();
		String packDescription = selectedPack.getTexturePackDescription().replace("§r", "");

		drawText(packName, packX, packNameY, nameTextColor, nameTextShadow, !packName.contains("§") && nameTextChroma);
				
		if (showDescription) {
			drawText(packDescription, packX, pos.getAbsoluteY() + offsetY + 28 - font.FONT_HEIGHT - 4, descriptionTextColor, descriptionTextShadow, !packDescription.contains("§") && descriptionTextChroma);
		}
	}
	
	private void drawDefaultPack() {
		if (showIcon) {
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			
			mc.getTextureManager().bindTexture(DefaultPack.getResourceLocation());
			
			Gui.drawModalRectWithCustomSizedTexture(pos.getAbsoluteX(), pos.getAbsoluteY(), 0.0F, 0.0F, 28, 28, 28, 28);
		}

		int packX = pos.getAbsoluteX() + 4 + (showIcon ? 28 : 0);
		int packNameY = pos.getAbsoluteY() + (showDescription ? 4 : 28 / 2 - 4);
		
		drawText(DefaultPack.getPackName(), packX, packNameY, nameTextColor, nameTextShadow, nameTextChroma);
		
		if (showDescription) {
			drawText(DefaultPack.getPackDescription(), packX, pos.getAbsoluteY() + 28 - font.FONT_HEIGHT - 4, descriptionTextColor, descriptionTextShadow, descriptionTextChroma);
		}
	}
	
	private String getLongestSelectedPackName() {
		List<ResourcePackRepository.Entry> selectedPacks = mc.getResourcePackRepository().getRepositoryEntries();
		
		String longest = "";
		String last;
		
		for (ResourcePackRepository.Entry selectedPack : selectedPacks) {
			last = selectedPack.getResourcePackName();
			
			if (font.getStringWidth(last) > font.getStringWidth(longest)) {
				longest = last;
			}
		}
		
		return longest;
	}
	
	private String getLongestSelectedPackDescription() {
		List<ResourcePackRepository.Entry> selectedPacks = mc.getResourcePackRepository().getRepositoryEntries();
		
		String longest = "";
		String last;
		
		for (ResourcePackRepository.Entry selectedPack : selectedPacks) {
			last = selectedPack.getTexturePackDescription();
			
			if (font.getStringWidth(last) > font.getStringWidth(longest)) {
				longest = last;
			}
		}
		
		return longest;
	}
	
	public void setShowBackground(boolean enabled) {
		showBackground = enabled;
		
		setToFile("showBackground", enabled);
	}
	
	public boolean isShowBackgroundEnabled() {
		return showBackground;
	}
	
	public void setNameTextColor(int rgb) {
		this.nameTextColor = ColorManager.fromRGB(rgb);
		
		setToFile("nameTextColor", rgb);
	}
	
	public ColorManager getNameTextColor() {
		return nameTextColor;
	}
	
	public void setNameTextShadow(boolean enabled) {
		nameTextShadow = enabled;
		
		setToFile("nameTextShadow", enabled);
	}
	
	public boolean isNameTextShadowEnabled() {
		return nameTextShadow;
	}
	
	public void setNameTextChroma(boolean enabled) {
		this.nameTextChroma = enabled;
		
		setToFile("nameTextChroma", enabled);
	}
	
	public boolean isNameTextChromaEnabled() {
		return nameTextChroma;
	}
	
	public void setDescriptionTextColor(int rgb) {
		this.descriptionTextColor = ColorManager.fromRGB(rgb);
		
		setToFile("descriptionTextColor", rgb);
	}
	
	public ColorManager getDescriptionTextColor() {
		return descriptionTextColor;
	}
	
	public void setDescriptionTextShadow(boolean enabled) {
		descriptionTextShadow = enabled;
		
		setToFile("descriptionTextShadow", enabled);
	}
	
	public boolean isDescriptionTextShadowEnabled() {
		return descriptionTextShadow;
	}
	
	public void setDescriptionTextChroma(boolean enabled) {
		this.descriptionTextChroma = enabled;
		
		setToFile("descriptionTextChroma", enabled);
	}
	
	public boolean isDescriptionTextChromaEnabled() {
		return descriptionTextChroma;
	}
	
	public void setShowIcon(boolean toggled) {
		showIcon = toggled;
		
		setToFile("showIcon", toggled);
	}
	
	public boolean isShowIconToggled() {
		return showIcon;
	}
	
	public void setShowDescription(boolean toggled) {
		showDescription = toggled;
		
		setToFile("showDescription", toggled);
	}
	
	public boolean isShowDescriptionToggled() {
		return showDescription;
	}
}
