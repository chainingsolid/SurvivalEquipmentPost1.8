package chainingSolid.survivalEquipment.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import chainingSolid.survivalEquipment.tileEnties.EnchantedEnderDiggerMarkerTile;
import chainingSolid.survivalEquipment.tileEnties.EnchantedEnderDiggerTile;
import chainingSolid.survivalEquipment.util.GenericInventory;
import chainingSolid.survivalEquipment.util.SlotThatTakesInToAccountIsItemVaildForSlot;
import chainingSolid.survivalEquipment.util.enchantedEnderDigger.EnchantedEnderDiggerWhiteListRow;


public class ContainerEnchantedEnderDigger extends SurvivalEquipmentContainer {
	
	protected int xSize = 256;
	protected int ySize = 240;
	
	protected final int SLOT_SIZE = 20;
	
	EnchantedEnderDiggerTile diggerTile;
	
	EntityPlayer player;
	
	public ContainerEnchantedEnderDigger(EnchantedEnderDiggerTile diggerTile, EntityPlayer player){
		super(player);
		this.diggerTile = diggerTile;
		this.player = player;
		this.setPlayerInvPosition(0, SLOT_SIZE*7);
		this.buildContianer();
	}
	
	@Override
	public void buildContianer(){
		super.buildContianer();
		int index = 0;
		A: for(int x = 0 ; x < 9;x++){
			for(int y = 0; y < 3;y++){
				this.addSlot(diggerTile, index, SLOT_SIZE*x, SLOT_SIZE*y);
				index++;
				if(index >= diggerTile.getSizeInventory()){
					break A;
				}
			}
		}
		this.slotSetMap.put(MAIN_INV, new SlotSet(
				MAIN_INV,
				START_OF_SUBCLASS_SLOTS,
				START_OF_SUBCLASS_SLOTS+diggerTile.getSizeInventory(),
				this.PLAYER_INV_SLOT_SET_NAME
				));
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}
	
}
