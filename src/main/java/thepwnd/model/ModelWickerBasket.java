package thepwnd.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Wicker Basket - ThePwnd
 * Created using Tabula 4.1.1
 */
public class ModelWickerBasket extends ModelBase {
    public ModelRenderer Base;
    public ModelRenderer LowerInnerRim;
    public ModelRenderer LowerInnerRim_1;
    public ModelRenderer LowerInnerRim_2;
    public ModelRenderer LowerInnerRim_3;
    public ModelRenderer Side;
    public ModelRenderer Side_1;
    public ModelRenderer Side_2;
    public ModelRenderer Side_3;
    public ModelRenderer Curve;
    public ModelRenderer Curve_1;
    public ModelRenderer Curve_2;
    public ModelRenderer Curve_3;

    public ModelWickerBasket() {
        this.textureWidth = 16;
        this.textureHeight = 16;
        this.Curve_3 = new ModelRenderer(this, 0, 0);
        this.Curve_3.setRotationPoint(4.0F, 26.0F, -5.0F);
        this.Curve_3.addBox(0.0F, 0.0F, 0.0F, 1, 7, 1, 0.0F);
        this.LowerInnerRim_1 = new ModelRenderer(this, 0, 0);
        this.LowerInnerRim_1.setRotationPoint(-5.0F, 25.0F, 4.0F);
        this.LowerInnerRim_1.addBox(0.0F, 0.0F, 0.0F, 8, 1, 1, 0.0F);
        this.setRotateAngle(LowerInnerRim_1, 0.0F, 1.5707963267948966F, 0.0F);
        this.Side = new ModelRenderer(this, 0, 0);
        this.Side.setRotationPoint(5.0F, 26.0F, 4.0F);
        this.Side.addBox(0.0F, 0.0F, 0.0F, 8, 7, 1, 0.0F);
        this.setRotateAngle(Side, 0.0F, 1.5707963267948966F, 0.0F);
        this.Side_2 = new ModelRenderer(this, 0, 0);
        this.Side_2.setRotationPoint(-4.0F, 26.0F, 5.0F);
        this.Side_2.addBox(0.0F, 0.0F, 0.0F, 8, 7, 1, 0.0F);
        this.LowerInnerRim_2 = new ModelRenderer(this, 0, 0);
        this.LowerInnerRim_2.setRotationPoint(-4.0F, 25.0F, 4.0F);
        this.LowerInnerRim_2.addBox(0.0F, 0.0F, 0.0F, 8, 1, 1, 0.0F);
        this.Base = new ModelRenderer(this, 0, 0);
        this.Base.setRotationPoint(-4.0F, 24.0F, -4.0F);
        this.Base.addBox(0.0F, 0.0F, 0.0F, 8, 1, 8, 0.0F);
        this.Curve_1 = new ModelRenderer(this, 0, 0);
        this.Curve_1.setRotationPoint(-5.0F, 26.0F, 4.0F);
        this.Curve_1.addBox(0.0F, 0.0F, 0.0F, 1, 7, 1, 0.0F);
        this.LowerInnerRim_3 = new ModelRenderer(this, 0, 0);
        this.LowerInnerRim_3.setRotationPoint(4.0F, 25.0F, 4.0F);
        this.LowerInnerRim_3.addBox(0.0F, 0.0F, 0.0F, 8, 1, 1, 0.0F);
        this.setRotateAngle(LowerInnerRim_3, 0.0F, 1.5707963267948966F, 0.0F);
        this.Curve_2 = new ModelRenderer(this, 0, 0);
        this.Curve_2.setRotationPoint(-5.0F, 26.0F, -5.0F);
        this.Curve_2.addBox(0.0F, 0.0F, 0.0F, 1, 7, 1, 0.0F);
        this.LowerInnerRim = new ModelRenderer(this, 0, 0);
        this.LowerInnerRim.setRotationPoint(-4.0F, 25.0F, -5.0F);
        this.LowerInnerRim.addBox(0.0F, 0.0F, 0.0F, 8, 1, 1, 0.0F);
        this.Side_1 = new ModelRenderer(this, 0, 0);
        this.Side_1.setRotationPoint(-4.0F, 26.0F, -6.0F);
        this.Side_1.addBox(0.0F, 0.0F, 0.0F, 8, 7, 1, 0.0F);
        this.Curve = new ModelRenderer(this, 0, 0);
        this.Curve.setRotationPoint(4.0F, 26.0F, 4.0F);
        this.Curve.addBox(0.0F, 0.0F, 0.0F, 1, 7, 1, 0.0F);
        this.Side_3 = new ModelRenderer(this, 0, 0);
        this.Side_3.setRotationPoint(-6.0F, 26.0F, 4.0F);
        this.Side_3.addBox(0.0F, 0.0F, 0.0F, 8, 7, 1, 0.0F);
        this.setRotateAngle(Side_3, 0.0F, 1.5707963267948966F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Curve_3.render(f5);
        this.LowerInnerRim_1.render(f5);
        this.Side.render(f5);
        this.Side_2.render(f5);
        this.LowerInnerRim_2.render(f5);
        this.Base.render(f5);
        this.Curve_1.render(f5);
        this.LowerInnerRim_3.render(f5);
        this.Curve_2.render(f5);
        this.LowerInnerRim.render(f5);
        this.Side_1.render(f5);
        this.Curve.render(f5);
        this.Side_3.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
