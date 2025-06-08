package drop.mods.impl;

import java.awt.Color;
import java.util.List;

import drop.ColorManager;
import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiPackDisplay;
import drop.mods.hud.ScreenPosition;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.util.ResourceLocation;
import drop.mods.ModDraggable;

public class PackDisplay extends ModDraggable {
	private ColorManager nameTextColor = ColorManager.fromColor(Color.WHITE, false);
	private boolean nameTextShadow = true;
	private boolean showDescription = false;
	private ColorManager descriptionTextColor = ColorManager.fromColor(Color.GRAY, false);
	private boolean descriptionTextShadow = true;
	private boolean showBackground = true;
	private boolean showIcon = true;
	private boolean showAllSelectedPacks = true;
	
	public PackDisplay() {
		super(false, 0.5, 0.5);
		
		setNameTextColor((int) ((long) getFromFile("nameTextColor", nameTextColor.getRGB())));
		setNameTextChroma((boolean) getFromFile("nameTextChroma", nameTextColor.isChromaToggled()));
		setNameTextShadow((boolean) getFromFile("nameTextShadow", nameTextShadow));
		setShowDescription((boolean) getFromFile("showDescription", showDescription));
		setDescriptionTextColor((int) ((long) getFromFile("descriptionTextColor", descriptionTextColor.getRGB())));
		setDescriptionTextChroma((boolean) getFromFile("descriptionTextChroma", descriptionTextColor.isChromaToggled()));
		setDescriptionTextShadow((boolean) getFromFile("descriptionTextShadow", descriptionTextShadow));
		setShowBackground((boolean) getFromFile("showBackground", showBackground));
		setShowIcon((boolean) getFromFile("showIcon", showIcon));
		setShowAllSelectedPacks((boolean) getFromFile("showAllSelectedPacks", showAllSelectedPacks));
	}
	
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
			int offsetY = 0;

			if (showAllSelectedPacks) {
				for (int i = 0; i < selectedPacks.size(); i++) {
					ResourcePackRepository.Entry selectedPack = selectedPacks.get(i);
					
					drawSelectedPack(selectedPack, offsetY);
					
					offsetY += 28;
				}
			} else {
				ResourcePackRepository.Entry selectedPack = selectedPacks.get(0);
				
				drawSelectedPack(selectedPack, offsetY);
			}
		}
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		List<ResourcePackRepository.Entry> selectedPacks = mc.getResourcePackRepository().getRepositoryEntries();

		if (!selectedPacks.isEmpty()) {
			int offsetY = 0;

			if (showAllSelectedPacks) {
				for (int i = 0; i < selectedPacks.size(); i++) {
					ResourcePackRepository.Entry selectedPack = selectedPacks.get(i);
					
					drawSelectedPack(selectedPack, offsetY);
					
					offsetY += 28;
				}
			} else {
				ResourcePackRepository.Entry selectedPack = selectedPacks.get(0);
				
				drawSelectedPack(selectedPack, offsetY);
			}
		} else {
			drawDefaultPack();
		}
	}
	
	private void drawSelectedPack(ResourcePackRepository.Entry selectedPack, int offsetY) {
		if (showBackground) {
			drawRect(pos.getAbsoluteX() + (showIcon ? 28 : 0), pos.getAbsoluteY() + offsetY, pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() + offsetY + 28);
		}
		
		if (showIcon) {
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			
			selectedPack.bindTexturePackIcon(mc.getTextureManager());
			
			Gui.drawModalRectWithCustomSizedTexture(pos.getAbsoluteX(), pos.getAbsoluteY() + offsetY, 0.0F, 0.0F, 28, 28, 28, 28);
		}

		int packX = pos.getAbsoluteX() + 4 + (showIcon ? 28 : 0);
		int packNameY = pos.getAbsoluteY() + offsetY + (showDescription ? 4 : 28 / 2 - 4);
		
		String packName = selectedPack.getResourcePackName();
		String packDescription = selectedPack.getTexturePackDescription().replace("�r", "");

		drawText(packName, packX, packNameY, nameTextColor.getRGB(), nameTextShadow, !packName.contains("�") && nameTextColor.isChromaToggled());
				
		if (showDescription) {
			drawText(packDescription, packX, pos.getAbsoluteY() + offsetY + 28 - font.FONT_HEIGHT - 4, descriptionTextColor.getRGB(), descriptionTextShadow, !packDescription.contains("�") && descriptionTextColor.isChromaToggled());
		}
	}
	
	private void drawDefaultPack() {
		if (showBackground) {
			drawRect(pos.getAbsoluteX() + (showIcon ? 28 : 0), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() + 28);
		}
		
		if (showIcon) {
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			
			mc.getTextureManager().bindTexture(DefaultPack.getResourceLocation());
			
			Gui.drawModalRectWithCustomSizedTexture(pos.getAbsoluteX(), pos.getAbsoluteY(), 0.0F, 0.0F, 28, 28, 28, 28);
		}

		int packX = pos.getAbsoluteX() + 4 + (showIcon ? 28 : 0);
		int packNameY = pos.getAbsoluteY() + (showDescription ? 4 : 28 / 2 - 4);
		
		drawText(DefaultPack.getPackName(), packX, packNameY, nameTextColor.getRGB(), nameTextShadow, nameTextColor.isChromaToggled());
		
		if (showDescription) {
			drawText(DefaultPack.getPackDescription(), packX, pos.getAbsoluteY() + 28 - font.FONT_HEIGHT - 4, descriptionTextColor.getRGB(), descriptionTextShadow, descriptionTextColor.isChromaToggled());
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
	
	public void setNameTextColor(int rgb) {
		nameTextColor.setRGB(rgb);
		
		setToFile("nameTextColor", rgb);
	}
	
	public ColorManager getNameTextColor() {
		return nameTextColor;
	}
	
	public void setNameTextChroma(boolean enabled) {
		nameTextColor.setChromaToggled(enabled);
		
		setToFile("nameTextChroma", enabled);
	}
	
	public boolean isNameTextChromaToggled() {
		return nameTextColor.isChromaToggled();
	}
	
	public void setNameTextShadow(boolean enabled) {
		nameTextShadow = enabled;
		
		setToFile("nameTextShadow", enabled);
	}
	
	public boolean isNameTextShadowToggled() {
		return nameTextShadow;
	}
	
	public void setShowDescription(boolean toggled) {
		showDescription = toggled;
		
		setToFile("showDescription", toggled);
	}
	
	public boolean isShowDescriptionToggled() {
		return showDescription;
	}
	
	public void setDescriptionTextColor(int rgb) {
		descriptionTextColor.setRGB(rgb);
		
		setToFile("descriptionTextColor", rgb);
	}
	
	public ColorManager getDescriptionTextColor() {
		return descriptionTextColor;
	}
	
	public void setDescriptionTextChroma(boolean enabled) {
		descriptionTextColor.setChromaToggled(enabled);
		
		setToFile("descriptionTextChroma", enabled);
	}
	
	public boolean isDescriptionTextChromaToggled() {
		return descriptionTextColor.isChromaToggled();
	}
	
	public void setDescriptionTextShadow(boolean enabled) {
		descriptionTextShadow = enabled;
		
		setToFile("descriptionTextShadow", enabled);
	}
	
	public boolean isDescriptionTextShadowToggled() {
		return descriptionTextShadow;
	}
	
	public void setShowBackground(boolean enabled) {
		showBackground = enabled;
		
		setToFile("showBackground", enabled);
	}
	
	public boolean isShowBackgroundToggled() {
		return showBackground;
	}
	
	public void setShowIcon(boolean toggled) {
		showIcon = toggled;
		
		setToFile("showIcon", toggled);
	}
	
	public boolean isShowIconToggled() {
		return showIcon;
	}
	
	public void setShowAllSelectedPacks(boolean toggled) {
		showAllSelectedPacks = toggled;
		
		setToFile("showAllSelectedPacks", toggled);
	}
	
	public boolean isShowAllSelectedPacksToggled() {
		return showAllSelectedPacks;
	}
}
