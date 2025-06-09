package drop.gui.mod;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.gui.GuiButtonToggled;
import drop.gui.GuiSlider;
import drop.mods.ModInstances;
import drop.mods.ModDraggableDisplayText.Brackets;
import drop.mods.impl.togglesprintsneak.ToggleSprintSneak;

public class GuiToggleSprintSneak extends GuiMod {
	private static final ToggleSprintSneak mod = ModInstances.getToggleSprintSneakMod();

	private GuiSlider sliderFlyBoostFactor;
	
	public GuiToggleSprintSneak(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, mod);
	}

	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
        
        this.writeOptionText("Show Text", 1);
		this.writeOptionText("Text Color", 2);
    	this.writeOptionText("Text Shadow", 3);
    	this.writeOptionText("Show Background", 4);
    	this.writeOptionText("Brackets", 5);
        this.writeOptionText("Toggle Sprint", 6);
        this.writeOptionText("Toggle Sneak", 7);
        this.writeOptionText("Fly Boost", 8);
        this.writeOptionText("Fly Boost Factor", 9);
        this.writeOptionValue(String.format("%.1f", mod.getFlyBoostFactor()), 9);
    }
    
    @Override
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
    	mod.setFlyBoostFactor(sliderFlyBoostFactor.getSliderPosition() * 8.0F);
    	
    	if (mod.getFlyBoostFactor() < 2.0F) {
    		mod.setFlyBoostFactor(2.0F);
    	}
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        switch (button.id) {
	        case 1:
	        	mod.setShowText(!mod.isShowTextToggled());
	        	this.initGui();
	        	break;
	        case 2:
	        	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getTextColor()));
	        	break;
	        case 3:
	        	mod.setTextShadow(!mod.isTextShadowToggled());
	        	this.initGui();
	        	break;
	        case 4:
	        	mod.setShowBackground(!mod.isShowBackgroundToggled());
	        	this.initGui();
	        	break;
	        case 5:
	        	mod.setBrackets(Brackets.fromId(mod.getBrackets() == Brackets.ANGULAR ? Brackets.NONE.getId() : mod.getBrackets().getId() + 1));
	        	this.initGui();
	        	break;
            case 6:
            	mod.setToggleSprint(!mod.isToggleSprintToggled());
            	this.initGui();
            	break;
            case 7:
            	mod.setToggleSneak(!mod.isToggleSneakToggled());
            	this.initGui();
            	break;
            case 8:
            	mod.setFlyBoost(!mod.isFlyBoostToggled());
            	this.initGui();
            	break;
            case 9:
            	mod.setFlyBoostFactor(sliderFlyBoostFactor.getSliderPosition() * 8.0F);
            	
            	if (mod.getFlyBoostFactor() < 2.0F) {
            		mod.setFlyBoostFactor(2.0F);
            	}
            	break;
        }
    }
	
	@Override
    public void initGui() {
        super.initGui();
        
    	this.buttonList.add(this.createGuiButtonToggled(1, mod.isShowTextToggled(), 1));
    	this.buttonList.add(this.createGuiRect(2, mod.getTextColor().getRGB(), 2));
		this.buttonList.add(this.createGuiButtonToggled(3, mod.isTextShadowToggled(), 3));
		this.buttonList.add(this.createGuiButtonToggled(4, mod.isShowBackgroundToggled(), 4));
		this.buttonList.add(this.createGuiText(5, mod.getBrackets().getName(), true, 5));
    	this.buttonList.add(this.createGuiButtonToggled(6, mod.isToggleSprintToggled(), 6));
    	this.buttonList.add(this.createGuiButtonToggled(7, mod.isToggleSneakToggled(), 7));
    	this.buttonList.add(this.createGuiButtonToggled(8, mod.isFlyBoostToggled(), 8));
    	this.buttonList.add(sliderFlyBoostFactor = this.createGuiSlider(9, 8.0F, mod.getFlyBoostFactor(), 10));
    }
}
