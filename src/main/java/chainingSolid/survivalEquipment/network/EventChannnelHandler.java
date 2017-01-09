package chainingSolid.survivalEquipment.network;

import chainingSolid.survivalEquipment.network.EventData.EnchantedBagChestData;
import chainingSolid.survivalEquipment.network.EventData.EnchantedEnderDiggerSyncData;
import chainingSolid.survivalEquipment.network.EventData.LivingArmorFlightClientInput;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ServerCustomPacketEvent;

public class EventChannnelHandler {
	
	
	public final static byte 
		EVENT_DATA_LIVING_ARMOR_FLIGHT_CLIENT_INPUT_ID = 0,
		EVENT_DATA_ENCHANTED_ENDER_DIGGER = 1,
		EVENT_DATA_ENCHANTED_BAG_CHEST = 2,
		EVENT_DATA_STUFF_MOVER_PIPE_BLOCK = 3;
	
	
	@SubscribeEvent
	public void onServerPacket(ServerCustomPacketEvent event) {
		ByteBuf buf = event.getPacket().payload();
		byte id = buf.readByte();
		switch(id){
		case EVENT_DATA_LIVING_ARMOR_FLIGHT_CLIENT_INPUT_ID:
			LivingArmorFlightClientInput data = new LivingArmorFlightClientInput();
			data.writeFromByteBuffer(buf);
			data.act();
			break;
		
		}
		
		
	}
	
	@SubscribeEvent
	public void onClientPacket(ClientCustomPacketEvent event) {
		ByteBuf buf = event.getPacket().payload();
		byte id = buf.readByte();
		switch(id){
		case EVENT_DATA_ENCHANTED_ENDER_DIGGER:
			EnchantedEnderDiggerSyncData SyncData = new EnchantedEnderDiggerSyncData();
			SyncData.writeFromByteBuffer(buf);
			SyncData.act();
			break;
			
		case EVENT_DATA_ENCHANTED_BAG_CHEST:
			EnchantedBagChestData chestData = new EnchantedBagChestData();
			chestData.writeFromByteBuffer(buf);
			chestData.act();
			break;
		}
	}
	
}
