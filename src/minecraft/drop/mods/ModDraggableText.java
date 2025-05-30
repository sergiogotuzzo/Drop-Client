package drop.mods;

import java.awt.Color;

import drop.ColorManager;
import drop.gui.GuiDropClientScreen;
import drop.gui.mod.hud.ScreenPosition;

public abstract class ModDraggableText extends ModDraggable {
	public static enum Brackets {
	    NONE(0, "", ""),
	    ROUND(1, "(", ")"),
	    SQUARE(2, "[", "]"),
	    CURLY(3, "{", "}"),
		ANGULAR(4, "<", ">");

	    private final int id;
	    private final String open;
	    private final String close;

	    Brackets(int id, String open, String close) {
	        this.id = id;
	        this.open = open;
	        this.close = close;
	    }

	    public int getId() {
	        return id;
	    }

	    public String getOpen() {
	        return open;
	    }

	    public String getClose() {
	        return close;
	    }

	    public String wrap(String text) {
	        return open + text + close;
	    }
	    
	    public String getName() {
	    	return id == Brackets.NONE.getId() ? "None" : open + close;
	    }
	    
	    public static Brackets fromId(int id) {
	        for (Brackets b : values()) {
	            if (b.id == id) {
	            	return b;
	            }
	        }
	        
	        return NONE;
	    }
	}

	protected boolean showBackground = false;
	protected ColorManager textColor = ColorManager.fromColor(Color.WHITE);
	protected boolean textShadow = true;
	protected boolean textChroma = false;
	protected Brackets brackets = Brackets.SQUARE;
	
	public ModDraggableText() {
		setShowBackground((boolean) getFromFile("showBackground", showBackground));
		setTextColor((int) ((long) getFromFile("textColor", textColor.getRGB())));
		setTextShadow((boolean) getFromFile("textShadow", textShadow));
		setTextChroma((boolean) getFromFile("textChroma", textChroma));
		setBrackets(Brackets.fromId((int) ((long) getFromFile("brackets", brackets.getId()))));
	}
	
	public void setShowBackground(boolean enabled) {
		showBackground = enabled;
		
		setToFile("showBackground", enabled);
	}
	
	public boolean isShowBackgroundEnabled() {
		return showBackground;
	}
	
	public void setTextColor(int rgb) {
		this.textColor = ColorManager.fromRGB(rgb);
		
		setToFile("textColor", rgb);
	}
	
	public ColorManager getTextColor() {
		return textColor;
	}
	
	public void setTextShadow(boolean enabled) {
		textShadow = enabled;
		
		setToFile("textShadow", enabled);
	}
	
	public boolean isTextShadowEnabled() {
		return textShadow;
	}
	
	public void setTextChroma(boolean enabled) {
		this.textChroma = enabled;
		
		setToFile("textChroma", enabled);
	}
	
	public boolean isTextChromaEnabled() {
		return textChroma;
	}
	
	public void setBrackets(Brackets brackets) {
		this.brackets = brackets;
		
		setToFile("brackets", brackets.getId());
	}
	
	public Brackets getBrackets() {
		return brackets;
	}
}
