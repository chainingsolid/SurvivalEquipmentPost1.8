package chainingSolid.survivalEquipment.network;

import java.util.EnumMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.FMLEmbeddedChannel;
import net.minecraftforge.fml.common.network.FMLOutboundHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import chainingSolid.survivalEquipment.network.packet.SUPacket;

public class SurvivalUtilsNetworkHandler {
	
	public static final String CHANNEL_NAME = "SurvivalUtilsNetwork";
	
	public static EnumMap<Side,FMLEmbeddedChannel> channel;
	
	
	
	public SurvivalUtilsNetworkHandler() {
		
	}
	
	public static void setUpHandler(){
		channel = NetworkRegistry.INSTANCE.newChannel(CHANNEL_NAME, new PacketHandler());
	}
	
	public static void sendPacketToPlayer(EntityPlayer player, SUPacket packet){
		channel.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.PLAYER);
		channel.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(player);
		//channel.get(Side.SERVER).writeOutbound(packet);
	}
	
	public static void sendPacketToServer(SUPacket packet){
		channel.get(Side.CLIENT).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.TOSERVER);
		//channel.get(Side.CLIENT).writeOutbound(packet);
	}
	
}
