package drop.mods.impl;

import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiVisualTweaks;
import drop.mods.Mod;

public class VisualTweaks extends Mod {
	private boolean minimalViewBobbing = true;
	private boolean leftHand = false;
	private boolean hurtShake = true;
	private float hurtShakeIntensity = 14.0F;
	
	public VisualTweaks() {
		setMinimalViewBobbing((boolean) getFromFile("minimalViewBobbing", minimalViewBobbing));
		setLeftHand((boolean) getFromFile("leftHand", leftHand));
		setHurtShake((boolean) getFromFile("hurtShake", hurtShake));
		setHurtShakeIntensity((float) ((double) getFromFile("hurtShakeIntensity", hurtShakeIntensity)));
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiVisualTweaks(previousGuiScreen);
	}
	
	public void setMinimalViewBobbing(boolean toggled) {
		this.minimalViewBobbing = toggled;
		
		setToFile("minimalViewBobbing", minimalViewBobbing);
	}
	
	public boolean isMinimalViewBobbingToggled() {
		return minimalViewBobbing;
	}
	
	public void setLeftHand(boolean toggled) {
		this.leftHand = toggled;
		
		setToFile("leftHand", leftHand);
	}
	
	public boolean isLeftHandToggled() {
		return leftHand;
	}
	
	public void setHurtShake(boolean toggled) {
		this.hurtShake = toggled;
		
		setToFile("hurtShake", hurtShake);
	}
	
	public boolean isHurtShakeToggled() {
		return hurtShake;
	}
	
	public void setHurtShakeIntensity(float intensity) {
		this.hurtShakeIntensity = intensity;
		
		setToFile("hurtShakeIntensity", hurtShakeIntensity);
	}
	
	public float getHurtShakeIntensity() {
		return hurtShakeIntensity;
	}
}
