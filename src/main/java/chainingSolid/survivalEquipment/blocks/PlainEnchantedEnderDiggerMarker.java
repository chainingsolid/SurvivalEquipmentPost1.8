package chainingSolid.survivalEquipment.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import chainingSolid.survivalEquipment.loading.SULoader;
import chainingSolid.survivalEquipment.loading.loadable.LoadableBlock;

public class PlainEnchantedEnderDiggerMarker extends Block implements LoadableBlock {
	
	public PlainEnchantedEnderDiggerMarker(Material material) {
		super(material);
		this.blockResistance=500;
		this.setHardness(4);
		this.setLightLevel(0F);
		
	}
	
	@Override
	public void register(FMLPreInitializationEvent event, SULoader loader) {
		loader.registerBlockWithItemVariant(this);
	}
	
	@Override
	public String getIdentifier() {
		return "plain_enchanted_ender_marker";
	}
	
	@Override
	public Block getInstance() {
		return this;
	}
	
}
