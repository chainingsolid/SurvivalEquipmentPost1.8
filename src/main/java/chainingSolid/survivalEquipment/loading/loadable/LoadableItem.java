package chainingSolid.survivalEquipment.loading.loadable;

import chainingSolid.survivalEquipment.loading.SULoader;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface LoadableItem extends Loadable {
	
	public Item getInstance();
	
	public void registerModels(FMLPreInitializationEvent event, SULoader loader);
}
