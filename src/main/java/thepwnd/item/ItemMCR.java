package thepwnd.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thepwnd.reference.References;
import thepwnd.reference.Textures;

public class ItemMCR extends Item {
	
	public ItemMCR() {
		super();		
	}
	
	@Override
	public String getUnlocalizedName() {
		return String.format("item.%s%s", References.MODID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return String.format("item.%s%s", References.MODID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconregister) {
		itemIcon = iconregister.registerIcon(this.getUnlocalizedName().toLowerCase().substring(this.getUnlocalizedName().indexOf(".") + 1));
	}	

	protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}

}
