package chainingSolid.survivalEquipment.tileEnties;

import chainingSolid.survivalEquipment.util.GenericInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;

public class EnchantedEnderDiggerMarkerTile extends TileEntity implements
		IInventory {
	
	public GenericInventory inventory = new GenericInventory(24){
		
		@Override
		public boolean isItemValidForSlot(int slot, ItemStack stack){
			if(slot < this.getSizeInventory()/2){
				if(stack.getItem() instanceof ItemTool){
					return true;
				}else{
					return false;
				}
			}else{
				if(stack.getItem() instanceof ItemBlock){
					return true;
				}else{
					return false;
				}
			}
		}
		
	};
	
	@Override
	public int getSizeInventory() {
		return inventory.getSizeInventory();
	}
	
	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory.getStackInSlot(slot);
	}
	
	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		return inventory.decrStackSize(slot, amount);
	}
	
	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inventory.setInventorySlotContents(slot, stack);
	}
	
	@Override
	public int getInventoryStackLimit() {
		return inventory.getInventoryStackLimit();
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return inventory.isUseableByPlayer(player);
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return inventory.isItemValidForSlot(slot, stack);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		tag.setTag("inv", inventory.writeInventoryToTag());
		return tag;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		inventory.setInventoryFromTag(tag.getCompoundTag("inv"));
	}
	
	public void onDestroyed(){
		inventory.onDestroyed(worldObj, pos.getX(),pos.getY(),pos.getZ());
	}
	
	@Override
	public String getName() {
		return null;
	}
	
	@Override
	public boolean hasCustomName() {
		return false;
	}
	
	@Override
	public void openInventory(EntityPlayer player) {
		
	}
	
	@Override
	public void closeInventory(EntityPlayer player) {
		
	}
	
	@Override
	public int getField(int id) {
		return inventory.getField(id);
	}
	
	@Override
	public void setField(int id, int value) {
		inventory.setField(id, value);
	}
	
	@Override
	public int getFieldCount() {
		return inventory.getFieldCount();
	}
	
	@Override
	public void clear() {
		inventory.clear();
	}
	
	@Override
	public ITextComponent getDisplayName() {
		return inventory.getDisplayName();
	}
	
	@Override
	public ItemStack removeStackFromSlot(int index) {
		return inventory.removeStackFromSlot(index);
	}
	
}
