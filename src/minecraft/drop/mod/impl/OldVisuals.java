package drop.mod.impl;

import net.minecraft.entity.Entity;

import drop.gui.GuiSettings;
import drop.mod.Mod;
import drop.mod.ModOptions;
import drop.mod.option.type.BooleanOption;

public class OldVisuals extends Mod {
	public OldVisuals() {
		super(true);
		
		this.options = new ModOptions(
				new BooleanOption(this, "fishingRod", true, new GuiSettings(1, "Fishing Rod")),
				new BooleanOption(this, "bow", true, new GuiSettings(2, "Bow")),
				new BooleanOption(this, "blockHitting", true, new GuiSettings(3, "Block Hitting")),
				new BooleanOption(this, "sneaking", true, new GuiSettings(5, "Sneaking")),
				new BooleanOption(this, "armorHitAnimation", true, new GuiSettings(4, "Armor Hit Animation"))
				);
		
		saveOptions();
	}
	
	private long sneak = 0L;
    private boolean isSneaking = false;
    private int value = 0;
    
    public float getCustomEyeHeight(Entity entity) {
    	if (!options.getBooleanOption("sneaking").isToggled()) {
    		return entity.getEyeHeight();
    	}
    	
        if (isSneaking != entity.isSneaking() || sneak <= 0L) {
            sneak = System.currentTimeMillis();
        }

        isSneaking = entity.isSneaking();
        float f = 1.62F;

        if (entity.isSneaking()) {
            int i = (int) (sneak + 8L - System.currentTimeMillis());

            if (i > -50) {
                f = (float) (f + i * 0.0017D);

                if (f < 0.0F || f > 10.0F) {
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
}
