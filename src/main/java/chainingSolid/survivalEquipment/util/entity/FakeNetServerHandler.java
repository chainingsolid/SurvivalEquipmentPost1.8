package chainingSolid.survivalEquipment.util.entity;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.EnumPacketDirection;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.client.CPacketClickWindow;
import net.minecraft.network.play.client.CPacketClientSettings;
import net.minecraft.network.play.client.CPacketClientStatus;
import net.minecraft.network.play.client.CPacketCloseWindow;
import net.minecraft.network.play.client.CPacketConfirmTransaction;
import net.minecraft.network.play.client.CPacketCreativeInventoryAction;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraft.network.play.client.CPacketEnchantItem;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketInput;
import net.minecraft.network.play.client.CPacketKeepAlive;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerAbilities;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketTabComplete;
import net.minecraft.network.play.client.CPacketUpdateSign;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;

public class FakeNetServerHandler extends NetHandlerPlayServer {
	
	public FakeNetServerHandler(MinecraftServer server, EntityPlayerMP player) {
		super(server, new FakeNetworkManager(EnumPacketDirection.CLIENTBOUND), player);
	}
	
	@Override
	public void kickPlayerFromServer(String reson){}
	
	@Override
	public void processInput(CPacketInput input){}
	
	@Override
	public void processPlayer(CPacketPlayer packet){}
	
	@Override
	public void setPlayerLocation(double x, double y, double z, float p_147364_7_, float p_147364_8_){}
	
	@Override
	public void processPlayerDigging(CPacketPlayerDigging packet){}
	
	@Override
	public void onDisconnect(ITextComponent chat){}
	
	@Override
	public void sendPacket(final Packet packet){}
	
	@Override
	public void processHeldItemChange(CPacketHeldItemChange itemChange){}
	
	@Override
	public void processChatMessage(CPacketChatMessage chatMsg){}
	
	@Override
	public void processEntityAction(CPacketEntityAction packet){}
	
	@Override
	public void processUseEntity(CPacketUseEntity packet){}
	
	@Override
	public void processClientStatus(CPacketClientStatus packet){}
	
	@Override
	public void processCloseWindow(CPacketCloseWindow packet){}
	
	@Override
	public void processClickWindow(CPacketClickWindow packet){}
	
	@Override
	public void processEnchantItem(CPacketEnchantItem packet){}
	
	@Override
	public void processCreativeInventoryAction(CPacketCreativeInventoryAction packet){}
	
	@Override
	public void processConfirmTransaction(CPacketConfirmTransaction packet){}
	
	@Override
	public void processUpdateSign(CPacketUpdateSign packet){}
	
	@Override
	public void processKeepAlive(CPacketKeepAlive packet){}
	
	@Override
	public void processPlayerAbilities(CPacketPlayerAbilities packet){}
	
	@Override
	public void processTabComplete(CPacketTabComplete packet){}
	
	@Override
	public void processClientSettings(CPacketClientSettings packet){}
	
}
