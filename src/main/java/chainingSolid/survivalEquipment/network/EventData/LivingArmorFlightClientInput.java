package chainingSolid.survivalEquipment.network.EventData;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import chainingSolid.survivalEquipment.network.EventChannnelHandler;

public class LivingArmorFlightClientInput extends EventData {
	
	public boolean isJumpKeyDown,
	isCrouchKeyDown,
	isForwardKeyDown,
	isBackwardKeyDown,
	isRightKeyDown,
	isLeftKeyDown,
	isFlightToggleDown
	;
	public int playerId;
	
	@Override
	public ByteBuf writeDataToArray() {
		ByteBuf buffer = Unpooled.buffer();
		buffer.writeByte(EventChannnelHandler.EVENT_DATA_LIVING_ARMOR_FLIGHT_CLIENT_INPUT_ID);
		buffer.writeBoolean(isJumpKeyDown);
		buffer.writeBoolean(isCrouchKeyDown);
		buffer.writeBoolean(isForwardKeyDown);
		buffer.writeBoolean(isBackwardKeyDown);
		buffer.writeBoolean(isRightKeyDown);
		buffer.writeBoolean(isLeftKeyDown);
		buffer.writeBoolean(isFlightToggleDown);
		buffer.writeInt(playerId);
		return buffer;
	}
	
	@Override
	public void writeFromByteBuffer(ByteBuf buffer) {
		this.isJumpKeyDown = buffer.readBoolean();
		this.isCrouchKeyDown = buffer.readBoolean();
		this.isForwardKeyDown = buffer.readBoolean();
		this.isBackwardKeyDown = buffer.readBoolean();
		this.isRightKeyDown = buffer.readBoolean();
		this.isLeftKeyDown = buffer.readBoolean();
		this.isFlightToggleDown = buffer.readBoolean();
		this.playerId = buffer.readInt();
	}
	
	@Override
	public void act() {
		
		
	}
	
}
