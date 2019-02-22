package thepwnd.client.render.items;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

public class GenericBlockItemRenderer implements IItemRenderer {
	
	private TileEntity tileEntity;
	private TileEntitySpecialRenderer specialRenderer;
	
	public GenericBlockItemRenderer(TileEntity tileEntity, TileEntitySpecialRenderer specialRenderer) {
		this.tileEntity = tileEntity;
		this.specialRenderer = specialRenderer;
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		GL11.glPushMatrix();
			TileEntityRendererDispatcher.instance.renderTileEntityAt(tileEntity, 0, 0, 0, 0);
		GL11.glPopMatrix();
		
	}

}
