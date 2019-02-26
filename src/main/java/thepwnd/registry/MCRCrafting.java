package thepwnd.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class MCRCrafting {
	
	public static void mainRegistry() {
		addCraftingRecipes();
		addSmelting();
	}
	
	public static void addCraftingRecipes() {
		GameRegistry.addRecipe(new ItemStack(MCRBlocks.blockWickerBasket), new Object[]{"BBB","B B","BBB", 'B', Items.reeds});
		GameRegistry.addRecipe(new ItemStack(MCRItems.itemStrawBedding), new Object[]{"GG","GG", 'G', Blocks.tallgrass});
	}
	
	public static void addSmelting() {
		
	}

}
