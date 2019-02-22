package thepwnd.common.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thepwnd.client.gui.inventory.GuiWickerBasket;
import thepwnd.inventory.ContainerWickerBasket;
import thepwnd.morecraftingrealism.MoreCraftingRealism;

public class GuiHandler implements IGuiHandler {
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if(tileEntity != null) {
			if(ID == MoreCraftingRealism.GUI_ENUM.WICKER_BASKET.ordinal()) {
				return new ContainerWickerBasket(player.inventory, (IInventory)tileEntity);
			}
		}
		
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		//DEBUG
		System.out.println("GuiHandler getClientGuiElement()");
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if(tileEntity != null) {
			if(ID == MoreCraftingRealism.GUI_ENUM.WICKER_BASKET.ordinal()) {
				return new GuiWickerBasket(player.inventory, (IInventory)tileEntity);
			}
		}
		
		return null;
	}
}
