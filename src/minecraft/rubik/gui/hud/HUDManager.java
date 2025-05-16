package rubik.gui.hud;

import java.util.Collection;
import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import rubik.events.EventManager;
import rubik.events.EventTarget;
import rubik.events.impl.RenderEvent;
import rubik.gui.GuiModPositioning;

public class HUDManager {
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
		if (mc.gameSettings.showDebugInfo) return;
		
		if (mc.currentScreen == null || mc.currentScreen instanceof GuiScreen && !(mc.currentScreen instanceof GuiModPositioning)) {
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
