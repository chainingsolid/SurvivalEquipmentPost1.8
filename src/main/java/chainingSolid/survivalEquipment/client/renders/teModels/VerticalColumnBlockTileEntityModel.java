package chainingSolid.survivalEquipment.client.renders.teModels;

import net.minecraft.client.model.ModelRenderer;
import chainingSolid.survivalEquipment.client.renders.GenericTileEntityModel;

public class VerticalColumnBlockTileEntityModel extends GenericTileEntityModel {
	
	public ModelRenderer column;
	
	public VerticalColumnBlockTileEntityModel(){
		column = new ModelRenderer(this,0,0);
		column.addBox(-.0F, -.0F, -.0F, 16, 16, 16);
		column.setTextureSize(64, 64);
	}
	
	@Override
	public void render() {
		column.render(.0625F);
	}

}
