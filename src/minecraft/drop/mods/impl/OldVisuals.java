package drop.mods.impl;

import net.minecraft.entity.Entity;

import java.awt.Color;

import drop.ColorManager;
import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiOldVisuals;
import drop.mods.Mod;

public class OldVisuals extends Mod {
	private boolean fishingRod = true;
	private boolean bow = true;
	private boolean blockHitting = true;
	private boolean armorHitAnimation = true;
	private ColorManager hitArmorColor = ColorManager.fromColor(Color.RED, false).setAlpha(76);
	
	public OldVisuals() {
		super(true);
		
		setFishingRod((boolean) getFromFile("fishingRod", fishingRod));
		setBow((boolean) getFromFile("bow", bow));
		setBlockHitting((boolean) getFromFile("blockHitting", blockHitting));
		setArmorHitAnimation((boolean) getFromFile("armorHitAnimation", armorHitAnimation));
		setHitArmorColor((int) ((long) getFromFile("hitArmorColor", hitArmorColor.getRGB())));
		setHitArmorChroma((boolean) getFromFile("hitArmorChroma", hitArmorColor.isChromaToggled()));
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiOldVisuals(previousGuiScreen);
	}
	
	public void setFishingRod(boolean enabled) {
		this.fishingRod = enabled;
		
		setToFile("fishingRod", enabled);
	}
	
	public boolean isFishingRodToggled() {
		return fishingRod;
	}
	
	public void setBow(boolean enabled) {
		this.bow = enabled;
		
		setToFile("bow", enabled);
	}
	
	public boolean isBowToggled() {
		return bow;
	}
	
	public void setBlockHitting(boolean enabled) {
		this.blockHitting = enabled;
		
		setToFile("blockHitting", enabled);
	}
	
	public boolean isBlockHittingToggled() {
		return blockHitting;
	}
	
	public void setArmorHitAnimation(boolean enabled) {
		this.armorHitAnimation = enabled;
		
		setToFile("armorHitAnimation", enabled);
	}
	
	public boolean isArmorHitAnimationToggled() {
		return armorHitAnimation;
	}
	
	public void setHitArmorColor(int rgb) {
		this.hitArmorColor = ColorManager.fromRGB(rgb, hitArmorColor.isChromaToggled());
		
		setToFile("hitArmorColor", rgb);
	}
	
	public ColorManager getHitArmorColor() {
		return hitArmorColor;
	}
	
	public void setHitArmorChroma(boolean enabled) {
		hitArmorColor.setChromaToggled(enabled);
		
		setToFile("hitArmorChroma", enabled);
	}
	
	public boolean isHitArmorChromaToggled() {
		return hitArmorColor.isChromaToggled();
	}
}
