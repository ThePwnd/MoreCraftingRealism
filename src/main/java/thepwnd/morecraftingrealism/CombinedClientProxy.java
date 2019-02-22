package thepwnd.morecraftingrealism;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import thepwnd.client.render.items.GenericBlockItemRenderer;
import thepwnd.client.render.tileentity.RenderWickerBasket;
import thepwnd.registry.MCRBlocks;
import thepwnd.tileentity.TileEntityWickerBasket;

public class CombinedClientProxy extends CommonProxy {
	
	public static void registerRenderThings() {
		TileEntitySpecialRenderer renderWickerBasket = new RenderWickerBasket();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWickerBasket.class, renderWickerBasket);
		
		registerItemRenderers();
	}
	
	public static void registerItemRenderers() {
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(MCRBlocks.blockWickerBasket), new GenericBlockItemRenderer(new TileEntityWickerBasket(), new RenderWickerBasket()));
	}

}
