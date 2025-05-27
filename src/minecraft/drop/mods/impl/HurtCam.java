package drop.mods.impl;

import drop.mods.Mod;

public class HurtCam extends Mod {
	private boolean hurtShake = true;
	
	public HurtCam() {
		setHurtShake((boolean) getFromFile("hurtShake", hurtShake));
	}
	
	public void setHurtShake(boolean toggled) {
		this.hurtShake = toggled;
		
		setToFile("hurtShake", hurtShake);
	}
	
	public boolean isHurtShakeToggled() {
		return hurtShake;
	}
}
