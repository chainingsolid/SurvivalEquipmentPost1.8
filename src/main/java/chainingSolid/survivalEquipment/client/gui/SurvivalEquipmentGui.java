package chainingSolid.survivalEquipment.client.gui;

import org.lwjgl.opengl.GL11;

import chainingSolid.survivalEquipment.util.ArmorSlot;
import chainingSolid.survivalEquipment.util.SlotButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;

public class SurvivalEquipmentGui extends GuiContainer {
	
	private SurvivalEquipmentContainer contianer;
	public static final int SLOT_SIZE = 20;
	
	public static final ResourceLocation TEXTURE = new ResourceLocation("survivalequipment", "textures/gui/slot.png");
	
	public SurvivalEquipmentGui(SurvivalEquipmentContainer par1Container) {
		super(par1Container);
		this.contianer = par1Container;
		this.xSize = 256;
		this.ySize = 250;
		contianer.isClient = false;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1, 1, 1, 1);
		this.drawAllSlots();
	}
	
	private void drawAllSlots(){
		try{
			for(int i = 0; i < contianer.inventorySlots.size();i++){
				Slot s = (Slot) contianer.inventorySlots.get(i);
				if(s instanceof SlotButton){
					this.drawButtonSlot(s.xDisplayPosition, s.yDisplayPosition);
				}else{
					this.drawSlot(s.xDisplayPosition, s.yDisplayPosition);
				}
			}
		}catch(Exception e){
			
		}
	}
	
	private void drawPlayerInv(){
		for(int x = 0;x < 9;x++){
			this.drawSlot(x*SLOT_SIZE + contianer.playerInvX, 3*SLOT_SIZE + contianer.playerInvY);
		}
		for(int y = 0; y < 3;y++){
			for(int x = 0 ;x < 9; x++){
				this.drawSlot(x*SLOT_SIZE + contianer.playerInvX, y*SLOT_SIZE + contianer.playerInvY);
			}
		}
	}
	
	protected void drawSlot(int x,int y){
		Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE);
		this.drawTexturedModalRect(x + this.guiLeft - 2, y + this.guiTop - 2, 0, 0, SLOT_SIZE, SLOT_SIZE);
	}
	
	protected void drawButtonSlot(int x,int y){
		Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE);
		this.drawTexturedModalRect(x + this.guiLeft - 2, y + this.guiTop - 2, 20, 0, SLOT_SIZE, SLOT_SIZE);
	}
	
}
