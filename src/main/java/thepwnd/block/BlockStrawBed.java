package thepwnd.block;

import java.util.Iterator;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Direction;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import thepwnd.reference.References;
import thepwnd.registry.MCRItems;

public class BlockStrawBed extends BlockDirectionalMCR {
	
	public static final int[][] blockMap = new int[][] {{0, 1}, { -1, 0}, {0, -1}, {1, 0}};
	@SideOnly(Side.CLIENT)
	private IIcon[] texturesEnd;
	@SideOnly(Side.CLIENT)
	private IIcon[] texturesSide;
	@SideOnly(Side.CLIENT)
	private IIcon[] texturesTop;
	
	public BlockStrawBed() {
		super(Material.ground);
		this.setBlockName("BlockStrawBed");
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.1875F, 1.0F);
		this.setStepSound(this.soundTypeGrass);
	}
	
	@Override
	public boolean isBed(IBlockAccess world, int posX, int posY, int posZ, EntityLivingBase player) {
		return true;
	}
	
	@Override
	public boolean onBlockActivated(World world, int posX, int posY, int posZ, EntityPlayer player, int mystI, float hitX, float hitY, float hitZ)
    {
        if (world.isRemote)
        {
            return true;
        }
        else
        {
            int meta = world.getBlockMetadata(posX, posY, posZ);

            if (!isBlockHeadOfBed(meta))
            {
                int dirOrient = getDirection(meta);
                posX += blockMap[dirOrient][0];
                posZ += blockMap[dirOrient][1];

                if (world.getBlock(posX, posY, posZ) != this)
                {
                    return true;
                }

                meta = world.getBlockMetadata(posX, posY, posZ);
            }

            if (world.provider.canRespawnHere() && world.getBiomeGenForCoords(posX, posZ) != BiomeGenBase.hell)
            {
                if (isBlockFootOfBed(meta))
                {
                    EntityPlayer entityplayer1 = null;
                    Iterator iterator = world.playerEntities.iterator();

                    while (iterator.hasNext())
                    {
                        EntityPlayer entityplayer2 = (EntityPlayer)iterator.next();

                        if (entityplayer2.isPlayerSleeping())
                        {
                            ChunkCoordinates chunkcoordinates = entityplayer2.playerLocation;

                            if (chunkcoordinates.posX == posX && chunkcoordinates.posY == posY && chunkcoordinates.posZ == posZ)
                            {
                                entityplayer1 = entityplayer2;
                            }
                        }
                    }

                    if (entityplayer1 != null)
                    {
                        player.addChatComponentMessage(new ChatComponentTranslation("tile.bed.occupied", new Object[0]));
                        return true;
                    }

                    BlockBed.func_149979_a(world, posX, posY, posZ, false);
                }

                EntityPlayer.EnumStatus enumstatus = player.sleepInBedAt(posX, posY, posZ);

                if (enumstatus == EntityPlayer.EnumStatus.OK)
                {
                    BlockBed.func_149979_a(world, posX, posY, posZ, true);
                    return true;
                }
                else
                {
                    if (enumstatus == EntityPlayer.EnumStatus.NOT_POSSIBLE_NOW)
                    {
                        player.addChatComponentMessage(new ChatComponentTranslation("tile.bed.noSleep", new Object[0]));
                    }
                    else if (enumstatus == EntityPlayer.EnumStatus.NOT_SAFE)
                    {
                        player.addChatComponentMessage(new ChatComponentTranslation("tile.bed.notSafe", new Object[0]));
                    }

                    return true;
                }
            }
            else
            {
                double dX = (double)posX + 0.5D;
                double dY = (double)posY + 0.5D;
                double dZ = (double)posZ + 0.5D;
                world.setBlockToAir(posX, posY, posZ);
                int k1 = getDirection(meta);
                posX += blockMap[k1][0];
                posZ += blockMap[k1][1];

                if (world.getBlock(posX, posY, posZ) == this)
                {
                    world.setBlockToAir(posX, posY, posZ);
                    dX = (dX + (double)posX + 0.5D) / 2.0D;
                    dY = (dY + (double)posY + 0.5D) / 2.0D;
                    dZ = (dZ + (double)posZ + 0.5D) / 2.0D;
                }

                world.newExplosion((Entity)null, (double)((float)posX + 0.5F), (double)((float)posY + 0.5F), (double)((float)posZ + 0.5F), 5.0F, true, true);
                return true;
            }
        }
    }
	
	public static boolean isBlockHeadOfBed(int meta) {
		return (meta & 8) != 0;
	}
	
	@Override
	public boolean isBedFoot(IBlockAccess world, int posX, int posY, int posZ) {
		return this.isBlockHeadOfBed(world.getBlockMetadata(posX, posY, posZ));
	}
	
	public static boolean isBlockFootOfBed(int meta) {
		return (meta & 4) != 0;
	}
	
	@Override
	public void onNeighborBlockChange(World world, int posX, int posY, int posZ, Block block) {
        int l = world.getBlockMetadata(posX, posY, posZ);
        int i1 = getDirection(l);

        if (isBlockHeadOfBed(l))
        {
            if (world.getBlock(posX - blockMap[i1][0], posY, posZ - blockMap[i1][1]) != this)
            {
                world.setBlockToAir(posX, posY, posZ);
            }
        }
        else if (world.getBlock(posX + blockMap[i1][0], posY, posZ + blockMap[i1][1]) != this)
        {
            world.setBlockToAir(posX, posY, posZ);

            if (!world.isRemote)
            {
                this.dropBlockAsItem(world, posX, posY, posZ, l, 0);
            }
        }
    }
    
	@Override
	public boolean isOpaqueCube()
    {
        return false;
    }
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public int getRenderType() {
		return 14;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int posX, int posY, int posZ) {
		return MCRItems.itemStrawBedding;
	}
	
	@Override
	public void dropBlockAsItemWithChance(World world, int posX, int posY, int posZ, int meta, float mystF, int mystI)
    {
        if (!isBlockHeadOfBed(meta))
        {
            super.dropBlockAsItemWithChance(world, posX, posY, posZ, meta, mystF, 0);
        }
    }
	
	@Override
	public Item getItemDropped(int meta, Random random, int mystI) {
		return isBlockHeadOfBed(meta) ? Item.getItemById(0) : MCRItems.itemStrawBedding;
	}
	
	@Override
	public void onBlockHarvested(World world, int posX, int posY, int posZ, int meta, EntityPlayer player)
    {
        if (player.capabilities.isCreativeMode && isBlockHeadOfBed(meta))
        {
            int i1 = getDirection(meta);
            posX -= blockMap[i1][0];
            posZ -= blockMap[i1][1];

            if (world.getBlock(posX, posY, posZ) == this)
            {
                world.setBlockToAir(posX, posY, posZ);
            }
        }
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (side == 0) {
			return Blocks.hay_block.getBlockTextureFromSide(side);
		}
		else {
			final int k = getDirection(meta);
			final int l = Direction.bedDirection[k][side];
			final int i1 = isBlockHeadOfBed(meta) ? 1 : 0;
			return (i1 != 1 || l != 2) && (i1 != 0 || l != 3) ? l != 5 && l != 4 ? texturesTop[i1] : texturesSide[i1] : texturesEnd[i1];
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconregister) {
		this.texturesTop = new IIcon[] {iconregister.registerIcon(References.MODID + ":blockstrawbed_foot_top"), iconregister.registerIcon(References.MODID + ":blockstrawbed_head_top")};
		this.texturesSide = new IIcon[] {iconregister.registerIcon(References.MODID + ":blockstrawbed_foot_side"), iconregister.registerIcon(References.MODID + ":blockstrawbed_head_side")};
		this.texturesEnd = new IIcon[] {iconregister.registerIcon(References.MODID + ":blockstrawbed_foot_end"), iconregister.registerIcon(References.MODID + ":blockstrawbed_head_end")};
	}
	
}
