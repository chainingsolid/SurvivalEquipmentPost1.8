package chainingSolid.survivalEquipment.client.gui;

import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;


public class SlotSet {
	
	public final String NAME,
	SLOT_SET_TO_SHIFT_CLICK_TO;
	
	public final int MIN_SLOT_INDEX;
	public final int MAX_SLOT_INDEX;
	
	public SlotSet(String name,int min,int max,String slotSetToShitClickTo){
		NAME = name;
		MIN_SLOT_INDEX = min;
		MAX_SLOT_INDEX = max;
		SLOT_SET_TO_SHIFT_CLICK_TO = slotSetToShitClickTo;
	}
	
	public boolean containesIndex(int index){
		if(MIN_SLOT_INDEX <= index && index <= MAX_SLOT_INDEX){
			return true;
		}else{
			return false;
		}
	}
	

	public boolean mergeItemStackInToThisSlotSet(ItemStack stack,SurvivalEquipmentContainer contianer){
		
		for(int index = MIN_SLOT_INDEX; index < MAX_SLOT_INDEX;index++){
			Slot slot = contianer.getSlot(index);
			if(this.canPutStackInSlot(slot, stack)){
				ItemStack stackInSlot = slot.getStack();
				
				if(stackInSlot == null){
					stackInSlot = stack.copy();
					stackInSlot.stackSize = 0;
					slot.putStack(stackInSlot);
				}
				
				while(stack.stackSize > 0 && stackInSlot.stackSize < stackInSlot.getMaxStackSize() && stackInSlot.stackSize < slot.getSlotStackLimit()){
					stack.stackSize--;
					stackInSlot.stackSize++;
				}
				
				
				slot.onSlotChanged();
				
				if(stack.stackSize == 0){
					return true;
				}
				
			}
			
			
		}
		
		return false;
	}
	
	public boolean canPutStackInSlot(Slot slot , ItemStack stack){
		ItemStack stackInSlot = slot.getStack();
		
		if(!slot.isItemValid(stack)){
			return false;
		}
		
		if(stackInSlot == null){
			return true;
		}
		
		if(!stackInSlot.isStackable()){
			return false;
		}
		
		if(stackInSlot.getItem() == stack.getItem()){
			if(stackInSlot.getItemDamage() == stack.getItemDamage()){
				if(ItemStack.areItemStackTagsEqual(stack, stackInSlot)){
					if(stackInSlot.stackSize < stackInSlot.getMaxStackSize() && stackInSlot.stackSize < slot.getSlotStackLimit()){
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	
}
