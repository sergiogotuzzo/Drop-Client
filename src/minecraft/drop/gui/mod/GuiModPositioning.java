package drop.gui.mod;

import java.awt.Color;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Predicate;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import drop.gui.GuiDropClientScreen;
import drop.gui.hud.HUDManager;
import drop.gui.hud.IRenderer;
import drop.gui.hud.ScreenPosition;

public class GuiModPositioning extends GuiDropClientScreen {
	private final HashMap<IRenderer, ScreenPosition> renderers = new HashMap<IRenderer, ScreenPosition>();
	
	private Optional<IRenderer> selectedRenderer = Optional.empty();
	
	private int prevX;
	private int prevY;
	
	public static final int marginX = 2;
	public static final int marginY = 2;
	
	public GuiModPositioning(HUDManager manager) {
		Collection<IRenderer> registeredRenderers = manager.getRegisteredRenderers();
		
		for (IRenderer renderer : registeredRenderers) {
			if (!renderer.isEnabled()) {
				continue;
			}
			
			ScreenPosition pos = renderer.getPosition();
			
			if (pos == null) {
				pos = ScreenPosition.fromRelativePosition(0.5, 0.5);
			}
			
			adjustBounds(renderer, pos);
			
			this.renderers.put(renderer, pos);
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {		
	    final float zBackup = this.zLevel;
	    
	    this.zLevel = 200.0F;
	    
	    for (IRenderer renderer : renderers.keySet()) {
	        ScreenPosition pos = renderers.get(renderer);
	        	        
	        renderer.renderDummy(pos);
	    }
	    
	    if (selectedRenderer.isPresent()) {	    	
	    	ScreenPosition pos = renderers.get(selectedRenderer.get());

	    	int gap = 10;
	        
	    	if ((pos.getAbsoluteX() + selectedRenderer.get().getWidth()) >= this.width / 2 - gap && pos.getAbsoluteX() <= this.width / 2 + gap) {
	    		drawVerticalLine(this.width / 2, 0, this.height, Color.WHITE.getRGB());
	    	}
	        
	    	if ((pos.getAbsoluteY() + selectedRenderer.get().getHeight()) >= this.height / 2 - gap && pos.getAbsoluteY() <= this.height / 2 + gap) {
	    		drawHorizontalLine(0, this.width - 1, this.height / 2, Color.WHITE.getRGB());
	    	}
	    	
		    drawHollowRect(pos.getAbsoluteX(), pos.getAbsoluteY(), selectedRenderer.get().getWidth(), selectedRenderer.get().getHeight(), Color.CYAN.getRGB());
	    }

	    this.zLevel = zBackup;
	    
	    super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if (keyCode == Keyboard.KEY_ESCAPE) {
			renderers.entrySet().forEach((entry) -> {
				entry.getKey().setPosition(entry.getValue());
			});
			
			this.mc.displayGuiScreen(null);
		}
	}
	
	@Override
	protected void mouseClicked(int x, int y, int mouseButton) throws IOException {
		this.prevX = x;
		this.prevY = y;
		
		loadMouseOver(x, y);
	}
	
	@Override
	protected void mouseClickMove(int x, int y, int button, long time) {
		if (selectedRenderer.isPresent()) {
			moveSelectedRenderBy(x - prevX, y - prevY);
		}
		
		this.prevX = x;
		this.prevY = y;
	}

	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
		
		for (IRenderer renderer : renderers.keySet()) {
			renderer.setPosition(renderers.get(renderer));
		}
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return true;
	}
	
	private void adjustBounds(IRenderer renderer, ScreenPosition pos) {
		ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
		
		int screenWidth = res.getScaledWidth();
		int screenHeight = res.getScaledHeight();
		
		int absoluteX = Math.max(marginX, Math.min(pos.getAbsoluteX(), Math.max(screenWidth - renderer.getWidth() - marginX - 1, 0)));
		int absoluteY = Math.max(marginY, Math.min(pos.getAbsoluteY(), Math.max(screenHeight - renderer.getHeight() - marginY - 1, 0)));
		
		pos.setAbsolute(absoluteX, absoluteY);
	}

	private void moveSelectedRenderBy(int offsetX, int offsetY) {
		IRenderer renderer = selectedRenderer.get();
		ScreenPosition pos = renderers.get(renderer);
		
		pos.setAbsolute(pos.getAbsoluteX() + offsetX, pos.getAbsoluteY() + offsetY);
		
		adjustBounds(renderer, pos);
	}
	
	private boolean isMouseOver(IRenderer renderer, int mouseX, int mouseY) {
	    ScreenPosition pos = renderers.get(renderer);
	    
	    int absoluteX = pos.getAbsoluteX();
		int absoluteY = pos.getAbsoluteY();
	    
	    return (mouseX >= absoluteX && mouseX <= absoluteX + renderer.getWidth()) && (mouseY >= absoluteY && mouseY <= absoluteY + renderer.getHeight());
	}

	private void loadMouseOver(int x, int y) {
		selectedRenderer = renderers.keySet().stream().filter(renderer -> isMouseOver(renderer, x, y)).findFirst();
	}
}
