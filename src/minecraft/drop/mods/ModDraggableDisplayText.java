package drop.mods;

import java.awt.Color;

import drop.ColorManager;
import drop.gui.GuiDropClientScreen;

public abstract class ModDraggableDisplayText extends ModDraggableText {
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
	protected Brackets brackets = Brackets.SQUARE;
	
	public ModDraggableDisplayText() {
		setShowBackground((boolean) getFromFile("showBackground", showBackground));
		setBrackets(Brackets.fromId((int) ((long) getFromFile("brackets", brackets.getId()))));
	}
	
	public void setShowBackground(boolean enabled) {
		showBackground = enabled;
		
		setToFile("showBackground", enabled);
	}
	
	public boolean isShowBackgroundEnabled() {
		return showBackground;
	}
	
	public void setBrackets(Brackets brackets) {
		this.brackets = brackets;
		
		setToFile("brackets", brackets.getId());
	}
	
	public Brackets getBrackets() {
		return brackets;
	}
	
	@Override
	public void drawCenteredText(String text, int x, int y, int color, boolean dropShadow, boolean chroma) {
		drawText(text, x + (getWidth() - font.getStringWidth(text)) / 2 + (showBackground ? 0 : 1), y + getHeight() / 2 - 4 + (showBackground ? 0 : 1), color, dropShadow, chroma);
	}
}
