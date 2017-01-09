package chainingSolid.survivalEquipment.util;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotThatTakesInToAccountIsItemVaildForSlot extends Slot {
	
	int indexInInv;
	
	public SlotThatTakesInToAccountIsItemVaildForSlot(IInventory inv, int indexInInv, int displayX, int displayY) {
		super(inv, indexInInv, displayX, displayY);
		this.indexInInv = indexInInv;
	}
	
	
	@Override
	public boolean isItemValid(ItemStack par1ItemStack){
		return this.inventory.isItemValidForSlot(indexInInv, par1ItemStack);
	}
	
}
