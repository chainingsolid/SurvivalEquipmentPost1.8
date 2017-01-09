package chainingSolid.survivalEquipment.network.EventData;

import java.io.IOException;

import chainingSolid.survivalEquipment.network.EventChannnelHandler;
import chainingSolid.survivalEquipment.tileEnties.EnchantedBagChestTile;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EnchantedBagChestData extends EventData {
	
	public NBTTagCompound chestTag;
	public int dimId , xCoord, yCoord, zCoord;
	
	@Override
	public void writeFromByteBuffer(ByteBuf buffer) {
		PacketBuffer buf = new PacketBuffer(buffer);
		try {
			this.chestTag = buf.readNBTTagCompoundFromBuffer();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		this.dimId = buf.readInt();
		this.xCoord = buf.readInt();
		this.yCoord = buf.readInt();
		this.zCoord = buf.readInt();
		
	}
	
	@Override
	public PacketBuffer writeDataToArray() {
		PacketBuffer buffer = new PacketBuffer(Unpooled.buffer());
		buffer.writeByte(EventChannnelHandler.EVENT_DATA_ENCHANTED_BAG_CHEST);
		buffer.writeNBTTagCompoundToBuffer(chestTag);
		buffer.writeInt(dimId);
		buffer.writeInt(xCoord);
		buffer.writeInt(yCoord);
		buffer.writeInt(zCoord);
		return buffer;
	}
	
	@Override
	public void act() {
		World world = Minecraft.getMinecraft().theWorld;
		if(world != null){
			if(world.provider.getDimension() == dimId){
				TileEntity te = world.getTileEntity(new BlockPos(xCoord,yCoord,zCoord));
				if(te instanceof EnchantedBagChestTile){
					EnchantedBagChestTile chestTile = (EnchantedBagChestTile)te;
					chestTile.readFromNBT(chestTag);
				}
			}
		}
	}
	
}
