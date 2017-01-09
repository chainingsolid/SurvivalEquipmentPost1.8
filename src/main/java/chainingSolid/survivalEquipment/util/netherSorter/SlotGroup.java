package chainingSolid.survivalEquipment.util.netherSorter;

import net.minecraft.inventory.ISidedInventory;
import net.minecraft.util.EnumFacing;

public class SlotGroup {
	
	public int[] slots;
	
	public SlotGroupItemList itemList;
	
	public SlotGroupOperator operator;
	
	public EnumFacing side;
	
	private boolean isEnabled = false;
	
	public SlotGroup(){
		
	}
	
	public void tick(ISidedInventory inv){
		
	}
	
	public boolean isEnabled(){
		return isEnabled && areEnablementRequirementsMet();
	}
	
	public boolean areEnablementRequirementsMet(){
		if(slots == null || itemList == null || operator == null || side == null){
			return false;
		}else{
			return true;
		}
	}
	
	public void setEnablement(boolean enablement){
		isEnabled = enablement;
	}
	
}
