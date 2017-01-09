package chainingSolid.survivalEquipment.network.packet;

import io.netty.buffer.ByteBuf;

public class TestPacket extends SUPacket {
	
	public int testInt;
	
	public TestPacket(int testInt) {
		this.testInt = testInt;
	}
	
	public TestPacket(){
		
	}
	
	@Override
	public void encode(ByteBuf data) {
		data.writeByte(TEST_PACKET);
		data.writeInt(testInt);
	}
	
	@Override
	public void deEncode(ByteBuf data) {
		super.deEncode(data);
		testInt = data.readInt();
	}
	
}
