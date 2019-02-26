package thepwnd.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import thepwnd.block.BlockStrawBed;
import thepwnd.block.BlockWickerBasket;

public class MCRBlocks {
	
	public static void mainRegistry() {
		initializeBlock();
		registerBlock();
	}
	
	public static Block blockWickerBasket;
	public static Block blockStrawBed;

	private static void initializeBlock() {
		blockWickerBasket = new BlockWickerBasket().setBlockName("BlockWickerBasket");
		blockStrawBed = new BlockStrawBed().setBlockName("BlockStrawBed");
	}

	private static void registerBlock() {
		GameRegistry.registerBlock(blockWickerBasket, blockWickerBasket.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(blockStrawBed, "BlockStrawBed");
	}

}
