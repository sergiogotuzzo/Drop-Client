package drop.gui;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ServerSelectionList;
import net.minecraft.client.multiplayer.ServerList;
import net.minecraft.client.network.LanServerDetector;

public class GuiIngameMultiplayer extends GuiMultiplayer {
	public GuiIngameMultiplayer(GuiScreen parentScreen) {
		super(parentScreen);
	}
	
	@Override
	public void initGui() {
		Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();

        if (!this.initialized)
        {
            this.initialized = true;
            this.savedServerList = new ServerList(this.mc);
            this.savedServerList.loadServerList();
            this.lanServerList = new LanServerDetector.LanServerList();

            try
            {
                this.lanServerDetector = new LanServerDetector.ThreadLanServerFind(this.lanServerList);
                this.lanServerDetector.start();
            }
            catch (Exception exception)
            {
                logger.warn("Unable to start LAN server detection: " + exception.getMessage());
            }

            this.serverListSelector = new ServerSelectionList(this, this.mc, this.width, this.height, 32, this.height - 64, 36);
            this.serverListSelector.func_148195_a(this.savedServerList);
        }
        else
        {
            this.serverListSelector.setDimensions(this.width, this.height, 32, this.height - 64);
        }

        this.createButtons();
	}

	@Override
	public void connectToSelected() {
		this.disconnect();
		
		super.connectToSelected();
	}
	
	public void disconnect() {
		if (this.mc.theWorld != null) {
			this.mc.theWorld.sendQuittingDisconnectingPacket();
			this.mc.loadWorld(null);
		}
	}
}
