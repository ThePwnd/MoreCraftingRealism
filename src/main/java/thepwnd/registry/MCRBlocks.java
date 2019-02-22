package thepwnd.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import thepwnd.block.BlockWickerBasket;

public class MCRBlocks {
	
	public static void mainRegistry() {
		initializeBlock();
		registerBlock();
	}
	
	public static Block blockWickerBasket;

	private static void initializeBlock() {
		blockWickerBasket = new BlockWickerBasket().setBlockName("BlockWickerBasket");
	}

	private static void registerBlock() {
		GameRegistry.registerBlock(blockWickerBasket, blockWickerBasket.getUnlocalizedName().substring(5));		
	}

}
