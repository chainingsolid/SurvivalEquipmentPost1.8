package chainingSolid.survivalEquipment.util.enchantedBag;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import chainingSolid.survivalEquipment.items.EnchantedBag;
import chainingSolid.survivalEquipment.loading.ItemList;
import chainingSolid.survivalEquipment.util.GenericInventory;
import chainingSolid.survivalEquipment.util.Util;

public class EnchantedBagInventory extends GenericInventory {
	
	public ItemStack bag;
	
	public static final int BAG_INV_WIDTH = 9, BAG_INV_HIEGHT = 5;
	
	
	public EnchantedBagInventory(ItemStack bag) {
		super(BAG_INV_WIDTH * BAG_INV_HIEGHT);
		this.bag = bag;
		this.setInventoryFromBag(bag);
	}
	
	public void setInventoryFromBag(ItemStack bag){
		if(bag == null){
			return;
		}
		for(int index = 0;index < this.getSizeInventory(); index++){
			if(bag.getTagCompound() == null){
				bag.setTagCompound(new NBTTagCompound());
			}
			ItemStack stackInBag = ItemStack.loadItemStackFromNBT(bag.getTagCompound().getCompoundTag(""+index));
			this.setInventorySlotContents(index, stackInBag);
		}
	}
	
	public void saveBagToStack(ItemStack bag){
		for(int index = 0;index < this.getSizeInventory(); index++){
			bag.getTagCompound().setTag(""+index, new NBTTagCompound());
			ItemStack stackInBag = this.getStackInSlot(index);
			if(stackInBag != null){
				bag.getTagCompound().setTag(""+index, stackInBag.writeToNBT(new NBTTagCompound()));
			}
		}
	}
	
	public void setThisBagInUse(ItemStack stack){
		Util.nullNBTTagCheck(stack);
		ItemList.enchantedBag.setIsOpen(stack, true);
	}
	
	public void setThisBagNoLongerInUse(ItemStack stack){
		Util.nullNBTTagCheck(stack);
		ItemList.enchantedBag.setIsOpen(stack, false);
	}
	
	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		if(stack.getItem() instanceof EnchantedBag){
			return false;
		}else{
			return true;
		}
	}
	
	
}
