package thepwnd.client.gui.inventory;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import thepwnd.inventory.ContainerWickerBasket;

@SideOnly(Side.CLIENT)
public class GuiWickerBasket extends GuiContainer {
	
	private static final ResourceLocation texturePath = new ResourceLocation("textures/gui/container/hopper.png");
	private InventoryPlayer playerInventory;
	private IInventory basketInventory;
	private int inventoryRows;
	
	public GuiWickerBasket(InventoryPlayer inventoryPlayer, IInventory inventoryBasket) {
		super(new ContainerWickerBasket(inventoryPlayer, inventoryBasket));
		this.playerInventory = inventoryPlayer;
		this.basketInventory = inventoryBasket;
		this.inventoryRows = inventoryBasket.getSizeInventory() / 9;
		this.ySize = 133;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.fontRendererObj.drawString(this.basketInventory.hasCustomInventoryName() ? this.basketInventory.getInventoryName() : I18n.format(this.basketInventory.getInventoryName(), new Object[0]), 8, 6, 4210752);
		this.fontRendererObj.drawString(this.playerInventory.hasCustomInventoryName() ? this.playerInventory.getInventoryName() : I18n.format(this.playerInventory.getInventoryName(), new Object[0]), 8, this.ySize - 96 + 2, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texturePath);
        int marginHorizontal = (this.width - this.xSize) / 2;
        int marginVertical = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(marginHorizontal, marginVertical, 0, 0, this.xSize, this.ySize);
	}

}
