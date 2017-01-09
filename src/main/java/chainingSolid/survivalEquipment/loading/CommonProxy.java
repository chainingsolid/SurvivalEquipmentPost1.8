package chainingSolid.survivalEquipment.loading;

import chainingSolid.survivalEquipment.SurvivalEquipment;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	
	public CommonProxy() {
		
	}
	
	public void preInit(FMLPreInitializationEvent event){
		SurvivalEquipment.LOADER.preInit(event);
		TileEntities.preInint(event);
	}
	
	public void Init(FMLInitializationEvent event){
		SurvivalEquipment.LOADER.init(event);
		MinecraftForge.EVENT_BUS.register(SurvivalEquipment.eventHandler);
	}
	
	public void postInit(FMLPostInitializationEvent event){
		SurvivalEquipment.LOADER.postInit(event);
		
		
	}
	
	public boolean isClient(){
		return false;
	}
	
}
