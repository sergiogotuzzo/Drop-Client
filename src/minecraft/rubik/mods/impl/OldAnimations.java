package rubik.mods.impl;

import rubik.mods.Mod;

public class OldAnimations extends Mod {
	private boolean oldFishingRod = true;
	private boolean oldBow = true;
	private boolean blockHit = true;
	
	public void setOldFishingRod(boolean enabled) {
		this.oldFishingRod = enabled;
	}
	
	public boolean isOldFishingRodEnabled() {
		return oldFishingRod;
	}
	
	public void setOldBow(boolean enabled) {
		this.oldBow = enabled;
	}
	
	public boolean isOldBowEnabled() {
		return oldBow;
	}
	
	public void setBlockHit(boolean enabled) {
		this.blockHit = enabled;
	}
	
	public boolean isBlockHitEnabled() {
		return blockHit;
	}
}
