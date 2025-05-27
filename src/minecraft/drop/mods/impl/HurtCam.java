package drop.mods.impl;

import drop.mods.Mod;

public class HurtCam extends Mod {
	private boolean hurtShake = true;
	private float hurtShakeIntensity = 14.0F;
	
	public HurtCam() {
		setHurtShake((boolean) getFromFile("hurtShake", hurtShake));
		setHurtShakeIntensity((float) ((double) getFromFile("hurtShakeIntensity", hurtShakeIntensity)));
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
