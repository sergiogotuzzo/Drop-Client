package drop.mods.impl;

import java.util.List;

import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiPackDisplay;
import drop.mods.hud.ScreenPosition;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.ResourcePackRepository;
import drop.mods.ModDraggableText;

public class PackDisplay extends ModDraggableText {
	private boolean showBackground = true;
	private boolean showIcon = true;
	private boolean showDescription = true;
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiPackDisplay(previousGuiScreen);
	}
	
	@Override
	public int getWidth() {
		return font.getStringWidth(showDescription ? getLongestSelectedPackDescription() : getLongestSelectedPackName()) + 4 * 2 + (showIcon ? 32 : 0);
	}

	@Override
	public int getHeight() {
		return mc.getResourcePackRepository().getRepositoryEntries().isEmpty() ? 28 : 28 * mc.getResourcePackRepository().getRepositoryEntries().size();
	}

	@Override
	public void render(ScreenPosition pos) {
		if (showBackground) {
			drawRect(pos.getAbsoluteX() + (showIcon ? 28 : 0), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() + getHeight());
		}
		
		List<ResourcePackRepository.Entry> selectedPacks = mc.getResourcePackRepository().getRepositoryEntries();
		
		if (!selectedPacks.isEmpty()) {
			int offsetY = 0;
			
			for (int i = 0; i < selectedPacks.size(); i++) {
				ResourcePackRepository.Entry selectedPack = selectedPacks.get(i);
				
				drawSelectedPack(selectedPack, offsetY);
				
				offsetY += 28;
			}
		}
	}
	
	private void drawSelectedPack(ResourcePackRepository.Entry selectedPack, int offsetY) {		
		if (showIcon) {
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			
			selectedPack.bindTexturePackIcon(mc.getTextureManager());
			
			Gui.drawModalRectWithCustomSizedTexture(pos.getAbsoluteX(), pos.getAbsoluteY() + offsetY, 0.0F, 0.0F, 28, 28, 28, 28);
		}

		int packX = pos.getAbsoluteX() + 4 + (showIcon ? 28 : 0);
		int packNameY = pos.getAbsoluteY() + offsetY + (showDescription ? 4 : (28 - font.FONT_HEIGHT) / 2);
		String packName = selectedPack.getTexturePackDescription().replace("§r", "");

		drawText(selectedPack.getResourcePackName(), packX, packNameY, textColor, textShadow, packName.contains("§") ? false : textChroma);
				
		if (showDescription) {
			drawText(packName, packX, pos.getAbsoluteY() + offsetY + 28 - font.FONT_HEIGHT - 4, textColor, textShadow, packName.contains("§") ? false : textChroma);
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
