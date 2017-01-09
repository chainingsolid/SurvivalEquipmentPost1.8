package chainingSolid.survivalEquipment.network.EventData;

import java.util.HashMap;

import io.netty.buffer.ByteBuf;

public abstract class EventData {
	
	public abstract void writeFromByteBuffer(ByteBuf buffer);
	
	public abstract ByteBuf writeDataToArray();
	
	public abstract void act();
}
