package drop.mods.impl;

import drop.gui.hud.ScreenPosition;
import drop.mods.option.ParentOption;
import drop.mods.option.type.BooleanOption;
import drop.mods.option.type.BracketsOption;
import drop.mods.option.type.ColorOption;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.server.S19PacketEntityStatus;

import java.awt.Color;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import drop.event.EventTarget;
import drop.event.impl.EntityAttackEvent;
import drop.event.impl.EntityDamageEvent;
import drop.gui.GuiSettings;
import drop.mods.ModColor;
import drop.mods.ModDraggable;
import drop.mods.ModOptions;
import drop.mods.option.Brackets;

public class ComboCounter extends ModDraggable {
	public ComboCounter() {
		super(false, 0.5, 0.5);
		
		this.options = new ModOptions(
				new ColorOption(this, "textColor", ModColor.fromColor(Color.WHITE, false), new GuiSettings(1, "Text Color", true, false)),
				new BooleanOption(this, "textShadow", true, new GuiSettings(2, "Text Shadow")),
				new BooleanOption(this, "showBackground", false, new GuiSettings(3, "Background")),
				new ColorOption(this, "backgroundColor", ModColor.fromRGB(0, 0, 0, 102, false), new ParentOption("showBackground"), new GuiSettings(4, "Background Color", false, true)),
				new BooleanOption(this, "showBorder", false, new ParentOption("showBackground"), new GuiSettings(6, "Border")),
				new ColorOption(this, "borderColor", ModColor.fromRGB(0, 0, 0, 255, false), new ParentOption("showBorder"), new GuiSettings(7, "Border Color", false, true)),
				new BracketsOption(this, "brackets", Brackets.SQUARE, new ParentOption("showBackground", true), new GuiSettings(5, "Brackets"))
				);
				
		saveOptions();
	}

	private int targetId;
	private int combo = 0;
	private long lastCombo;
	
	@Override
	public int getWidth() {
		return options.getBooleanOption("showBackground").isToggled() ? 58 : font.getStringWidth(options.getBracketsOption("brackets").wrap("0 combo"));
	}

	@Override
	public int getHeight() {
		return options.getBooleanOption("showBackground").isToggled() ? 18 : font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (mc.thePlayer.hurtTime > 3 || System.currentTimeMillis() - lastCombo >= 5000) {
			combo = 0;
		}
		
		if (options.getBooleanOption("showBackground").isToggled()) {
	    	getBounds().fill(options.getColorOption("backgroundColor").getColor().getRGB());
	    	
	    	if (options.getBooleanOption("showBorder").isToggled()) {
		    	getBounds().stroke(options.getColorOption("borderColor").getColor().getRGB());
	    	}
	    	
			drawCenteredText(combo + " combo", pos.getAbsoluteX(), pos.getAbsoluteY(), options.getColorOption("textColor").getColor(), options.getBooleanOption("textShadow").isToggled());
    	} else {
		    drawAlignedText(options.getBracketsOption("brackets").wrap(combo + " combo"), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, options.getColorOption("textColor").getColor(), options.getBooleanOption("textShadow").isToggled());
    	}
	}

	@Override
	public void renderDummy(ScreenPosition pos) {
		if (options.getBooleanOption("showBackground").isToggled()) {
	    	getBounds().fill(options.getColorOption("backgroundColor").getColor().getRGB());
	    	
	    	if (options.getBooleanOption("showBorder").isToggled()) {
		    	getBounds().stroke(options.getColorOption("borderColor").getColor().getRGB());
	    	}
	    	
			drawCenteredText("0 combo", pos.getAbsoluteX(), pos.getAbsoluteY(), options.getColorOption("textColor").getColor(), options.getBooleanOption("textShadow").isToggled());
    	} else {
		    drawAlignedText(options.getBracketsOption("brackets").wrap("0 combo"), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, options.getColorOption("textColor").getColor(), options.getBooleanOption("textShadow").isToggled());
    	}
	}

	@EventTarget
	public void onEntityAttack(EntityAttackEvent event) {
		this.targetId = event.getEntity().getEntityId();
	}
	
	@EventTarget
	public void onEntityDamage(EntityDamageEvent event) {
		if (event.getEntity().getEntityId() == targetId) {
			dealHit();
		} else if (event.getEntity() == mc.thePlayer) {
			takeHit();
		}
	}
	
	private void dealHit() {
		targetId = -1;
		combo++;
		lastCombo = System.currentTimeMillis();
	}
	
	private void takeHit() {
		combo = 0;
	}
}
