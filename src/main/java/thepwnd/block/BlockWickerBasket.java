package thepwnd.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thepwnd.reference.References;
import thepwnd.morecraftingrealism.MoreCraftingRealism;
import thepwnd.tileentity.TileEntityWickerBasket;

public class BlockWickerBasket extends BlockContainer {
	
	private final Random random = new Random();
	private int modGuiId = MoreCraftingRealism.GUI_ENUM.WICKER_BASKET.ordinal();

	public BlockWickerBasket() {
		super(Material.wood);
		this.setStepSound(this.soundTypeGrass);
		this.setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 0.5625F, 0.875F);
		this.setBlockTextureName(References.MODID + ":wicker.png");
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean isNormalCube() {
		return false;
	}
	
	@Override
	public int getRenderType() {
		return -1;
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, int posX, int posY, int posZ) {
		if (world.getBlock(posX, posY - 1, posZ) == this) {
			return false;
		}
		
		else {
			return true;
		}
	}
	
	@Override
	public boolean onBlockActivated(World parWorld, int posX, int posY, int posZ, EntityPlayer parPlayer, int mysteriousInt, float hitX, float hitY, float hitZ) {
		if (parWorld.isRemote) {
			// DEBUG
			System.out.println("BlockWickerBasket onBlockActivated()");
			return true;
		}
		
		parPlayer.openGui(MoreCraftingRealism.instance, MoreCraftingRealism.GUI_ENUM.WICKER_BASKET.ordinal(), parWorld, posX, posY, posZ);
		return true;
	}
	
	/*
	public IInventory func_149951_m(World parWorld, int posX, int posY, int posZ) {
		// TODO Auto-generated method stub
		Object object = (TileEntityWickerBasket)parWorld.getTileEntity(posX, posY, posZ);
		
		if (object == null) {
			return null;
		}
		
		else {
			return (IInventory)object;
		}
		
	}
	*/
	
	@Override
	public void breakBlock(World world, int posX, int posY, int posZ, Block block, int meta) {
		
		TileEntityWickerBasket tileEntityWickerBasket = (TileEntityWickerBasket)world.getTileEntity(posX, posY, posZ);

        if (tileEntityWickerBasket != null)
        {
            for (int i1 = 0; i1 < tileEntityWickerBasket.getSizeInventory(); ++i1)
            {
                ItemStack itemstack = tileEntityWickerBasket.getStackInSlot(i1);

                if (itemstack != null)
                {
                    float f = this.random.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.random.nextFloat() * 0.8F + 0.1F;
                    EntityItem entityitem;

                    for (float f2 = this.random.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; world.spawnEntityInWorld(entityitem))
                    {
                        int j1 = this.random.nextInt(21) + 10;

                        if (j1 > itemstack.stackSize)
                        {
                            j1 = itemstack.stackSize;
                        }

                        itemstack.stackSize -= j1;
                        entityitem = new EntityItem(world, (double)((float)posX + f), (double)((float)posY + f1), (double)((float)posZ + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
                        float f3 = 0.05F;
                        entityitem.motionX = (double)((float)this.random.nextGaussian() * f3);
                        entityitem.motionY = (double)((float)this.random.nextGaussian() * f3 + 0.2F);
                        entityitem.motionZ = (double)((float)this.random.nextGaussian() * f3);

                        if (itemstack.hasTagCompound())
                        {
                            entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                        }
                    }
                }
            }

            world.func_147453_f(posX, posY, posZ, block);
        }

        super.breakBlock(world, posX, posY, posZ, block, meta);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		// DEBUG
		System.out.println("BlockWickerBasket createNewTileEntity()");
		return new TileEntityWickerBasket();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) { 
		this.blockIcon = iconRegister.registerIcon(References.MODID + ":wicker");
	}

}