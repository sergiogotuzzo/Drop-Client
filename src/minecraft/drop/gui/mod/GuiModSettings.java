package drop.gui.mod;

import java.awt.Color;
import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import drop.gui.GuiDropClientScreen;
import drop.mods.Mod;
import drop.mods.ModColor;
import drop.mods.option.*;
import drop.mods.option.type.*;

public class GuiModSettings extends GuiModMenu {
	private final Mod mod;

	public GuiModSettings(GuiScreen previousGuiScreen, Mod mod) {
		super(previousGuiScreen, mod.getName());
		
		this.mod = mod;
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	super.drawScreen(mouseX, mouseY, partialTicks);
        
        int i = 1;
		
		for (ModOption option : mod.getOptions().getOptions()) {			
			if (isOptionVisible(option)) {
				i = this.writeOption(option, i);
				
				i++;
			}
		}
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
        
        for (ModOption option : mod.getOptions().getOptions()) {
			if (button.id == option.getGuiSettings().getButtonId()) {
				if (option instanceof BooleanOption) {
					((BooleanOption) option).toggle();
					this.initGui();
				} else if (option instanceof ColorOption) {
					mc.displayGuiScreen(new GuiModColor(this, mod, (ColorOption) option));
				}
			}
			
			if (option instanceof BracketsOption) {
				if (button.id == -1) {
					((BracketsOption) option).saveValue(Brackets.fromId(((BracketsOption) option).getBrackets() == Brackets.NONE ? Brackets.ANGULAR.getId() : ((BracketsOption) option).getBrackets().getId() - 1));
	            	this.initGui();
				} else if (button.id == -2) {
					((BracketsOption) option).saveValue(Brackets.fromId(((BracketsOption) option).getBrackets() == Brackets.ANGULAR ? Brackets.NONE.getId() : ((BracketsOption) option).getBrackets().getId() + 1));
	            	this.initGui();
				}
			}
		}
    }
	
	@Override
    public void initGui() {
		super.initGui();
        
        int i = 1;
        
		for (ModOption option : mod.getOptions().getOptions()) {				
			if (isOptionVisible(option)) {
				i = this.drawButton(option, i);
				
				i++;
			}
		}
    }
    
    private int writeOption(ModOption option, int i) {
    	this.writeOptionText(option.getGuiSettings().getOptionName(), i);
		
		if (option instanceof FloatOption) {
			this.writeOptionValue(String.format("%." + option.getGuiSettings().getDecimals() + "f", option.getValue()), i);
			i++;
		} else if (option instanceof IntOption) {
			this.writeOptionValue(String.valueOf(option.getValue()), i);
			i++;
		} else if (option instanceof BracketsOption) {
        	this.writeSelectedOptionValue(((BracketsOption) option).getBrackets().getName(), i);
		}
		
		return i;
    }
	
	private int drawButton(ModOption option, int i) {
		if (option instanceof BooleanOption) {
			this.buttonList.add(this.createGuiCheckBox(option.getGuiSettings().getButtonId(), ((BooleanOption) option).isToggled(), i));
		} else if (option instanceof ColorOption) {
    		this.buttonList.add(this.createGuiRect(option.getGuiSettings().getButtonId(), ((ColorOption) option).getColor().getRGB(), i));
		} else if (option instanceof FloatOption) {
			i++;
			this.buttonList.add(this.createGuiSliderOption(option.getGuiSettings().getButtonId(), ((FloatOption) option).getMax(), (float) option.getValue(), i, (FloatOption) option));
		} else if (option instanceof IntOption) {
			i++;
			this.buttonList.add(this.createGuiSliderOption(option.getGuiSettings().getButtonId(), ((IntOption) option).getMax(), (int) option.getValue(), i, (IntOption) option));
		} else if (option instanceof BracketsOption) {
			this.buttonList.add(this.createGuiTextLeftArrow(-1, ((BracketsOption) option).getBrackets().getName(), i));
			this.buttonList.add(this.createGuiTextRightArrow(-2, i));
		}
		
		return i;
	}
	
	private boolean isOptionVisible(ModOption option) {
		if (!option.getGuiSettings().isVisible()) {
			return false;
		}
		
	    ParentOption parentOption = option.getParentOption();

	    if (parentOption == null) {
	        return true;
	    }

	    ModOption optionParented = mod.getOptions().getOption(parentOption.getKey());

	    if (!(optionParented instanceof BooleanOption)) {
	        return false;
	    }

	    boolean parentOptionState = ((BooleanOption) optionParented).isToggled();
	    boolean visible = parentOption.isInverted() ? !parentOptionState : parentOptionState;

	    return visible && isOptionVisible(optionParented);
	}
}
