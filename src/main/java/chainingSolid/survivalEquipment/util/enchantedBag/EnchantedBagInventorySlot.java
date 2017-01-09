package chainingSolid.survivalEquipment.util.enchantedBag;

import chainingSolid.survivalEquipment.items.EnchantedBag;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class EnchantedBagInventorySlot extends Slot {
	
	public EnchantedBagInventorySlot(IInventory par1iInventory, int slotIndex, int displayX, int displayY) {
		super(par1iInventory, slotIndex, displayX, displayY);
		
	}
	
	@Override
	public boolean isItemValid(ItemStack stack){
		if(stack.getItem() instanceof EnchantedBag){
			return false;
		}else{
			return true;
		}
	}
	
	
}
