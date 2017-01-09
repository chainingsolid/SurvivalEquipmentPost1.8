package chainingSolid.survivalEquipment.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class EnchantedEnderDiggerGui extends SurvivalEquipmentGui {
	
	private static final ResourceLocation TEXTURE = new ResourceLocation("survivalutils", "textures/gui/enchantedbagviewer.png");
	
	ContainerEnchantedEnderDigger contianer;
	
	protected final int SLOT_SIZE = 20;
	
	private GuiTextField instructions;
	
	public EnchantedEnderDiggerGui(ContainerEnchantedEnderDigger container){
		super(container);
		this.contianer = container;
		
		this.xSize = container.xSize;
		this.ySize = container.ySize;
		
		
	}
	
	
}
