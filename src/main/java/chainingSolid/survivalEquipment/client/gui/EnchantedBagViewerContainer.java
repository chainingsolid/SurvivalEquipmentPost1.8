package chainingSolid.survivalEquipment.client.gui;

import chainingSolid.survivalEquipment.items.EnchantedBag;
import chainingSolid.survivalEquipment.util.SlotButton;
import chainingSolid.survivalEquipment.util.SlotButtonEventReciver;
import chainingSolid.survivalEquipment.util.enchantedBag.EnchantedBagInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;



public class EnchantedBagViewerContainer extends SurvivalEquipmentContainer implements SlotButtonEventReciver{
	
	public boolean isShowingABag = false;
	
	public ItemStack currentBag;
	
	public int showingBagIndexInPlayerInv;
	
	public EnchantedBagInventory currentBagInv;
	
	public EnchantedBagViewerContainer(EntityPlayer player){
		super(player);
		this.setPlayerInvPosition(0, SLOT_SIZE*8);
		
		this.buildContianer();
	}
	
	public void buildContianer(){
		super.buildContianer();
		if(isShowingABag){
			this.slotSetMap.put(MAIN_INV, new SlotSet(MAIN_INV,START_OF_SUBCLASS_SLOTS,START_OF_SUBCLASS_SLOTS+(EnchantedBagInventory.BAG_INV_HIEGHT*EnchantedBagInventory.BAG_INV_WIDTH),this.PLAYER_INV_SLOT_SET_NAME));
			this.addBagSlots();
		}
	}
	
	public void addBagSlots(){
		int index = 0;
		for(int x = 0; x < this.currentBagInv.BAG_INV_WIDTH;x++){
			for(int y = 0; y < this.currentBagInv.BAG_INV_HIEGHT;y++){
				this.addSlot(currentBagInv, index, x*SLOT_SIZE, (y+2)*SLOT_SIZE);
				index++;
			}
		}
	}
	
	@Override
	public void onContainerClosed(EntityPlayer player){
		if(isShowingABag){
			this.closeCurrentBag();
		}
		super.onContainerClosed(player);
	}
	
	public void addSlot(IInventory inv,int indexInInv,int x,int y){
		ItemStack stack = inv.getStackInSlot(indexInInv);
		if(stack != null){
			if(stack.getItem() instanceof EnchantedBag){
				this.addSlotButton(inv, indexInInv, x, y, this, ""+indexInInv);
				return;
			}
		}
		super.addSlot(inv, indexInInv, x, y);
	}
	
	
	@Override
	public void onSlotButtonClicked(EntityPlayer player, SlotButton button, String returnMessage) {
		super.onSlotButtonClicked(player, button, returnMessage);
		int index;
		try{
			index = Integer.parseInt(returnMessage);
		}catch(NumberFormatException e){
			System.out.println("failed");
			return;
		}
		ItemStack stack = this.player.inventory.getStackInSlot(index);
		if(stack != null){
			if(stack.getItem() instanceof EnchantedBag){
				if(isShowingABag){
					this.closeCurrentBag();
				}
				this.showingBagIndexInPlayerInv = index;
				this.isShowingABag = true;
				this.currentBag = stack;
				this.currentBagInv = new EnchantedBagInventory(stack);
				this.currentBagInv.setThisBagInUse(currentBag);
				this.buildContianer();
			}
		}	
	}
	
	public void closeCurrentBag(){
		this.currentBagInv.setThisBagNoLongerInUse(currentBag);
		this.currentBagInv.saveBagToStack(currentBag);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}
	
	@Override //TODO figure this out
	public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex){
		
		if(!this.isShowingABag){
			return null;
		}
		
		return super.transferStackInSlot(player, slotIndex);
	}
	
	
}
