package thepwnd.client.render.tileentity;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import thepwnd.model.ModelWickerBasket;
import thepwnd.reference.References;

public class RenderWickerBasket extends TileEntitySpecialRenderer {
	
	private ModelWickerBasket model;
	private ResourceLocation texture = new ResourceLocation(References.MODID + ":textures/blocks/wicker.png");
	
	public RenderWickerBasket() {
		model = new ModelWickerBasket();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z,	float scale) {
		// TODO Auto-generated method stub
		
		GL11.glPushMatrix();
			GL11.glTranslated(x + 0.5, y - 1.5, z + 0.5);
			GL11.glRotated(0, 1, 1, 1);
			this.bindTexture(texture);
			this.model.render((Entity)null, 0, -0.1F, 0, 0, 0, 0.0625F);
		GL11.glPopMatrix();
		
	}

}
