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
		
        if (mod.isShowTextToggled()) {
        	this.writeOptionText("Text Color", 2);
        	this.writeOptionText("Text Shadow", 3);
        }
        
    	this.writeOptionText("Show Background", mod.isShowTextToggled() ? 4 : 2);
    	
    	if (!mod.isShowBackgroundToggled()) {
    		this.writeOptionText("Brackets", mod.isShowTextToggled() ? 5 : 3);
        	this.writeSelectedOptionValue(mod.getBrackets().getName(), mod.isShowTextToggled() ? 5 : 3);
        	this.writeOptionText("Toggle Sprint", mod.isShowTextToggled() ? 6 : 4);
            this.writeOptionText("Toggle Sneak", mod.isShowTextToggled() ? 7 : 5);
            this.writeOptionText("Fly Boost", mod.isShowTextToggled() ? 8 : 6);
            
            if (mod.isFlyBoostToggled()) {
            	this.writeOptionText("Fly Boost Factor", mod.isShowTextToggled() ? 9 : 7);
                this.writeOptionValue(String.format("%.1f", mod.getFlyBoostFactor()), mod.isShowTextToggled() ? 9 : 7);
            }
    	} else {
    		this.writeOptionText("Toggle Sprint", mod.isShowTextToggled() ? 5 : 3);
            this.writeOptionText("Toggle Sneak", mod.isShowTextToggled() ? 6 : 4);
            this.writeOptionText("Fly Boost", mod.isShowTextToggled() ? 7 : 5);
            
            if (mod.isFlyBoostToggled()) {
            	this.writeOptionText("Fly Boost Factor", mod.isShowTextToggled() ? 8 : 6);
                this.writeOptionValue(String.format("%.1f", mod.getFlyBoostFactor()), mod.isShowTextToggled() ? 8 : 6);
            }
    	}
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
            	mod.setBrackets(Brackets.fromId(mod.getBrackets() == Brackets.NONE ? Brackets.ANGULAR.getId() : mod.getBrackets().getId() - 1));
            	this.initGui();
            	break;
            case 6:
            	mod.setBrackets(Brackets.fromId(mod.getBrackets() == Brackets.ANGULAR ? Brackets.NONE.getId() : mod.getBrackets().getId() + 1));
            	this.initGui();
            	break;
            case 7:
            	mod.setToggleSprint(!mod.isToggleSprintToggled());
            	this.initGui();
            	break;
            case 8:
            	mod.setToggleSneak(!mod.isToggleSneakToggled());
            	this.initGui();
            	break;
            case 9:
            	mod.setFlyBoost(!mod.isFlyBoostToggled());
            	this.initGui();
            	break;
            case 10:
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
        
    	this.buttonList.add(this.createGuiCheckBox(1, mod.isShowTextToggled(), 1));
    	
    	if (mod.isShowTextToggled()) {
    		this.buttonList.add(this.createGuiRect(2, mod.getTextColor().getRGB(), 2));
    		this.buttonList.add(this.createGuiCheckBox(3, mod.isTextShadowToggled(), 3));
    	}
    	
		this.buttonList.add(this.createGuiCheckBox(4, mod.isShowBackgroundToggled(), mod.isShowTextToggled() ? 4 : 2));
		
		if (!mod.isShowBackgroundToggled()) {
			this.buttonList.add(this.createGuiTextLeftArrow(5, mod.getBrackets().getName(), mod.isShowTextToggled() ? 5 : 3));
			this.buttonList.add(this.createGuiTextRightArrow(6, mod.isShowTextToggled() ? 5 : 3));
			this.buttonList.add(this.createGuiCheckBox(7, mod.isToggleSprintToggled(), mod.isShowTextToggled() ? 6 : 4));
	    	this.buttonList.add(this.createGuiCheckBox(8, mod.isToggleSneakToggled(), mod.isShowTextToggled() ? 7 : 5));
	    	this.buttonList.add(this.createGuiCheckBox(9, mod.isFlyBoostToggled(), mod.isShowTextToggled() ? 8 : 6));

	    	if (mod.isFlyBoostToggled()) {
	        	this.buttonList.add(sliderFlyBoostFactor = this.createGuiSlider(10, 8.0F, mod.getFlyBoostFactor(), mod.isShowTextToggled() ? 10 : 8));
	    	}
		} else {
			this.buttonList.add(this.createGuiCheckBox(7, mod.isToggleSprintToggled(), mod.isShowTextToggled() ? 5 : 3));
	    	this.buttonList.add(this.createGuiCheckBox(8, mod.isToggleSneakToggled(), mod.isShowTextToggled() ? 6 : 4));
	    	this.buttonList.add(this.createGuiCheckBox(9, mod.isFlyBoostToggled(), mod.isShowTextToggled() ? 7 : 5));

	    	if (mod.isFlyBoostToggled()) {
	        	this.buttonList.add(sliderFlyBoostFactor = this.createGuiSlider(10, 8.0F, mod.getFlyBoostFactor(), mod.isShowTextToggled() ? 9 : 7));
	    	}
		}
	}
}
