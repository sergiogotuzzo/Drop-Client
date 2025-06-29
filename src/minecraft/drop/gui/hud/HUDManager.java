package drop.gui.hud;

import java.util.Collection;
import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import drop.event.EventManager;
import drop.event.EventTarget;
import drop.event.impl.RenderEvent;
import drop.gui.mod.GuiModPositioning;

public class HUDManager {
	private static HUDManager instance = null;
	
	public static HUDManager getInstance() {
		if (instance != null) {
			return instance;
		}
		
		instance = new HUDManager();
		
		EventManager.register(instance);
		
		return instance;
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
		
		ScreenPosition pos = renderer.getPosition();
		
		if (pos == null) {
			pos = ScreenPosition.fromRelativePosition(0.5, 0.5);
		}
		
		renderer.render(pos);
	}
}
