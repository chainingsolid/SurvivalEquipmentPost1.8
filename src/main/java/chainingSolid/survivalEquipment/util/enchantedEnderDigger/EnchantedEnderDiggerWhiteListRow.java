package chainingSolid.survivalEquipment.util.enchantedEnderDigger;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import chainingSolid.survivalEquipment.util.GenericInventory;

public class EnchantedEnderDiggerWhiteListRow extends GenericInventory {
	
	public EnchantedEnderDiggerWhiteListRow(int size) {
		super(size);
		
	}
	
	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		System.out.println("i is : "+i);
		if(i == 0 && stack.getItem() instanceof ItemTool){
			System.out.println("it's an item tool so it's allowed");
			return true;
		}else if(i > 0 && stack.getItem() instanceof ItemBlock){
			System.out.println("it's an item block so it's allowed");
			return true;
		}
		System.out.println("nope your not puting that stack here");
		return false;
	}
	
	public ItemStack getTool(){
		return this.getStackInSlot(0);
	}
	
	
	
}
