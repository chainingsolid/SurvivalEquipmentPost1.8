package chainingSolid.survivalEquipment.loading;

import chainingSolid.survivalEquipment.tileEnties.EnchantedBagChestTile;
import chainingSolid.survivalEquipment.tileEnties.EnchantedEnderDiggerMarkerTile;
import chainingSolid.survivalEquipment.tileEnties.EnchantedEnderDiggerTile;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntities {
	
	public static void preInint(FMLPreInitializationEvent event) {
		GameRegistry.registerTileEntity(EnchantedEnderDiggerTile.class, "EnchantedEnderDiggerTile");
		GameRegistry.registerTileEntity(EnchantedBagChestTile.class, "EnchantedBagChestTile");
		GameRegistry.registerTileEntity(EnchantedEnderDiggerMarkerTile.class, "EnchantedEnderDiggerTileMarker");
	}
	
}
