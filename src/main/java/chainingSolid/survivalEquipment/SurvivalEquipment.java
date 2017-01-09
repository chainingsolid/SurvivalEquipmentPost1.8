package chainingSolid.survivalEquipment;

import chainingSolid.survivalEquipment.achievement.SurvivalEquipmentAchievements;
import chainingSolid.survivalEquipment.client.gui.GuiHandler;
import chainingSolid.survivalEquipment.event.SurvivalEquipmentEventHandler;
import chainingSolid.survivalEquipment.items.shapeBuildingHelper.ShapeBuildingHelperCommand;
import chainingSolid.survivalEquipment.loading.CommonProxy;
import chainingSolid.survivalEquipment.loading.SULoader;
import chainingSolid.survivalEquipment.network.EventChannnelHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = SurvivalEquipment.MOD_ID, name="survivalEquipment", version = "0.5")
public class SurvivalEquipment {
	
	public static final String MOD_ID = "survivalequipment";
	
	@Instance
	public static SurvivalEquipment instance = new SurvivalEquipment();
	@SidedProxy(modId="survivalequipment",clientSide="chainingSolid.survivalEquipment.loading.ClientProxy",serverSide="chainingSolid.survivalEquipment.loading.CommonProxy")
	public static CommonProxy proxy;
	
	public static SurvialEquipmentTab tab = new SurvialEquipmentTab("SurvivalEquipment");
	
	public static GuiHandler guiHandler;
	
	public static final SULoader LOADER = new SULoader();
	
	public static final String MODRESOURCEPATH= MOD_ID+":";
	public static final String CHANNEL_NAME = "SurvivalEquipment";
	
	public static final SurvivalEquipmentEventHandler eventHandler = new SurvivalEquipmentEventHandler();
	
	public static FMLEventChannel channel = NetworkRegistry.INSTANCE.newEventDrivenChannel(CHANNEL_NAME);
	
	/*
	 * TODO this is the Todo/changes to make section
	 * 
	 * 1 improve the enchanted bag chest gui 
	 * 
	 */
	
	/* TODO ideas
	 * chandiler/light amplifer
	 * a te that takes a player set area nearby
	 *  takes the light emitting blocks in it and scales the structure up placing "lights" where the light emting blocks would be
	 * 
	 * 
	 */
	
	
	@EventHandler
	public void preInint(FMLPreInitializationEvent event){
		proxy.preInit(event);
		guiHandler = new GuiHandler();
		
	}
	
	@EventHandler
	public void inint(FMLInitializationEvent event){
		proxy.Init(event);
		SurvivalEquipmentAchievements.addAchievements();
		
		channel.register(new EventChannnelHandler());
	}
	
	@EventHandler
	public void postInint(FMLPostInitializationEvent event){
		proxy.postInit(event);
	}
	
	@EventHandler
	public void serverLoad(FMLServerStartingEvent event){
		event.registerServerCommand(ShapeBuildingHelperCommand.INSTANCE);
	}
	
	
	
}
