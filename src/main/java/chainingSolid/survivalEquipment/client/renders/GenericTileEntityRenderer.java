package chainingSolid.survivalEquipment.client.renders;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class GenericTileEntityRenderer extends TileEntitySpecialRenderer {
	
	@Override
	public void renderTileEntityAt(TileEntity te, double posX, double posY, double posZ, float p_180535_8_, int p_180535_9_) {
		
		if(te instanceof IGenericRenderableTileEntity){
			GenericTileEntityModel model = ((IGenericRenderableTileEntity)te).getModel();
			
			GlStateManager.pushMatrix();
			GlStateManager.translate((float)posX, (float)posY, (float)posZ);
			
			this.bindTexture(((IGenericRenderableTileEntity)te).getTexture());
			model.render();
			
			GlStateManager.popMatrix();
		}else{
			System.out.println(""+te+" does not implement IGenericRenderableTileEntity");
		}
	}

}
