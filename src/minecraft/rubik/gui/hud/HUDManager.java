package rubik.gui.hud;

import java.util.Collection;
import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.inventory.GuiContainer;
import rubik.event.EventManager;
import rubik.event.EventTarget;
import rubik.event.impl.RenderEvent;

public class HUDManager {
	private HUDManager() {
		
	};
	
	private static HUDManager hudManager = null;
	
	public static HUDManager getInstance() {
		if (hudManager != null) {
			return hudManager;
		}
		
		hudManager = new HUDManager();
		
		EventManager.register(hudManager);
		
		return hudManager;
	}
	
	private Set<IRenderer> registeredRenderers = Sets.newHashSet();
	private Minecraft mc = Minecraft.getMinecraft();
	
	public void register(IRenderer... renderers) {
		for (IRenderer renderer : renderers) {
			registeredRenderers.add(renderer);
		}
	}
	
	public void unregister(IRenderer... renderers) {
		for (IRenderer renderer : renderers) {
			registeredRenderers.remove(renderer);
		}
	}
	
	public Collection<IRenderer> getRegisteredRenderers() {
		return Sets.newHashSet(registeredRenderers);
	}
	
	@EventTarget
	public void onRender(RenderEvent e) {
		if (mc.currentScreen == null || mc.currentScreen instanceof GuiChat) {
			for (IRenderer renderer : registeredRenderers) {
				callRenderer(renderer);
			}
		}
	}

	private void callRenderer(IRenderer renderer) {
		if (!renderer.isEnabled()) {
			return;
		}
		
		ScreenPosition pos = renderer.load();
		
		if (pos == null) {
			pos = ScreenPosition.fromRelativePosition(0.5, 0.5);
		}
		
		renderer.render(pos);
	}
}
