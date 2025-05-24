package drop.mods.impl;

import java.awt.Color;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import drop.ColorManager;
import net.minecraft.item.ItemStack;
import drop.gui.mod.hud.ScreenPosition;
import drop.mods.ModDraggableText;

public class Clock extends ModDraggableText {
	private boolean showBackground = false;
	
	public Clock() {
		setShowBackground((boolean) getFromFile("showBackground", showBackground));
	}
	
	@Override
	public int getWidth() {
		return 58;
	}

	@Override
	public int getHeight() {
		return 18;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (showBackground) {
			drawRect(
					pos.getAbsoluteX(),
					pos.getAbsoluteY(),
					pos.getAbsoluteX() + getWidth(),
					pos.getAbsoluteY() + getHeight(),
					ColorManager.fromColor(Color.BLACK).setAlpha(102).getRGB()
					);
		}
		
		drawCenteredText(getTimeText(), pos.getAbsoluteX(), pos.getAbsoluteY(), textColor.getRGB(), textShadow, textChroma);
	}
	
	private String getTimeText() {
		LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String text = currentTime.format(formatter);
		
		return showBackground ? text : "[" + text + "]";
	}
	
	public void setShowBackground(boolean enabled) {
		showBackground = enabled;
		
		setToFile("showBackground", enabled);
	}
	
	public boolean isShowBackgroundEnabled() {
		return showBackground;
	}
}
