package chainingSolid.survivalEquipment.loading;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Config {
	//misc
	public static int stripMineInfoBlockRange = 200;
	
	
	private static Configuration config;
	public static void preInit(FMLPreInitializationEvent event){
		config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		
		
		//misc
		//stripMineInfoBlockRange = config.get("misc", "StripMineInfoBlockRange", 200).getInt();
		
		config.save();
	}
	
	private static int idGetterItems(int defaultId,String key){
		return 0;
	}
	private static int idGetterBlocks(int defaualtId,String key){
		return 0;
	}
	
}
