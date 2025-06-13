package drop.mods;

import java.awt.Color;

import drop.ColorManager;
import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiModDraggableDisplayText;
import drop.mods.hud.ScreenPosition;

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
	protected String dummyText = "";
	
	public ModDraggableDisplayText(boolean enabled, double relativeX, double relativeY, String dummyText) {
		super(enabled, relativeX, relativeY);
		
		this.dummyText = dummyText;
		
		setShowBackground(getBooleanFromFile("showBackground", showBackground));
		setBrackets(Brackets.fromId(getIntFromFile("brackets", brackets.getId())));
	}

	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiModDraggableDisplayText(previousGuiScreen, this);
	}
	
	@Override
	public int getWidth() {
		return showBackground ? 58 : font.getStringWidth(brackets.wrap(dummyText));
	}

	@Override
	public int getHeight() {
		return showBackground ? 18 : font.FONT_HEIGHT;
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		drawTextToRender(pos, dummyText);
	}
	
	@Override
	public void drawCenteredText(String text, int x, int y, int color, boolean dropShadow, boolean chroma) {
		drawText(text, x + (getWidth() - font.getStringWidth(text)) / 2 + (showBackground ? 0 : 1), y + getHeight() / 2 - 4 + (showBackground ? 0 : 1), color, dropShadow, chroma);
	}
	
	public void drawTextToRender(ScreenPosition pos, String textToRender) {
		if (showBackground) {
	    	drawRect(pos);
			drawCenteredText(textToRender, pos.getAbsoluteX(), pos.getAbsoluteY(), textColor, textShadow);
    	} else {
		    drawAlignedText(brackets.wrap(textToRender), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, textColor, textShadow);
    	}
	}
	
	public void setShowBackground(boolean enabled) {
		showBackground = enabled;
		
		setToFile("showBackground", enabled);
	}
	
	public boolean isShowBackgroundToggled() {
		return showBackground;
	}
	
	public void setBrackets(Brackets brackets) {
		this.brackets = brackets;
		
		setToFile("brackets", brackets.getId());
	}
	
	public Brackets getBrackets() {
		return brackets;
	}
}
