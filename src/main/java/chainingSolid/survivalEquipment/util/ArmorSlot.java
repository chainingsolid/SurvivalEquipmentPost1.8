package chainingSolid.survivalEquipment.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class ArmorSlot extends SlotThatTakesInToAccountIsItemVaildForSlot {
	
	public final int SLOT;
	public EntityPlayer thePlayer;
	
	public ArmorSlot(IInventory inv, int indexInInv, int displayX, int displayY,int slot,EntityPlayer p) {
		super(inv, indexInInv, displayX, displayY);
		SLOT = slot;
		thePlayer = p;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack){
		if(stack != null){
			return stack.getItem().isValidArmor(stack, EntityEquipmentSlot.values()[SLOT], thePlayer);
		}else{
			return false;
		}
	}
	
	
}
