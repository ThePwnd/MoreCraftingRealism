package thepwnd.tileentity;

import java.util.Iterator;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import thepwnd.inventory.ContainerWickerBasket;
import thepwnd.reference.References;

public class TileEntityWickerBasket extends TileEntity implements IInventory {
	
	private ItemStack[] contents = new ItemStack[5];
	public int numPlayersUsing;
	private int ticksSinceSync;
	private int cachedContainerType;
	private String customName = "Wicker Basket";
	
	public TileEntityWickerBasket() {
		this.cachedContainerType = -1;
	}
	
	@SideOnly(Side.CLIENT)
	public TileEntityWickerBasket(int index) {
		this.cachedContainerType = index;
	}
	
	@Override
	public int getSizeInventory() {
		return this.contents.length;
	}
	
	@Override
	public ItemStack getStackInSlot(int index) {
		return this.contents[index];
	}
	
	@Override
	public ItemStack decrStackSize(int index, int count) {
		if (this.contents[index] != null) {
			ItemStack itemstack;
			
			if (this.contents[index].stackSize <= count) {
				itemstack = this.contents[index];
				this.contents[index] = null;
				this.markDirty();
				return itemstack;
			}
			else {
				itemstack = this.contents[index].splitStack(count);
				
				if (this.contents[index].stackSize == 0) {
					this.contents[index] = null;
				}
				
				this.markDirty();
				return itemstack;
			}
		}
		else {
			return null;
		}
	}
	
	@Override
	public ItemStack getStackInSlotOnClosing(int index) {
		if (this.contents[index] != null) {
			ItemStack itemstack = this.contents[index];
			this.contents[index] = null;
			return itemstack;
		}
		else {
			return null;
		}
	}
	
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		this.contents[index] = stack;
		
		if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
			stack.stackSize = this.getInventoryStackLimit();
		}
		
		this.markDirty();
	}
	
	@Override
	public String getInventoryName() {
		return customName;
	}
	
	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		NBTTagList taglist = compound.getTagList("Items", 10);
		this.contents = new ItemStack[this.getSizeInventory()];
		
		if (compound.hasKey("CustomName", 8)) {
			this.customName = compound.getString("CustomName");
		}
		
		for (int i = 0; i < taglist.tagCount(); i++) {
			NBTTagCompound compound1 = taglist.getCompoundTagAt(i);
			int j = compound1.getByte("Slot") & 255;
			
			if (j >= 0 && j < this.contents.length) {
				this.contents[j] = ItemStack.loadItemStackFromNBT(compound1);
			}
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		NBTTagList taglist = new NBTTagList();
		
		for (int i = 0; i < this.contents.length; i++) {
			if (this.contents[i] != null) {
				NBTTagCompound compound1 = new NBTTagCompound();
				compound1.setByte("Slot", (byte)i);
				this.contents[i].writeToNBT(compound1);
				taglist.appendTag(compound1);
			}
		}
		
		compound.setTag("Items", taglist);
		
		if (this.hasCustomInventoryName()) {
			compound.setString("CustomName", this.customName);
		}
	}
	
	@Override
	public int getInventoryStackLimit()
    {
        return 64;
    }
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}
	
	@Override
	public void updateContainingBlockInfo()
    {
        super.updateContainingBlockInfo();
    }
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		if (worldObj != null && !this.worldObj.isRemote && this.numPlayersUsing != 0 && (this.ticksSinceSync + this.xCoord + this.yCoord + this.zCoord) % 200 == 0) {
			this.numPlayersUsing = 0;
			float f = 5.0F;
			List list = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox((double)((float)this.xCoord - f), (double)((float)this.yCoord - f), (double)((float)this.zCoord - f), (double)((float)(this.xCoord + 1) + f), (double)((float)(this.yCoord + 1) + f), (double)((float)(this.zCoord + 1) + f)));
			Iterator iterator = list.iterator();
			
			while (iterator.hasNext()) {
				EntityPlayer player = (EntityPlayer)iterator.next();
				
				if (player.openContainer instanceof ContainerWickerBasket) {
					IInventory iinventory = ((ContainerWickerBasket)player.openContainer).getLowerChestInventory();
					
					if (iinventory == this) {
						++this.numPlayersUsing;
					}
				}
			}
		}
	}
	
	@Override
	public boolean receiveClientEvent(int p_145842_1_, int p_145842_2_)
    {
        if (p_145842_1_ == 1)
        {
            this.numPlayersUsing = p_145842_2_;
            return true;
        }
        else
        {
            return super.receiveClientEvent(p_145842_1_, p_145842_2_);
        }
    }

	
	@Override
	public void openInventory() {
		if(this.numPlayersUsing < 0) {
			this.numPlayersUsing = 0;
		}
		
		++this.numPlayersUsing;
		this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType(), 1, this.numPlayersUsing);
	}
	
	@Override
	public void closeInventory() {
		if (this.getBlockType() instanceof BlockChest) {
			--this.numPlayersUsing;
			this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType(), 1, this.numPlayersUsing);
		}
	}
	
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}
	
	public String getGuiId() {
		return References.MODID + ":wickerbasket";
		
	}
	
	public Container createContainer(InventoryPlayer inventory, EntityPlayer player) {
		// DEBUG
		System.out.println("TileEntityWickerBasket createContainer()");
		return new ContainerWickerBasket(inventory, this);
	}	
}
