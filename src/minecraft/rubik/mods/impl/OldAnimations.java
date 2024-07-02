package rubik.mods.impl;

import net.minecraft.entity.Entity;
import rubik.mods.Mod;

public class OldAnimations extends Mod {
	private static long sneak = 0L;
    private static boolean is = false;
    private static int value = 0;
    
	private boolean oldFishingRod = true;
	private boolean oldBow = true;
	private boolean blockHit = true;
	private boolean oldSneaking = true;
	
	public OldAnimations() {
		setOldFishingRod((boolean) getFromFile("showBackground", oldFishingRod));
		setOldBow((boolean) getFromFile("textShadow", oldBow));
		setBlockHit((boolean) getFromFile("blockHit", blockHit));
	}
	
	public float getCustomEyeHeight(Entity entity)
    {
        if (is != entity.isSneaking() || sneak <= 0L)
        {
            sneak = System.currentTimeMillis();
        }

        is = entity.isSneaking();
        
        float f = 1.62F;

        if (entity.isSneaking()) {
            int i = (int) (sneak + 8L - System.currentTimeMillis());

            if (i > -50) {
                f = (float) (f + i * 0.0017D);

                if (f < 0.0F || f > 10.0F)
                {
                    f = 1.54F;
                }
            } else {
                f = (float) (f - 0.08D);
            }
        } else {
            int j = (int) (sneak + 8L - System.currentTimeMillis());

            if (j > -50) {
                f = (float) (f - j * 0.0017D);
                f = (float) (f - 0.08D);

                if (f < 0.0F) {
                    f = 1.62F;
                }
            } else {
                f = f - 0.0F;
            }
        }

        return f;
    }
	
	public void setOldFishingRod(boolean enabled) {
		this.oldFishingRod = enabled;
		
		setToFile("oldFishingRod", enabled);
	}
	
	public boolean isOldFishingRodEnabled() {
		return oldFishingRod;
	}
	
	public void setOldBow(boolean enabled) {
		this.oldBow = enabled;
		
		setToFile("oldBow", enabled);
	}
	
	public boolean isOldBowEnabled() {
		return oldBow;
	}
	
	public void setBlockHit(boolean enabled) {
		this.blockHit = enabled;
		
		setToFile("blockHit", enabled);
	}
	
	public boolean isBlockHitEnabled() {
		return blockHit;
	}
	
	public void setOldSneaking(boolean enabled) {
		this.oldSneaking = enabled;
		
		setToFile("oldSneaking", enabled);
	}
	
	public boolean isOldSneakingEnabled() {
		return oldSneaking;
	}
}
