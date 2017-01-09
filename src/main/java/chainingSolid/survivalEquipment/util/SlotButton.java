package chainingSolid.survivalEquipment.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotButton extends SlotThatTakesInToAccountIsItemVaildForSlot {
	
	private SlotButtonEventReciver reciver;
	private String returnMessage;
	
	public SlotButton(IInventory inventory, int index, int x, int z, SlotButtonEventReciver reciver,String returnMessage) {
		super(inventory, index, x, z);
		this.reciver = reciver;
		this.returnMessage = returnMessage;
		this.slotNumber = index;
	}
	
	@Override
	public boolean canTakeStack(EntityPlayer player){
		reciver.onSlotButtonClicked(player, this, returnMessage);
		return false;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack){
		return false;
	}
	
}
