package rubik.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;
import rubik.Client;
import rubik.gui.hud.IRenderer;
import rubik.gui.mods.GuiArmorStatus;
import rubik.gui.mods.GuiBlockOverlay;
import rubik.gui.mods.GuiCPSDisplay;
import rubik.gui.mods.GuiChat;
import rubik.gui.mods.GuiCoordinatesDisplay;
import rubik.gui.mods.GuiFPSDisplay;
import rubik.gui.mods.GuiFreelook;
import rubik.gui.mods.GuiFullbright;
import rubik.gui.mods.GuiKeystrokes;
import rubik.gui.mods.GuiOldAnimations;
import rubik.gui.mods.GuiPingDisplay;
import rubik.gui.mods.GuiPotionEffects;
import rubik.gui.mods.GuiScoreboard;
import rubik.gui.mods.GuiToggleSprintSneak;
import rubik.mods.Mod;
import rubik.mods.ModInstances;

public class GuiListMods extends GuiListExtended {
	private final List<ModEntry> entrys = new ArrayList<ModEntry>();

	public GuiListMods(Minecraft mcIn, GuiModsListNew inGui) {
		super(mcIn, inGui.width, inGui.height, 63, inGui.height - 32, 20);

		entrys.add(new ModEntry(inGui, ModInstances.getArmorStatusMod(), new GuiArmorStatus(inGui)));
		entrys.add(new ModEntry(inGui, ModInstances.getBlockOverlayMod(), new GuiBlockOverlay(inGui)));
		entrys.add(new ModEntry(inGui, ModInstances.getChatMod(), new GuiChat(inGui)));
		entrys.add(new ModEntry(inGui, ModInstances.getCoordinatesDisplayMod(), new GuiCoordinatesDisplay(inGui)));
		entrys.add(new ModEntry(inGui, ModInstances.getCPSDisplayMod(), new GuiCPSDisplay(inGui)));
		entrys.add(new ModEntry(inGui, ModInstances.getFPSDisplayMod(), new GuiFPSDisplay(inGui)));
		entrys.add(new ModEntry(inGui, ModInstances.getFreelookMod(), new GuiFreelook(inGui)));
		entrys.add(new ModEntry(inGui, ModInstances.getFullbrightMod(), new GuiFullbright(inGui)));
		entrys.add(new ModEntry(inGui, ModInstances.getKeystrokesMod(), new GuiKeystrokes(inGui)));
		entrys.add(new ModEntry(inGui, ModInstances.getOldAnimationsMod(), new GuiOldAnimations(inGui)));
		entrys.add(new ModEntry(inGui, ModInstances.getPingDisplayMod(), new GuiPingDisplay(inGui)));
		entrys.add(new ModEntry(inGui, ModInstances.getPotionEffectsMod(), new GuiPotionEffects(inGui)));
		entrys.add(new ModEntry(inGui, ModInstances.getScoreboardMod(), new GuiScoreboard(inGui)));
		entrys.add(new ModEntry(inGui, ModInstances.getToggleSprintSneakMod(), new GuiToggleSprintSneak(inGui)));
	}

	@Override
	public IGuiListEntry getListEntry(int index) {
		return entrys.get(index);
	}

	@Override
	protected int getSize() {
		return entrys.size();
	}
}