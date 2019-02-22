package thepwnd.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import thepwnd.tileentity.TileEntityWickerBasket;

public class MCRTileEntities {
	
	public static void mainRegistry() {
		registerTileEntity();
	}
	
	public static void registerTileEntity() {
		GameRegistry.registerTileEntity(TileEntityWickerBasket.class, "TileEntityWickerBasket");
	}

}
