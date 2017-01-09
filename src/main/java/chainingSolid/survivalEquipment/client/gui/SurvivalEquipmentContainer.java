package chainingSolid.survivalEquipment.client.gui;

import java.util.HashMap;

import chainingSolid.survivalEquipment.util.ArmorSlot;
import chainingSolid.survivalEquipment.util.SlotButton;
import chainingSolid.survivalEquipment.util.SlotButtonEventReciver;
import chainingSolid.survivalEquipment.util.SlotThatTakesInToAccountIsItemVaildForSlot;
import chainingSolid.survivalEquipment.util.Util;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.math.BlockPos;

public abstract class SurvivalEquipmentContainer extends Container implements SlotButtonEventReciver{
	
	public int playerInvX,playerInvY;
	public boolean shouldDrawPlayerInv = false;
	public EntityPlayer player;
	
	public static final String PLAYER_INV_SLOT_SET_NAME = "PlayerInv",MAIN_INV = "MainInv";
	
	public HashMap<String,SlotSet> slotSetMap = new HashMap<String,SlotSet>();
	
	public static final int SLOT_SIZE = 20,CRAFTING_GRID_SIZE = 5,PLAYER_INV_SIZE = (4*9)+4+1,START_OF_SUBCLASS_SLOTS = PLAYER_INV_SIZE + CRAFTING_GRID_SIZE;
	
	public InventoryCrafting craftMatrix = new InventoryCrafting(this, 2, 2);
	public InventoryCraftResult craftResult = new InventoryCraftResult();
	
	public boolean isClient = false;
	
	public boolean mustPlayerBeInRadius = false;
	public int radiusPlayerMustBeInForGuiToBeOpen = 5;
	public BlockPos radiusCenter = new BlockPos(0,0,0);
	
	//TODO add utility networking code for guis 
	
	public SurvivalEquipmentContainer(EntityPlayer player) {
		this.player = player;
		
	}
	
	public void buildContianer(){
		this.inventorySlots.clear();
		this.inventoryItemStacks.clear();
		this.slotSetMap.clear();
		if(shouldDrawPlayerInv){
			this.slotSetMap.put(PLAYER_INV_SLOT_SET_NAME, new SlotSet(PLAYER_INV_SLOT_SET_NAME, 0, START_OF_SUBCLASS_SLOTS-1, MAIN_INV));
			this.addPlayerSlots();
		}
		
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player){
		if(!mustPlayerBeInRadius){
			return true;
		}else if(Util.getDistance(radiusCenter.getX(), radiusCenter.getY(), radiusCenter.getZ(), (int)player.posX, (int)player.posY, (int)player.posZ) <= radiusPlayerMustBeInForGuiToBeOpen){
			return true;
		}else{
			return false;
		}
	}
	
	
	public void setPlayerInvPosition(int x,int y){
		this.playerInvX = x;
		this.playerInvY = y;
		this.shouldDrawPlayerInv = true;
	}
	
	private void addPlayerSlots(){
		InventoryPlayer inv = player.inventory;
		int index = 0;
		for(int x = 0;x < 9;x++){
			this.addSlot(inv, index, x*SLOT_SIZE + playerInvX, 3*SLOT_SIZE + playerInvY);
			index++;
		}
		for(int y = 0; y < 3;y++){
			for(int x = 0 ;x < 9; x++){
				this.addSlot(inv, index, x*SLOT_SIZE + playerInvX, y*SLOT_SIZE + playerInvY);
				index++;
			}
		}
		for(int y = 4; y > 0;y--){
			this.addSlot(inv, index, SLOT_SIZE*9 + playerInvX, (y-1)*SLOT_SIZE + playerInvY);
			index++;
		}
		//Off hand
		this.addSlot(inv, 40, (SLOT_SIZE*-1)+playerInvX-5, (SLOT_SIZE*0)+playerInvY);
		for(int x = 0;x < 2;x++){
			for(int y = 0;y < 2;y++){
				this.addSlot(craftMatrix, x+(y*2), (x+10)*SLOT_SIZE + playerInvX, y*SLOT_SIZE + playerInvY);
			}
		}
		this.addSlotToContainer(new SlotCrafting(player, craftMatrix,craftResult, 0, ((int)(10.5*SLOT_SIZE)) + playerInvX, 2*SLOT_SIZE + playerInvY));
		
		this.onCraftMatrixChanged(this.craftMatrix);
	}
	
	@Override
	public void onCraftMatrixChanged(IInventory inv){
		this.craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, player.worldObj));
	}
	
	@Override
	public void onContainerClosed(EntityPlayer player){
		super.onContainerClosed(player);
		if(!player.worldObj.isRemote){
			for(int i = 0;i < craftMatrix.getSizeInventory();i++){
				ItemStack stack = craftMatrix.getStackInSlot(i);
				if(stack != null){
					EntityItem dropedItems = new EntityItem(player.worldObj, player.posX, player.posY, player.posZ, stack);
					player.worldObj.spawnEntityInWorld(dropedItems);
				}
			}
			craftResult.setInventorySlotContents(0, (ItemStack)null);
		}
	}
	
	public void addSlot(IInventory inv,int indexInInv,int x,int y){
		
		if(inv instanceof InventoryPlayer && (indexInInv == 36 || indexInInv == 37 || indexInInv == 38 || indexInInv == 39)){
			this.addSlotToContainer(new ArmorSlot(inv, indexInInv, x, y,indexInInv-36,player));
		}else{
			this.addSlotToContainer(new SlotThatTakesInToAccountIsItemVaildForSlot(inv, indexInInv, x, y));
		}
	}
	
	public void addSlotButton(IInventory inv,int index,int x,int y,SlotButtonEventReciver reciver,String message){
		this.addSlotToContainer(new SlotButton(inv,index,x,y,reciver,message));
	}
	
	@Override
	public void onSlotButtonClicked(EntityPlayer player, SlotButton button, String returnMessage) {
		
		
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot){
		ItemStack leftOver = null;
		Slot slotClicked = this.getSlot(slot);
		
		if(slotClicked != null && slotClicked.getHasStack()){
			ItemStack stackInSlot = slotClicked.getStack();
			leftOver = stackInSlot.copy();
			
			A: for(SlotSet slotSet : this.slotSetMap.values()){
				if(slotSet.containesIndex(slot)){
					SlotSet setToShiftClickTo = slotSetMap.get(slotSet.SLOT_SET_TO_SHIFT_CLICK_TO);
					if(setToShiftClickTo != null){
						if(!setToShiftClickTo.mergeItemStackInToThisSlotSet(stackInSlot, this)){
							if(!this.mergeItemStack(stackInSlot, setToShiftClickTo.MIN_SLOT_INDEX, setToShiftClickTo.MAX_SLOT_INDEX, false)){
								return null;
							}
						break A;
					}
				}
			}
			
			if(stackInSlot.stackSize == 0){
				slotClicked.putStack(null);
			}else{
				slotClicked.onSlotChanged();
			}
			
			slotClicked.onPickupFromSlot(player, stackInSlot);
			
			}
		
		
		}
		return leftOver;
	}
	
}
