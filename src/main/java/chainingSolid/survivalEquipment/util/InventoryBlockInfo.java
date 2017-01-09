package chainingSolid.survivalEquipment.util;

import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;

public class InventoryBlockInfo {
	
	public final int X,Y,Z;
	public final IInventory INV;
	public final Block BLOCK; 
	
	public InventoryBlockInfo(int x,int y,int z,IInventory inv, Block block){
		X = x;
		Y = y;
		Z = z;
		INV = inv;
		BLOCK = block;
	}
	
}
