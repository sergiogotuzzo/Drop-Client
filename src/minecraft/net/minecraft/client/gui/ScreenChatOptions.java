package net.minecraft.client.gui;

import java.io.IOException;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import rubik.gui.GuiRubikClientScreen;
import rubik.gui.GuiSlider;
import rubik.mods.ModInstances;
import rubik.mods.impl.Chat;

public class ScreenChatOptions extends GuiRubikClientScreen
{
    private static final GameSettings.Options[] field_146399_a = new GameSettings.Options[] {GameSettings.Options.CHAT_VISIBILITY, GameSettings.Options.CHAT_COLOR, GameSettings.Options.CHAT_LINKS, GameSettings.Options.CHAT_OPACITY, GameSettings.Options.CHAT_LINKS_PROMPT, GameSettings.Options.CHAT_SCALE, GameSettings.Options.CHAT_HEIGHT_FOCUSED, GameSettings.Options.CHAT_HEIGHT_UNFOCUSED, GameSettings.Options.CHAT_WIDTH, GameSettings.Options.REDUCED_DEBUG_INFO};
    private final GuiScreen parentScreen;
    private final GameSettings game_settings;
    private String field_146401_i;
    private final Chat chatMod = ModInstances.getChatMod();
    private GuiSlider sliderBackgroundOpacity;

    public ScreenChatOptions(GuiScreen parentScreenIn, GameSettings gameSettingsIn)
    {
        this.parentScreen = parentScreenIn;
        this.game_settings = gameSettingsIn;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {
        int i = 0;
        this.field_146401_i = I18n.format("options.chat.title", new Object[0]);

        for (GameSettings.Options gamesettings$options : field_146399_a)
        {
            if (gamesettings$options.getEnumFloat())
            {
                this.buttonList.add(new GuiOptionSlider(gamesettings$options.returnEnumOrdinal(), this.width / 2 - 155 + i % 2 * 160, this.height / 6 + 24 * (i >> 1), gamesettings$options));
            }
            else
            {
                this.buttonList.add(new GuiOptionButton(gamesettings$options.returnEnumOrdinal(), this.width / 2 - 155 + i % 2 * 160, this.height / 6 + 24 * (i >> 1), gamesettings$options, this.game_settings.getKeyBinding(gamesettings$options)));
            }

            ++i;
        }
        
        this.buttonList.add(new GuiButton(43, this.width / 2 - 155, this.height / 6 + 120, 150, 20, "Text Shadow: " + (chatMod.isTextShadowEnabled() ? "ON" : "OFF")));
        this.buttonList.add(sliderBackgroundOpacity = new GuiSlider(44, this.width / 2 - 155 + 160, this.height / 6 + 120, 150, 20, "Background Opacity", 0, 127, chatMod.getBackgroundOpacity()));
        this.buttonList.add(new GuiButton(45, this.width / 2 - 155, this.height / 6 + 144, 150, 20, "Chat Height Fix: " + (chatMod.isChatHeightFixEnabled() ? "ON" : "OFF")));

        this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done", new Object[0])));
    }
    
    @Override
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
    	chatMod.setBackgroundOpacity((int) (sliderBackgroundOpacity.func_175217_d() * 127.0F));
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button) throws IOException
    {
        if (button.enabled)
        {
            if (button.id < 100 && button instanceof GuiOptionButton)
            {
                this.game_settings.setOptionValue(((GuiOptionButton)button).returnEnumOptions(), 1);
                button.displayString = this.game_settings.getKeyBinding(GameSettings.Options.getEnumOptions(button.id));
            }
            
            if (button.id == 43) {
            	chatMod.setTextShadow(!chatMod.isTextShadowEnabled());
            	button.displayString = "Text Shadow: " + (chatMod.isTextShadowEnabled() ? "ON" : "OFF");
            }
            
            if (button.id == 44) {
            	chatMod.setBackgroundOpacity((int) (sliderBackgroundOpacity.func_175217_d() * 127.0F));
            }
            
            if (button.id == 45) {
            	chatMod.setChatHeightFix(!chatMod.isChatHeightFixEnabled());
            	button.displayString = "Chat Height Fix: " + (chatMod.isChatHeightFixEnabled() ? "ON" : "OFF");
            }

            if (button.id == 200)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(this.parentScreen);
            }
        }
    }

    /**
     * Draws the screen and all the components in it. Args : mouseX, mouseY, renderPartialTicks
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, this.field_146401_i, this.width / 2, 20, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
