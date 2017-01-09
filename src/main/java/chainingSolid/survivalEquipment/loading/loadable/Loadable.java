package chainingSolid.survivalEquipment.loading.loadable;

import chainingSolid.survivalEquipment.loading.SULoader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface Loadable {
	
	public String getIdentifier();
	
	public void register(FMLPreInitializationEvent event, SULoader loader);
	
	
	
}
