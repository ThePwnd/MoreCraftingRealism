package thepwnd.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import thepwnd.reference.References;

public class BlockMCR extends Block {

	public BlockMCR(Material material) {
		super(material);
	}
	
	public BlockMCR() {
		this(Material.grass);
	}
	
	@Override
	public String getUnlocalizedName() {
		return String.format("tile.%s%s", References.MODID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconregister) {
		blockIcon = iconregister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
	}

	protected String getUnwrappedUnlocalizedName(String unlocalizedname) {
		return unlocalizedname.substring(unlocalizedname.indexOf(".") + 1);
	}

}
