package chainingSolid.survivalEquipment.util.enchantedBag;

import chainingSolid.survivalEquipment.util.SlotButton;
import chainingSolid.survivalEquipment.util.SlotButtonEventReciver;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class EnchantedBagSlot extends SlotButton {
	
	public EnchantedBagInventory bagInv;
	
	public EnchantedBagSlot(IInventory inv, int slotIndex, int displayX, int displayY, 
			SlotButtonEventReciver reciver, EnchantedBagInventory bagInv){
		super(inv, slotIndex, displayX, displayY, reciver, "");
		this.bagInv = bagInv;
		
	}
	
}
