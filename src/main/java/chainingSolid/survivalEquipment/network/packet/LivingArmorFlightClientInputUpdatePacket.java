package chainingSolid.survivalEquipment.network.packet;

import io.netty.buffer.ByteBuf;

public class LivingArmorFlightClientInputUpdatePacket extends SUPacket {
	
	public boolean isJumpKeyDown,
	isCrouchKeyDown,
	isForwardKeyDown,
	isBackwardKeyDown,
	isRightKeyDown,
	isLeftkeyDown,
	isFlightToggleDown
	;
	
	public int playerId;
	
	public float pitch;
	
	public LivingArmorFlightClientInputUpdatePacket() {
		
	}
	
	public void encode(ByteBuf data){
		data.writeByte(FLIGHT_UPDTAE_PACKET);
		data.writeBoolean(isJumpKeyDown);
		data.writeBoolean(isCrouchKeyDown);
		data.writeBoolean(isForwardKeyDown);
		data.writeBoolean(isBackwardKeyDown);
		data.writeBoolean(isRightKeyDown);
		data.writeBoolean(isLeftkeyDown);
		data.writeBoolean(isFlightToggleDown);
		data.writeInt(playerId);
		data.writeFloat(pitch);
	}
	
	public void deEncode(ByteBuf data){
		super.deEncode(data);
		this.isJumpKeyDown = data.readBoolean();
		this.isCrouchKeyDown = data.readBoolean();
		this.isForwardKeyDown = data.readBoolean();
		this.isBackwardKeyDown = data.readBoolean();
		this.isRightKeyDown = data.readBoolean();
		this.isLeftkeyDown = data.readBoolean();
		this.isFlightToggleDown = data.readBoolean();
		this.playerId = data.readInt();
		this.pitch = data.readFloat();
	}
	
}
