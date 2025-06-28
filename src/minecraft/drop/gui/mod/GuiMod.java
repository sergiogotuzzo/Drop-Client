package drop.gui.mod;

import java.awt.Color;
import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import drop.ColorManager;
import drop.gui.GuiDropClientScreen;
import drop.mods.Mod;
import drop.mods.option.Brackets;
import drop.mods.option.*;
import drop.mods.option.type.BooleanOption;
import drop.mods.option.type.BracketsOption;
import drop.mods.option.type.ColorOption;
import drop.mods.option.type.FloatOption;
import drop.mods.option.type.IntOption;

public class GuiMod extends GuiDropClientScreen {
	private final GuiScreen previousGuiScreen;
	protected final Mod mod;
	
	public GuiMod(GuiScreen previousGuiScreen, Mod mod) {
		this.previousGuiScreen = previousGuiScreen;
		this.mod = mod;
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	this.drawDefaultBackground();
    	
    	this.drawRect((this.width - 350) / 2, (this.height - 250) / 2, (this.width - 350) / 2 + 350, (this.height - 250) / 2 + 250, new Color(0, 0, 0, 127).getRGB());
        
        this.drawScaledText(mod.getName(), (this.width - 350) / 2 + 15, (this.height - 250) / 2 + 15, 2.0D, 0xFFFFFFFF, true, false);
        
        int i = 1;
		
		for (ModOption option : mod.getOptions().getOptions()) {			
			if (isOptionVisible(option)) {
				i = this.writeOption(option, i);
				
				i++;
			}
		}

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 0) {
        	this.mc.displayGuiScreen(this.previousGuiScreen);
        }
        
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
					((BracketsOption) option).setValue(Brackets.fromId(((BracketsOption) option).getBrackets() == Brackets.NONE ? Brackets.ANGULAR.getId() : ((BracketsOption) option).getId() - 1));
	            	this.initGui();
				} else if (button.id == -2) {
					((BracketsOption) option).setValue(Brackets.fromId(((BracketsOption) option).getBrackets() == Brackets.ANGULAR ? Brackets.NONE.getId() : ((BracketsOption) option).getId() + 1));
	            	this.initGui();
				}
			}
		}
    }
	
	@Override
    public void initGui() {
		super.initGui();

        this.buttonList.clear();

        this.buttonList.add(new GuiButton(0, (this.width + 350) / 2 - 50 - 15, (this.height - 250) / 2 + 15 - 3, 50, 20, I18n.format("gui.done", new Object[0])));
        
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
    		this.buttonList.add(this.createGuiRect(option.getGuiSettings().getButtonId(), ((ColorManager) option.getValue()).getRGB(), i));
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
