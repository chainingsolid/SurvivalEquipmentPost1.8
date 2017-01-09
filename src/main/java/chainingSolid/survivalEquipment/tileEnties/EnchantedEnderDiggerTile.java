package chainingSolid.survivalEquipment.tileEnties;

import java.util.ArrayList;
import java.util.List;

import chainingSolid.survivalEquipment.blocks.EnchantedEnderDiggerMarker;
import chainingSolid.survivalEquipment.blocks.PlainEnchantedEnderDiggerMarker;
import chainingSolid.survivalEquipment.loading.BlockList;
import chainingSolid.survivalEquipment.util.GenericInventory;
import chainingSolid.survivalEquipment.util.Util;
import chainingSolid.survivalEquipment.util.entity.UtilFakePlayer;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.WorldServer;

public class EnchantedEnderDiggerTile extends TileEntity implements IInventory,ITickable{
	
	private GenericInventory inv = new GenericInventory(24);
	
	private int negativeX,negativeY,negativeZ,positiveX,positiveY,positiveZ;
	private int miningOffestX = 0,miningOffsetY = 0,miningOffestZ = 0;
	private BlockPos miningPos = new BlockPos(0,0,0);
	
	private int AxisLenghtMaxDistance = 128;
	
	private boolean isMiningABlock = false,
			isFirstMineCycle = false;
	
	private ItemStack currentTool;
	
	private List<EnchantedEnderDiggerMarkerTile> markerBlocks = new ArrayList<EnchantedEnderDiggerMarkerTile>();
	
	private int miningOffsetX;
	private int miningOffsetZ;
	
	@Override
	public void update(){
		if(worldObj.isRemote){
			return;
		}
		
		runMineCycle();
	}
	
	private void updateArea(){
		markerBlocks.clear();
		positiveX = getEndOfAxis(EnumFacing.EAST);
		negativeX = -getEndOfAxis(EnumFacing.WEST);
		negativeZ = -getEndOfAxis(EnumFacing.NORTH);
		positiveZ = getEndOfAxis(EnumFacing.SOUTH);
		positiveY = getEndOfAxis(EnumFacing.UP);
		negativeY = -getEndOfAxis(EnumFacing.DOWN);
		
		
	}
	
	public int getEndOfAxis(EnumFacing direction){
		
		int distance = 1;
		int x , y ,z ;
		
		A: while(true){
			x = pos.getX() + distance*direction.getFrontOffsetX();
			y = pos.getY() + distance*direction.getFrontOffsetY();
			z = pos.getZ() + distance*direction.getFrontOffsetZ();
			if(Util.isCordInWorldBounds( x , y , z )){
				Block block = worldObj.getBlockState(pos).getBlock();
				if(this.isAMarkerBlock(block)){
					this.addMarkerBlockToList(block,x,y,z);
					if(distance < AxisLenghtMaxDistance){
						distance++;
					}else{
						break A;
					}
				}else{
					break A;
				}
			}
		}
		
		return distance-1;
	}
	
	public boolean isAMarkerBlock(Block block){
		if(block instanceof EnchantedEnderDiggerMarker || block instanceof PlainEnchantedEnderDiggerMarker){
			return true;
		}else{
			return false;
		}
		
	}
	
	public void addMarkerBlockToList(Block marker,int x,int y,int z){
		if(marker instanceof EnchantedEnderDiggerMarker){
			EnchantedEnderDiggerMarkerTile tile = (EnchantedEnderDiggerMarkerTile) worldObj.getTileEntity(pos);
			markerBlocks.add(tile);
		}
	}
	
	private void runMineCycle(){
		
		if(isMiningABlock){
			continueMining();
		}else{
			runIncrement();
			miningPos = new BlockPos(pos.getX() + miningOffsetX, pos.getY() + miningOffsetY, pos.getZ() + miningOffsetZ);
			startMining();
		}
	}
	
	private void runIncrement(){
		updateArea();
		if(incrementMiningOffsetY()){
			if(incrementMiningOffsetX()){
				incrementMiningOffsetZ();
			}
		}
	}
	
	private boolean incrementMiningOffsetY(){
		miningOffsetY--;
		boolean isOutOfBounds = Util.isIntInBounds(miningOffsetY, positiveY, negativeY);
		if(isOutOfBounds){
			miningOffsetY = positiveY;
		}
		return isOutOfBounds;
	}
	
	private boolean incrementMiningOffsetX(){
		miningOffsetX--;
		boolean isOutOfBounds = Util.isIntInBounds(miningOffsetX, positiveX, negativeX);
		if(isOutOfBounds){
			miningOffsetX = positiveX;
		}
		return isOutOfBounds;
	}
	
	private boolean incrementMiningOffsetZ(){
		miningOffsetZ--;
		boolean isOutOfBounds = Util.isIntInBounds(miningOffsetZ, positiveZ, negativeZ);
		if(isOutOfBounds){
			miningOffsetZ = positiveZ;
		}
		return isOutOfBounds;
	}
	
	private void startMining(){
		
		Block block = worldObj.getBlockState(miningPos).getBlock();
		if(block.isAir(worldObj.getBlockState(miningPos), worldObj, new BlockPos(miningPos))){
			return;
		}
		
		ItemStack tool = getStackToMineBlock(block,worldObj.getBlockState(miningPos));
		
		if(tool != null){
			isMiningABlock = true;
			isFirstMineCycle = true;
		}else{
			isMiningABlock = false;
		}
		
		currentTool = tool;
		
	}
	
	private void continueMining(){
		if(!(worldObj instanceof WorldServer)){
			return;
		}
		
		BlockPos miningPos = new BlockPos(pos.getX() + miningOffsetX, pos.getY() + miningOffsetY, pos.getZ() + miningOffsetZ);
		
		UtilFakePlayer player = UtilFakePlayer.getFakePlayer("EED"+pos.toString(), (WorldServer)worldObj);
		player.setHeldItem(EnumHand.MAIN_HAND, currentTool);
		
		if(this.isFirstMineCycle){
			player.interactionManager.onBlockClicked(miningPos, EnumFacing.UP);
			
			player.interactionManager.setBlockReachDistance(.5);
		}
		
		player.interactionManager.updateBlockRemoving();
		player.interactionManager.tryHarvestBlock(miningPos);
		
		Block block = worldObj.getBlockState(miningPos).getBlock();
		
		if(block.isAir(worldObj.getBlockState(miningPos), worldObj, miningPos) || isAMarkerBlock(block) /*|| block == BlockList.enchatntedEnderDigger*/){
			isMiningABlock = false;
			
			List items = worldObj.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(
					miningPos.getX()-1, miningPos.getY()-1, miningPos.getZ()-1,
					miningPos.getX()+1, miningPos.getY()+1, miningPos.getZ()+1));
			for(Object o : items){
				EntityItem item = (EntityItem)o;
				ItemStack stack = item.getEntityItem();
				
				stack = Util.mergeItemStackIntoInventory(stack, this);
				
				if(stack == null){
					item.setDead();
					item.setEntityItemStack(null);
				}
				
			}
			
		}
		
	}
	
	private ItemStack getStackToMineBlock(Block block , IBlockState state){
		ItemStack blockAsStack = new ItemStack(block);
		A: for(EnchantedEnderDiggerMarkerTile marker: markerBlocks){
			if(marker == null){
				continue A;
			}
			B: for(int slot = 0; marker.getSizeInventory() > slot;slot++){
				ItemStack stack = marker.getStackInSlot(slot);
				if(stack == null || blockAsStack == null){
					continue B;
				}
				if(stack.getItem() == blockAsStack.getItem()){
					ItemStack tool = getToolFromMarker(marker);
					if(tool != null){
						return tool;
					}
				}
			}
		}
		return null;
	}
	
	private ItemStack getToolFromMarker(EnchantedEnderDiggerMarkerTile marker){
		for(int index = 0;index < marker.getSizeInventory();index++){
			ItemStack stack = marker.getStackInSlot(index);
			if(stack == null){
				continue;
			}
			if(stack.getItem() instanceof ItemTool){
				return stack;
			}
		}
		return null;
	}
	
	@Override
	public void onChunkUnload(){
		UtilFakePlayer.killFakePlayer("EED"+pos.toString());
		System.out.println("killed fake player");
	}
	
	@Override
	public int getSizeInventory() {
		return inv.getSizeInventory();
	}
	
	@Override
	public ItemStack getStackInSlot(int slot) {
		return inv.getStackInSlot(slot);
	}
	
	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		return inv.decrStackSize(slot, amount);
	}
	
	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inv.setInventorySlotContents(slot, stack);
	}
	
	@Override
	public int getInventoryStackLimit() {
		return inv.getInventoryStackLimit();
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return false;
	}
	
	@Override
	public void openInventory(EntityPlayer player) {}
	
	@Override
	public void closeInventory(EntityPlayer player) {}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return inv.isItemValidForSlot(slot, stack);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		tag.setTag("inv", inv.writeInventoryToTag());
		tag.setInteger("negativeX", negativeX);
		tag.setInteger("negativeY", negativeY);
		tag.setInteger("negativeZ", negativeZ);
		tag.setInteger("positiveX", positiveX);
		tag.setInteger("positiveY", positiveY);
		tag.setInteger("positiveZ", positiveZ);
		
		tag.setInteger("miningOffsetX", miningOffsetX);
		tag.setInteger("miningOffsetY", miningOffsetY);
		tag.setInteger("miningOffsetZ", miningOffsetZ);
		return tag;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		inv.setInventoryFromTag(tag.getCompoundTag("inv"));
		
		negativeX = tag.getInteger("negativeX");
		negativeY = tag.getInteger("negativeY");
		negativeZ = tag.getInteger("negativeZ");
		positiveX = tag.getInteger("positiveX");
		positiveY = tag.getInteger("positiveY");
		positiveZ = tag.getInteger("positiveZ");
		
		miningOffsetX = tag.getInteger("miningOffsetX");
		miningOffsetY = tag.getInteger("miningOffsetY");
		miningOffsetZ = tag.getInteger("miningOffsetZ");
		
	}
	
	public void onDestroyed() {
		inv.onDestroyed(worldObj, pos.getX(),pos.getY(),pos.getZ());
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
	public int getField(int id) {
		return inv.getField(id);
	}
	
	@Override
	public void setField(int id, int value) {
		inv.setField(id, value);
	}
	
	@Override
	public int getFieldCount() {
		return inv.getFieldCount();
	}
	
	@Override
	public void clear() {
		inv.clear();
		
	}
	
	@Override
	public ITextComponent getDisplayName() {
		return inv.getDisplayName();
	}
	
	@Override
	public ItemStack removeStackFromSlot(int index) {
		return inv.removeStackFromSlot(index);
	}
	
	
	
}
