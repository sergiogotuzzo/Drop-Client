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
	protected ColorManager backgroundColor = ColorManager.fromRGB(0, 0, 0, 102, false);
	protected Brackets brackets = Brackets.SQUARE;
	protected String dummyText = "";
	
	public ModDraggableDisplayText(boolean enabled, double relativeX, double relativeY, String dummyText) {
		super(enabled, relativeX, relativeY);
		
		this.dummyText = dummyText;
		
		setShowBackground(getBooleanFromFile("showBackground", showBackground));
		setBackgroundColor(getIntFromFile("backgroundColor", backgroundColor.getRGB()));
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
	
	public void drawTextToRender(ScreenPosition pos, String textToRender) {
		if (showBackground) {
	    	getBounds().fill(backgroundColor.getRGB());
	    	
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
	
	public void setBackgroundColor(int rgb) {
		this.backgroundColor = ColorManager.fromRGB(rgb, false);
		
		setToFile("backgroundColor", rgb);
	}
	
	public ColorManager getBackgroundColor() {
		return backgroundColor;
	}
	
	public void setBrackets(Brackets brackets) {
		this.brackets = brackets;
		
		setToFile("brackets", brackets.getId());
	}
	
	public Brackets getBrackets() {
		return brackets;
	}
}
