package rubik.mods;

import rubik.gui.hud.IRenderer;
import rubik.gui.hud.ScreenPosition;

public abstract class ModDraggable extends Mod implements IRenderer {
	protected ScreenPosition pos;
	
	public void save(ScreenPosition pos) {
		this.pos = pos;
	}
	
	public ScreenPosition load() {
		return pos;
	}
	
	public final int getLineOffset(ScreenPosition pos, int lineNum) {
		return pos.getAbsoluteY() + getLineOffset(lineNum);
	}
	
	private int getLineOffset(int lineNum) {
		return (font.FONT_HEIGHT + 3) * lineNum;
	}
}
