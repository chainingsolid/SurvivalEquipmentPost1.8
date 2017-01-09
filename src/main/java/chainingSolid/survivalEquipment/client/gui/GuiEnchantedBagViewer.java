package chainingSolid.survivalEquipment.client.gui;

import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.opengl.GL11;

public class GuiEnchantedBagViewer extends SurvivalEquipmentGui {
	
	
	
	public GuiEnchantedBagViewer(EnchantedBagViewerContainer container,EntityPlayer player) {
		super(container);
		
		
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
		super.drawGuiContainerBackgroundLayer(f, x, y);
		GL11.glColor4f(1, 1, 1, 1);
	}
	
	
	
}
