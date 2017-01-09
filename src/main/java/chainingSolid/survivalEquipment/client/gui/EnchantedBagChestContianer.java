package chainingSolid.survivalEquipment.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import chainingSolid.survivalEquipment.tileEnties.EnchantedBagChestTile;
import chainingSolid.survivalEquipment.util.GenericInventory;
import chainingSolid.survivalEquipment.util.SlotButton;
import chainingSolid.survivalEquipment.util.Util;
import chainingSolid.survivalEquipment.util.enchantedBag.EnchantedBagInventory;

public class EnchantedBagChestContianer extends SurvivalEquipmentContainer {
	
	private EnchantedBagChestTile tile;
	
	private boolean isABagSelected = false;
	
	private ItemStack currentBag;
	private int currentSlot;
	
	private GenericInventory removeBagInv = new GenericInventory(1);
	
	
	/**
	 * TODO allow add/removing of bags in chest from within the gui
	 * TODO add buttons for opening
	 * TODO track open bags in chest
	 * TODO when a player removes bag notify others with the gui open
	 * 
	 */
	
	
	
	
	
	EnchantedBagChestContianer(EntityPlayer player,EnchantedBagChestTile tile) {
		super(player);
		this.tile = tile;
		this.setPlayerInvPosition(0, SLOT_SIZE*8);
		tile.openInventory(player);
		this.buildContianer();
	}
	
	@Override
	public void buildContianer(){
		super.buildContianer();
		addBags();
		if(isABagSelected){
			this.slotSetMap.put(MAIN_INV, new SlotSet(MAIN_INV,
					START_OF_SUBCLASS_SLOTS+tile.bagsInChest.getSizeInventory(),
					START_OF_SUBCLASS_SLOTS+tile.bagsInChest.getSizeInventory()+(EnchantedBagInventory.BAG_INV_HIEGHT*EnchantedBagInventory.BAG_INV_WIDTH),
					this.PLAYER_INV_SLOT_SET_NAME));
			this.addCurrentBagsInv();
			this.addRemoveButton();
		}
		this.detectAndSendChanges();
	}
	
	private void addBags(){
		for(int slot = 0; slot < tile.bagsInChest.getSizeInventory();slot++){
			this.addSlotButton(tile.bagsInChest, slot, SLOT_SIZE*slot, SLOT_SIZE*6 + 10, this, ""+slot);
		}
	}
	
	private void addCurrentBagsInv(){
		EnchantedBagInventory inv = tile.bagInvs[currentSlot];
		int index = 0;
		for(int x = 0; x < inv.BAG_INV_WIDTH;x++){
			for(int y = 0; y < inv.BAG_INV_HIEGHT;y++){
				this.addSlot(inv, index, x*SLOT_SIZE, y*SLOT_SIZE + 10);
				index++;
			}
		}
		
	}
	
	private void addRemoveButton(){
		this.addSlotButton(removeBagInv, 0, SLOT_SIZE * 10, SLOT_SIZE * 6, this, "remove");
		ItemStack removeStack = new ItemStack(Items.ARROW);
		removeStack.setTagCompound(new NBTTagCompound());
		NBTTagCompound nameTag = new NBTTagCompound();
		nameTag.setString("Name","click to remove current bag");
		removeStack.getTagCompound().setTag("display", nameTag);
		removeBagInv.setInventorySlotContents(0,removeStack);
	}
	
	@Override
	public void onContainerClosed(EntityPlayer player){
		if(isABagSelected){
			this.closeCurrentBag();
		}
		tile.closeInventory(player);
		super.onContainerClosed(player);
	}
	
	private void closeCurrentBag() {
		tile.bagInvs[currentSlot].setThisBagNoLongerInUse(currentBag);
		tile.bagInvs[currentSlot].saveBagToStack(currentBag);
	}
	
	@Override
	public void onSlotButtonClicked(EntityPlayer player, SlotButton button, String returnMessage) {
		super.onSlotButtonClicked(player, button, returnMessage);
		if(returnMessage.equals("remove") && this.isABagSelected){
			if(tile.removeBag(currentSlot, player)){
				this.isABagSelected = false;
				this.currentBag = null;
				this.currentSlot = -1;
				this.buildContianer();
			}
			return;
		}
		
		int slot;
		try{
			slot = Integer.decode(returnMessage);
		}catch(NumberFormatException e){
			return;
		}
		if(isABagSelected){
			this.closeCurrentBag();
		}
		currentSlot = slot;
		
		currentBag = tile.bagsInChest.getStackInSlot(slot);
		tile.bagInvs[slot].setThisBagInUse(currentBag);
		this.isABagSelected = true;
		
		this.buildContianer();
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex){
		if(!this.isABagSelected){
			return null;
		}
		return super.transferStackInSlot(player, slotIndex);
	}
	
}
