package thepwnd.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import thepwnd.block.BlockStrawBed;
import thepwnd.registry.MCRBlocks;

public class ItemStrawBedding extends ItemMCR {
	
	public ItemStrawBedding() {
		super();
		this.setUnlocalizedName("ItemStrawBedding");
	}
	
	@Override
	// Note the coordinates are those of the block that was hit by the player.
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int posX, int posY, int posZ, int meta, float hitX, float hitY, float hitZ) {
		if (world.isRemote) {
			return true;
		}
		else if (meta != 1) {
			return false;
		}
		else {
			posY++;
			BlockStrawBed strawbed = (BlockStrawBed)MCRBlocks.blockStrawBed;
			int dirFacing = MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
			byte axisX = 0;
			byte axisZ = 0;
			
			if (dirFacing == 0) {
				axisZ = 1;
			}
			if (dirFacing == 1) {
				axisX = -1;
			}
			if (dirFacing == 2) {
				axisZ = -1;
			}
			if (dirFacing == 3) {
				axisX = 1;
			}
			
			if (player.canPlayerEdit(posX, posY, posZ, meta, itemstack) && player.canPlayerEdit(posX + axisX, posY, posZ + axisZ, meta, itemstack)) {
				if (world.isAirBlock(posX, posY, posZ) && world.isAirBlock(posX, posY, posZ) && World.doesBlockHaveSolidTopSurface(world, posX + axisX, posY - 1, posZ + axisZ)) {
					world.setBlock(posX, posY, posZ, strawbed, dirFacing, 3);
					
					if (world.getBlock(posX, posY, posZ) == strawbed) {
						world.setBlock(posX + axisX, posY, posZ + axisZ, strawbed, dirFacing + 8, 3);
					}
					
					itemstack.stackSize--;
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}
	}

}
