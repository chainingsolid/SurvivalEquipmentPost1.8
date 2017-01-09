package chainingSolid.survivalEquipment.network.packet;

import io.netty.buffer.ByteBuf;

public abstract class SUPacket {
	
	public static final int TEST_PACKET= 0,FLIGHT_UPDTAE_PACKET = 1;
	
	
	
	public int packetId;
	
	public SUPacket() {
		
	}
	
	public abstract void encode(ByteBuf data);
	
	public void deEncode(ByteBuf data){
		packetId = data.readByte();
	}
	
}
