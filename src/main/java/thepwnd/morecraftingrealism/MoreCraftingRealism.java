package thepwnd.morecraftingrealism;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import thepwnd.reference.References;

@Mod(modid = References.MODID, name = References.NAME, version = References.VERSION)
public class MoreCraftingRealism {
	
	@Instance(References.MODID)
	public static MoreCraftingRealism instance;
	
	@SidedProxy(clientSide = "thepwnd.morerealisticcrafting.CombinedClientProxy", serverSide = "thepwnd.morerealisticcrafting.DedicatedServerProxy")
	public static CommonProxy proxy;
	public enum GUI_ENUM {
		WICKER_BASKET
	}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit();
	}

}
