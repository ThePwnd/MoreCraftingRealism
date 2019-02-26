package thepwnd.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import thepwnd.item.ItemLumberOak;
import thepwnd.item.ItemMCR;
import thepwnd.item.ItemStrawBedding;

public class MCRItems {
	
	public static void mainRegistry() {
		registerItem();
	}
	
	public static final ItemMCR itemLumberOak = new ItemLumberOak();
	public static final ItemMCR itemStrawBedding = new ItemStrawBedding();
	
	public static void registerItem() {
		GameRegistry.registerItem(itemLumberOak, "ItemLumberOak");
		GameRegistry.registerItem(itemStrawBedding, "ItemStrawBedding");
	}

}
