package chainingSolid.survivalEquipment.network.EventData;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import chainingSolid.survivalEquipment.network.EventChannnelHandler;

public class EnchantedEnderDiggerSyncData extends EventData {
	
	public int dimId;
	public int xcoord;
	public int ycoord;
	public int zcoord;
	
	public int XOffMin,XOffMax,YOffMin,YOffMax,ZOffMin,ZOffMax;
	
	public int currentXOff, currentYOff , currentZOff;
	
	@Override
	public void writeFromByteBuffer(ByteBuf buffer) {
		dimId = buffer.readInt();
		xcoord = buffer.readInt();
		ycoord= buffer.readInt();
		zcoord = buffer.readInt();
		XOffMin = buffer.readInt();
		XOffMax = buffer.readInt();
		YOffMin = buffer.readInt();
		YOffMax = buffer.readInt();
		ZOffMin = buffer.readInt();
		ZOffMax = buffer.readInt();
		currentXOff = buffer.readInt();
		currentYOff = buffer.readInt();
		currentZOff = buffer.readInt();
	}
	
	@Override
	public ByteBuf writeDataToArray() {
		ByteBuf buffer = Unpooled.buffer();
		buffer.writeByte(EventChannnelHandler.EVENT_DATA_ENCHANTED_ENDER_DIGGER);
		buffer.writeInt(dimId);
		buffer.writeInt(xcoord);
		buffer.writeInt(ycoord);
		buffer.writeInt(zcoord);
		buffer.writeInt(XOffMin);
		buffer.writeInt(XOffMax);
		buffer.writeInt(YOffMin);
		buffer.writeInt(YOffMax);
		buffer.writeInt(ZOffMin);
		buffer.writeInt(ZOffMax);
		buffer.writeInt(currentXOff);
		buffer.writeInt(currentYOff);
		buffer.writeInt(currentZOff);
		
		return buffer;
	}

	@Override
	public void act() {
		// TODO Auto-generated method stub
		
	}
	
	/*@Override
	public void act() {
		World world = Minecraft.getMinecraft().theWorld;
		if(world != null){
			if(world.provider.dimensionId == dimId){
				TileEntity te = world.getTileEntity(xcoord, ycoord, zcoord);
				if(te instanceof EnchantedEnderDiggerTile){
					EnchantedEnderDiggerTile diggerTile = (EnchantedEnderDiggerTile)te;
					diggerTile.XOffMin = this.XOffMin;
					diggerTile.XOffMax = this.XOffMax;
					diggerTile.YOffMin = this.YOffMin;
					diggerTile.YOffMax = this.YOffMax;
					diggerTile.ZOffMin = this.ZOffMin;
					diggerTile.ZOffMax = this.ZOffMax;
					diggerTile.currentXOff = this.currentXOff;
					diggerTile.currentYOff = this.currentYOff;
					diggerTile.currentZOff = this.currentZOff;
				}
			}
		}
	}
	*/
}
