package drop.mods.impl;

import java.awt.Color;

import drop.mods.Mod;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.network.NetworkPlayerInfo;

public class TabOverlay extends Mod {
	private boolean showPlayerHeads = true;
	private boolean pingIcon = true;
	
	public TabOverlay() {
		setShowPlayerHeads((boolean) getFromFile("showPlayerHeads", showPlayerHeads));
		setPingIcon((boolean) getFromFile("pingIcon", pingIcon));
	}

	public static void writePing(FontRenderer font, int p_175245_1_, int p_175245_2_, int p_175245_3_, NetworkPlayerInfo networkPlayerInfoIn) {
		Color color = Color.WHITE;
		int ping = networkPlayerInfoIn.getResponseTime();
		
		if (ping > 300) {
			color = new Color(170, 0, 0);
		} else if (ping > 200) {
			color = new Color(255, 85, 85);
		} else if (ping > 150) {
			color = new Color(255, 170, 0);
		} else if (ping > 100) {
			color = new Color(255, 255, 85);
		} else {
			color = new Color(85, 255, 85);
		}
		
		String pingText = String.valueOf(ping);
		
		font.drawString(pingText, p_175245_2_ + p_175245_1_ - font.getStringWidth(pingText), p_175245_3_, color.getRGB(), true);
	}

	public void setShowPlayerHeads(boolean enabled) {
		this.showPlayerHeads = enabled;
		
		setToFile("showPlayerHeads", showPlayerHeads);
	}
	
	public boolean isShowPlayerHeadsEnabled() {
		return showPlayerHeads;
	}

	public void setPingIcon(boolean toggled) {
		this.pingIcon = toggled;
		
		setToFile("pingIcon", pingIcon);
	}
	
	public boolean isPingIconToggled() {
		return pingIcon;
	}
}
