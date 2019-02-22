package thepwnd.morecraftingrealism;

import cpw.mods.fml.common.network.NetworkRegistry;
import thepwnd.common.gui.GuiHandler;
import thepwnd.registry.MCRBlocks;
import thepwnd.registry.MCRCrafting;
import thepwnd.registry.MCRItems;
import thepwnd.registry.MCRTileEntities;

public class CommonProxy {

	public void preInit() {
		MCRBlocks.mainRegistry();
		MCRItems.mainRegistry();
		MCRTileEntities.mainRegistry();
		NetworkRegistry.INSTANCE.registerGuiHandler(MoreCraftingRealism.instance, new GuiHandler());
	}

	public void init() {
		MCRCrafting.mainRegistry();		
	}

	public void postInit() {
		
	}

}
