package drop.mods.impl;

import java.awt.Color;
import java.util.List;

import drop.ColorManager;
import drop.gui.GuiSettings;
import drop.mods.hud.ScreenPosition;
import drop.mods.option.ParentOption;
import drop.mods.option.type.BooleanOption;
import drop.mods.option.type.ColorOption;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.util.ResourceLocation;
import drop.mods.ModDraggable;
import drop.mods.ModOptions;

public class PackDisplay extends ModDraggable {
	public PackDisplay() {
		super(false, 0.5, 0.5);
		
		this.options = new ModOptions(
				new ColorOption(this, "nameTextColor", ColorManager.fromColor(Color.WHITE, false), new GuiSettings(1, "Name Text Color", true, false)),
				new BooleanOption(this, "nameTextShadow", true, new GuiSettings(2, "Name Text Shadow")),
				new BooleanOption(this, "showDescription", false, new GuiSettings(3, "Show Description")),
				new ColorOption(this, "descriptionTextColor", ColorManager.fromColor(Color.GRAY, false), new ParentOption("showDescription"), new GuiSettings(4, "Description Text Color", true, false)),
				new BooleanOption(this, "descriptionTextShadow", true, new ParentOption("showDescription"), new GuiSettings(5, "Description Text Shadow")),
				new ColorOption(this, "backgroundColor", ColorManager.fromRGB(0, 0, 0, 102, false), new GuiSettings(6, "Background Color", false, true)),
				new BooleanOption(this, "showIcon", true, new GuiSettings(7, "Show Icon")),
				new BooleanOption(this, "showAllSelectedPacks", true, new GuiSettings(8, "Show All Selected Packs"))
				);
				
		saveOptions();
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
	public int getWidth() {
		int width = 8;
		
		if (options.getBooleanOption("showIcon").isToggled()) {
			width += 28;
		}
		
		if (mc.getResourcePackRepository().getRepositoryEntries().isEmpty()) {
			width += font.getStringWidth((options.getBooleanOption("showDescription").isToggled() ? DefaultPack.getPackDescription() : DefaultPack.getPackName()));
		} else {
			width += font.getStringWidth(getLongestPackText());
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

			if (options.getBooleanOption("showAllSelectedPacks").isToggled()) {
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

			if (options.getBooleanOption("showAllSelectedPacks").isToggled()) {
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
		drawRect(pos.getAbsoluteX() + (options.getBooleanOption("showIcon").isToggled() ? 28 : 0), pos.getAbsoluteY() + offsetY, pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() + offsetY + 28, options.getColorOption("backgroundColor").getColor().getRGB());
		
		if (options.getBooleanOption("showIcon").isToggled()) {
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			
			selectedPack.bindTexturePackIcon(mc.getTextureManager());
			
			Gui.drawModalRectWithCustomSizedTexture(pos.getAbsoluteX(), pos.getAbsoluteY() + offsetY, 0.0F, 0.0F, 28, 28, 28, 28);
		}

		int packX = pos.getAbsoluteX() + 4 + (options.getBooleanOption("showIcon").isToggled() ? 28 : 0);
		int packNameY = pos.getAbsoluteY() + offsetY + (options.getBooleanOption("showDescription").isToggled() ? 4 : 28 / 2 - 4);
		
		String packName = selectedPack.getResourcePackName();
		String packDescription = selectedPack.getTexturePackDescription().replace("�r", "");

		drawText(packName, packX, packNameY, options.getColorOption("nameTextColor").getColor().getRGB(), options.getBooleanOption("nameTextShadow").isToggled(), !packName.contains("�") && options.getColorOption("nameTextColor").getColor().isChromaToggled());
				
		if (options.getBooleanOption("showDescription").isToggled()) {
			drawText(packDescription, packX, pos.getAbsoluteY() + offsetY + 28 - font.FONT_HEIGHT - 4, options.getColorOption("descriptionTextColor").getColor().getRGB(), options.getBooleanOption("descriptionTextShadow").isToggled(), !packDescription.contains("�") && options.getColorOption("descriptionTextColor").getColor().isChromaToggled());
		}
	}
	
	private void drawDefaultPack() {
		drawRect(pos.getAbsoluteX() + (options.getBooleanOption("showIcon").isToggled() ? 28 : 0), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() + 28, options.getColorOption("backgroundColor").getColor().getRGB());
		
		if (options.getBooleanOption("showIcon").isToggled()) {
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			
			mc.getTextureManager().bindTexture(DefaultPack.getResourceLocation());
			
			Gui.drawModalRectWithCustomSizedTexture(pos.getAbsoluteX(), pos.getAbsoluteY(), 0.0F, 0.0F, 28, 28, 28, 28);
		}

		int packX = pos.getAbsoluteX() + 4 + (options.getBooleanOption("showIcon").isToggled() ? 28 : 0);
		int packNameY = pos.getAbsoluteY() + (options.getBooleanOption("showDescription").isToggled() ? 4 : 28 / 2 - 4);
		
		drawText(DefaultPack.getPackName(), packX, packNameY, options.getColorOption("nameTextColor").getColor().getRGB(), options.getBooleanOption("nameTextShadow").isToggled(), options.getColorOption("nameTextColor").getColor().isChromaToggled());
		
		if (options.getBooleanOption("showDescription").isToggled()) {
			drawText(DefaultPack.getPackDescription(), packX, pos.getAbsoluteY() + 28 - font.FONT_HEIGHT - 4, options.getColorOption("descriptionTextColor").getColor(), options.getBooleanOption("descriptionTextShadow").isToggled());
		}
	}
	
	private String getLongestPackText() {
		String longestName = getLongestSelectedPackName();
		String longestDescription = getLongestSelectedPackDescription();
		
		String longest = longestName;
		
		if (options.getBooleanOption("showDescription").isToggled()) {
			if (font.getStringWidth(longestDescription) > font.getStringWidth(longest)) {
				longest = longestDescription;
			}
		}
		
		return longest;
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
}
