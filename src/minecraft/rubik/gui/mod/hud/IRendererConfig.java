package rubik.gui.mod.hud;

public interface IRendererConfig {
	public void save(ScreenPosition pos);
	
	public ScreenPosition load();
}
