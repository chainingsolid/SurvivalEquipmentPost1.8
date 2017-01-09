package chainingSolid.survivalEquipment.tileEnties;

import chainingSolid.survivalEquipment.SurvivalEquipment;
import chainingSolid.survivalEquipment.blocks.EnchantedBagChest;
import chainingSolid.survivalEquipment.items.EnchantedBag;
import chainingSolid.survivalEquipment.loading.BlockList;
import chainingSolid.survivalEquipment.network.EventData.EnchantedBagChestData;
import chainingSolid.survivalEquipment.util.GenericInventory;
import chainingSolid.survivalEquipment.util.Util;
import chainingSolid.survivalEquipment.util.enchantedBag.EnchantedBagInventory;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;


public class EnchantedBagChestTile extends TileEntity implements IInventory, ITickable{
	
	private boolean hasSetup = false;
	
	private int bagCount = 0;
	
	private int playersViewingCount = 0;
	
	public EnchantedBagInventory[] bagInvs;
	
	public EnchantedBagInventory[] nonNullBagInvMapps;
	
	public int EnchantedBagInvSize = EnchantedBagInventory.BAG_INV_HIEGHT * EnchantedBagInventory.BAG_INV_WIDTH;
	
	public GenericInventory bagsInChest = new GenericInventory(9){
		@Override
		public boolean isItemValidForSlot(int slot, ItemStack stack){
			if(stack != null){
				if(stack.getItem() instanceof EnchantedBag){
					return true;
				}
			}
			return false;
		}
		
	};
	
	public EnchantedBagChestTile(){
		bagInvs = new EnchantedBagInventory[9];
	}
	
	@Override
	public void update(){
		if(!hasSetup){
			setup();
			hasSetup = true;
		}
		if(worldObj.isRemote){
			return;
		}
		
	}
	
	private void setup(){
		for(int index = 0; index < bagsInChest.getSizeInventory();index++){
			ItemStack stack = bagsInChest.getStackInSlot(index);
			bagInvs[index] = new EnchantedBagInventory(stack);
		}
		this.refreshBagCount();
	}
	
	public boolean addBagToChest(ItemStack bag){
		System.out.println("adding bag to chest world.isRemote="+worldObj.isRemote);
		for(int index = 0; index < bagsInChest.getSizeInventory();index++){
			ItemStack stack = bagsInChest.getStackInSlot(index);
			if(stack == null){
				bagsInChest.setInventorySlotContents(index, bag);
				this.setup();
				return true;
			}
		}
		return false;
	}
	
	public boolean removeBag(int slot, EntityPlayer player){
		ItemStack bag = bagsInChest.getStackInSlot(slot);
		if(bag != null){
			bagInvs[slot].saveBagToStack(bag);
			bagInvs[slot].setThisBagNoLongerInUse(bag);
			Util.putItemStackInWorld(worldObj, player.posX,player.posY+.5,player.posZ, bag);
			this.bagsInChest.setInventorySlotContents(slot, null);
			setup();
			return true;
		}
		return false;
	}
	
	public void refreshBagCount(){
		bagCount = 0;
		for(int i = 0;i < bagsInChest.getSizeInventory();i++){
			if(bagsInChest.getStackInSlot(i) != null){
				bagCount++;
			}
		}
		nonNullBagInvMapps = new EnchantedBagInventory[bagCount];
		int index = 0;
		for(int i = 0;i < bagsInChest.getSizeInventory();i++){
			if(bagsInChest.getStackInSlot(i) != null){
				nonNullBagInvMapps[index] = bagInvs[i];
				index++;
			}
		}
	}
	
	public void onDestroyed(){
		for(int i = 0; i < bagsInChest.getSizeInventory(); i++){
			ItemStack stack = bagsInChest.getStackInSlot(i);
			if(stack != null){
				Util.putItemStackInWorld(worldObj, pos.getX(),pos.getY(),pos.getZ(), stack);
			}
			
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		tag.setTag("inv", bagsInChest.writeInventoryToTag());
		for(int i = 0; i < bagInvs.length;i++){
			if(bagInvs[i] != null){
				tag.setTag(""+i, bagInvs[i].writeInventoryToTag());
			}
		}
		return tag;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		bagsInChest.setInventoryFromTag(tag.getCompoundTag("inv"));
		for(int i = 0; i < bagInvs.length;i++){
			if(tag.getTag(""+i) != null){
				bagInvs[i] = new EnchantedBagInventory(this.bagsInChest.getStackInSlot(i));
				bagInvs[i].setInventoryFromTag(tag.getCompoundTag(""+i));
			}
		}
		
	}
	
	public void sendChestToPlayer(EntityPlayer p){
		EnchantedBagChestData dataForClient = new EnchantedBagChestData();
		dataForClient.dimId = worldObj.provider.getDimension();
		dataForClient.xCoord = pos.getX();
		dataForClient.yCoord = pos.getY();
		dataForClient.zCoord = pos.getZ();
		dataForClient.chestTag = new NBTTagCompound();
		this.writeToNBT(dataForClient.chestTag);
		NetworkRegistry.TargetPoint target = new NetworkRegistry.TargetPoint(dataForClient.dimId, pos.getX(), pos.getY(), pos.getZ(), 10D);
		
		FMLProxyPacket pck = new FMLProxyPacket(dataForClient.writeDataToArray(),SurvivalEquipment.CHANNEL_NAME);
		SurvivalEquipment.channel.sendToAllAround(pck, target);
	}
	
	@Override
	public int getSizeInventory() {
		if(!this.hasSetup){
			return 0;
		}else{
			return nonNullBagInvMapps.length * EnchantedBagInvSize;
		}
	}
	
	@Override
	public ItemStack getStackInSlot(int slot) {
		int invOffset = slot / EnchantedBagInvSize;
		int slotInInv = slot - invOffset * EnchantedBagInvSize;
		return nonNullBagInvMapps[invOffset].getStackInSlot(slotInInv);
	}
	
	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		int invOffset = slot / EnchantedBagInvSize;
		int slotInInv = slot - invOffset * EnchantedBagInvSize;
		return nonNullBagInvMapps[invOffset].decrStackSize(slotInInv, amount);
	}
	
	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		int invOffset = slot / EnchantedBagInvSize;
		int slotInInv = slot - invOffset * EnchantedBagInvSize;
		nonNullBagInvMapps[invOffset].setInventorySlotContents(slotInInv, stack);
		
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) {
		return true;
	}
	
	@Override
	public void openInventory(EntityPlayer player) {
		this.playersViewingCount++;
		this.updateBlockState();
	}
	
	@Override
	public void closeInventory(EntityPlayer player) {
		this.playersViewingCount--;
		this.updateBlockState();
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		int invOffset = slot / EnchantedBagInvSize;
		int slotInInv = slot - invOffset * EnchantedBagInvSize;
		return nonNullBagInvMapps[invOffset].isItemValidForSlot(slotInInv, stack);
	}
	
	@Override
	public String getName() {
		return "EnchantedBagChestTile";
	}
	
	@Override
	public boolean hasCustomName() {
		return false;
	}
	
	@Override
	public ItemStack removeStackFromSlot(int index) {
		int invOffset = index / EnchantedBagInvSize;
		int slotInInv = index - invOffset * EnchantedBagInvSize;
		return nonNullBagInvMapps[invOffset].removeStackFromSlot(slotInInv);
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
	
	public void updateBlockState(){
		if(playersViewingCount > 0){
			worldObj.setBlockState(getPos(), worldObj.getBlockState(getPos()).withProperty(EnchantedBagChest.IS_OPEN, true),2);
		}else{
			worldObj.setBlockState(getPos(), worldObj.getBlockState(getPos()).withProperty(EnchantedBagChest.IS_OPEN, false),2);
		}
		
	}
	
	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate){
		return false;
	}
	
}
