package chainingSolid.survivalEquipment.util.netherSorter;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;

public abstract class SlotGroupOperator {
	
	public abstract void operate(SlotGroup group,ISidedInventory inv);
	
	public abstract IInventory getInputInv();
	
	public abstract IInventory getOutPutInv();
	
}
