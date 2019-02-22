package thepwnd.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerWickerBasket extends Container {
	
	private IInventory lowerInventory;
	private int numRows;
	
	public ContainerWickerBasket(InventoryPlayer inventoryPlayer, IInventory inventoryBasket) {
		//DEBUG
		System.out.println("ContainerWickerBasket constructor()");
		
		this.lowerInventory = inventoryBasket;
		int numSlots = inventoryBasket.getSizeInventory();
		inventoryBasket.openInventory();
		byte b0 = 51;
		
		for (int ordinalSlotContainer = 0; ordinalSlotContainer < numSlots; ordinalSlotContainer++) {
			this.addSlotToContainer(new Slot(inventoryBasket, ordinalSlotContainer, 44 + ordinalSlotContainer * 18, 20));
		}
		
		for (int ordinalRowPlayer = 0; ordinalRowPlayer < 3; ordinalRowPlayer++) {
			for (int ordinalSlotPlayer = 0; ordinalSlotPlayer < 9; ordinalSlotPlayer++) {
				this.addSlotToContainer(new Slot(inventoryPlayer, ordinalSlotPlayer + ordinalRowPlayer * 9 + 9, 8 + ordinalSlotPlayer * 18, ordinalRowPlayer * 18 + b0));
			}
		}
		
		for (int ordinalSlotHotbar = 0; ordinalSlotHotbar < 9; ordinalSlotHotbar++) {
			this.addSlotToContainer(new Slot(inventoryPlayer, ordinalSlotHotbar, 8 + ordinalSlotHotbar * 18, 58 + b0));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return this.lowerInventory.isUseableByPlayer(player);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
		ItemStack itemstack = null;
		Slot slot = (Slot)this.inventorySlots.get(slotIndex);
		
		if(slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			if(slotIndex < this.lowerInventory.getSizeInventory()) {
				if(!this.mergeItemStack(itemstack1, this.lowerInventory.getSizeInventory(), this.inventorySlots.size(), true)) {
					return null;
				}
			}
			else if(!this.mergeItemStack(itemstack1, 0, this.lowerInventory.getSizeInventory(), false)) {
				return null;
			}
			
			if(itemstack1.stackSize == 0) {
				slot.putStack((ItemStack)null);
			}
			else {
				slot.onSlotChanged();
			}
			
			if(itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}
			
			slot.onPickupFromSlot(player, itemstack1);
		}
		
		return itemstack;
	}
	
	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);
		this.lowerInventory.closeInventory();
	}
	
	public IInventory getLowerChestInventory() {
		return this.lowerInventory;
	}

}
