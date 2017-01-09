package chainingSolid.survivalEquipment.client.renders;

import java.util.ArrayList;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class XPFleshModel extends ModelBase {
	
	private ArrayList<ModelRenderer> parts;
	
	public XPFleshModel() {
		parts = new ArrayList<ModelRenderer>();
		this.textureHeight = 64;
		this.textureWidth = 64;
		ModelRenderer testBox = new ModelRenderer(this,0,0);
		ModelRenderer body = new ModelRenderer(this,0,0);
		body.addBox(-1F, -5F, -3F, 2, 10, 6);
		body.setRotationPoint(0F, -6F, 0F);
		parts.add(body);
		
		
	}
	
	
	
	public void render(Entity par1Entity,float yaw ,float partialTickTime,float scale){
		for(ModelRenderer model:parts){
			model.render(scale);
		}
		
		
	}
	
}
