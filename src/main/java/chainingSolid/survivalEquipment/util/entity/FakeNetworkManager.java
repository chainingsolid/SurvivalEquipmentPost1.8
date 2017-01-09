package chainingSolid.survivalEquipment.util.entity;

import java.net.InetAddress;
import java.net.SocketAddress;

import javax.crypto.SecretKey;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.EnumPacketDirection;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetworkManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FakeNetworkManager extends NetworkManager {
	
	public FakeNetworkManager(EnumPacketDirection packetDirection) {
		super(packetDirection);
		
	}
	
	@Override
	public void channelActive(ChannelHandlerContext p_channelActive_1_) throws Exception {}
	
	@Override
	public void setConnectionState(EnumConnectionState p_150723_1_) {}
	
	@Override
	public void channelInactive(ChannelHandlerContext p_channelInactive_1_) {}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext p_exceptionCaught_1_, Throwable p_exceptionCaught_2_) {}
	
	@Override
	public void setNetHandler(INetHandler p_150719_1_) {}
	
	@Override
	public void processReceivedPackets() {}
	
	@Override
	public boolean isLocalChannel() {return false;}
	
	@SideOnly(Side.CLIENT)
	public static NetworkManager provideLanClient(InetAddress p_150726_0_, int p_150726_1_) {return null;}
	
	@SideOnly(Side.CLIENT)
	public static NetworkManager provideLocalClient(SocketAddress p_150722_0_) {return null;}
	
	@Override
	public void enableEncryption(SecretKey p_150727_1_) {}
	
	@Override
	public boolean isChannelOpen() {return false;}
	
	@Override
	public INetHandler getNetHandler() {return null;}
	
	@Override
	public void disableAutoRead() {}
	
	@Override
	public Channel channel() {return null;}}
