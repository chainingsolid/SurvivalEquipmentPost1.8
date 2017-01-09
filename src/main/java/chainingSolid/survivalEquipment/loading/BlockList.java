package chainingSolid.survivalEquipment.loading;

import chainingSolid.survivalEquipment.blocks.LightProjector;
import chainingSolid.survivalEquipment.blocks.EnchantedBagChest;
import chainingSolid.survivalEquipment.blocks.MobFarmFloor;
import chainingSolid.survivalEquipment.blocks.ReplaceableBlock;
import net.minecraft.block.material.Material;

public class BlockList {
	
	//public static EnchantedEnderDigger enchatntedEnderDigger = new EnchantedEnderDigger(Material.IRON);
	//public static EnchantedEnderDiggerMarker enchantedEnderDiggerMarker = new EnchantedEnderDiggerMarker(Material.IRON);
	//public static PlainEnchantedEnderDiggerMarker plainEnchantedEnderDiggerMarker = new PlainEnchantedEnderDiggerMarker(Material.IRON);
	
	public static MobFarmFloor mobFarmFloor = new MobFarmFloor(Material.ROCK);
	
	public static EnchantedBagChest enchantedBagChest = new EnchantedBagChest(Material.WOOD);
	
	public static LightProjector lightProjector = new LightProjector(Material.ROCK);
	
	public static ReplaceableBlock replaceableBlock = new ReplaceableBlock(Material.GLASS);
	
}
