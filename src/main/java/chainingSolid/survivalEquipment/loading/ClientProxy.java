package chainingSolid.survivalEquipment.loading;

import chainingSolid.survivalEquipment.SurvivalEquipment;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
	
	public ClientProxy() {
			
	}
	
	public void preInit(FMLPreInitializationEvent event){
		super.preInit(event);
		SurvivalEquipment.LOADER.addTileEntityRenderers(event);
		SurvivalEquipment.LOADER.clientPreInint(event);
	}
	
	@Override
	public boolean isClient(){
		return true;
	}
}
