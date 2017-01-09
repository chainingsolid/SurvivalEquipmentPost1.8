package chainingSolid.survivalEquipment.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class GenericInventory implements IInventory {
	
	
	protected ItemStack[] inv;
	
	public GenericInventory(int size) {
		inv = new ItemStack[size];
	}
	
	@Override
	public int getSizeInventory() {
		return inv.length;
	}
	
	@Override
	public ItemStack getStackInSlot(int i) {
		return inv[i];
	}
	
	@Override
	public ItemStack decrStackSize(int i, int amount) {
		ItemStack stack = this.getStackInSlot(i);
		if(stack != null){
			if(stack.stackSize <= amount){
				this.setInventorySlotContents(i, null);
			}else{
				stack = stack.splitStack(amount);
			}
			
		}
		return stack;
	}
	
	@Override
	public void setInventorySlotContents(int i, ItemStack stack) {
		inv[i] = stack;
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}
	
	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		return true;
	}
	
	@Override
	public void markDirty() {
		
	}
	
	public final String TAG_KEY="TAG_KEY";
	
	public NBTTagCompound writeInventoryToTag(){
		NBTTagCompound tag = new NBTTagCompound();
		for(int index = 0; index < this.getSizeInventory();index++){
			if(this.getStackInSlot(index) != null){
				tag.setTag(TAG_KEY+index, this.getStackInSlot(index).writeToNBT(new NBTTagCompound()));
			}
		}
		return tag;
	}
	
	public void setInventoryFromTag(NBTTagCompound nbtTagCompound){
		for(int index = 0; index < this.getSizeInventory();index++){
			this.setInventorySlotContents(index, ItemStack.loadItemStackFromNBT(nbtTagCompound.getCompoundTag(TAG_KEY+index)));
		}
	}
	
	public void onDestroyed(World world, int x, int y,int z){
		for(int index = 0; index < this.getSizeInventory();index++){
			ItemStack stack = this.getStackInSlot(index);
			if(stack != null){
				Util.putItemStackInWorld(world, x, y, z, stack);
			}
		}
	}
	
	@Override
	public String getName() {
		return "";
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
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		
	}

	@Override
	public int getFieldCount() {
		return 0;
	}
	
	@Override
	public void clear() {
		
		
	}
	
	@Override
	public ITextComponent getDisplayName() {
		return null;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		ItemStack stack = inv[index];
		inv[index] = null;
		return stack;
	}
	
	
}
