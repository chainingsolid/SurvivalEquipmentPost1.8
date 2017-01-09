package chainingSolid.survivalEquipment.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.ITextComponent;

public class InventoryToSidedInventory implements ISidedInventory {
	
	public final IInventory INV;
	
	private int[] accessibleSlots;
	
	public InventoryToSidedInventory(IInventory inv){
		INV = inv;
		genIntArrays();
	}
	
	private void genIntArrays(){
		accessibleSlots = new int[INV.getSizeInventory()];
		for(int i = 0;i < INV.getSizeInventory();i++){
			accessibleSlots[i] = i;
		}
		
	}
	
	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return this.accessibleSlots;
	}
	
	@Override
	public boolean canInsertItem(int slot, ItemStack item, EnumFacing side) {
		return INV.isItemValidForSlot(slot, item);
	}
	
	@Override
	public boolean canExtractItem(int slot, ItemStack item, EnumFacing side) {
		return true;
	}
	
	@Override
	public int getSizeInventory() {
		return INV.getSizeInventory();
	}
	
	@Override
	public ItemStack getStackInSlot(int slot) {
		return INV.getStackInSlot(slot);
	}
	
	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		return INV.decrStackSize(slot, amount);
	}
	
	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		INV.setInventorySlotContents(slot, stack);
	}
	
	@Override
	public int getInventoryStackLimit() {
		return INV.getInventoryStackLimit();
	}
	
	@Override
	public void markDirty() {
		INV.markDirty();
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return INV.isUseableByPlayer(player);
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return INV.isItemValidForSlot(slot, stack);
	}
	
	@Override
	public void openInventory(EntityPlayer player) {
		INV.closeInventory(player);
	}
	
	@Override
	public void closeInventory(EntityPlayer player) {
		INV.closeInventory(player);
	}
	
	@Override
	public int getField(int id) {
		return INV.getField(id);
	}
	
	@Override
	public void setField(int id, int value) {
		INV.setField(id, value);
	}
	
	@Override
	public int getFieldCount() {
		return INV.getFieldCount();
	}
	
	@Override
	public void clear() {
		INV.clear();
	}
	
	@Override
	public String getName() {
		return INV.getName();
	}
	
	@Override
	public boolean hasCustomName() {
		return INV.hasCustomName();
	}
	
	@Override
	public ItemStack removeStackFromSlot(int index) {
		return INV.removeStackFromSlot(index);
	}

	@Override
	public ITextComponent getDisplayName() {
		return null;
	}
	
	
}
