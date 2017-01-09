package chainingSolid.survivalEquipment.client.gui;

import chainingSolid.survivalEquipment.blocks.EnchantedEnderDiggerMarker;
import chainingSolid.survivalEquipment.tileEnties.EnchantedEnderDiggerMarkerTile;
import net.minecraft.entity.player.EntityPlayer;

public class ContianerEnchantedEnderDiggerMarker extends SurvivalEquipmentContainer{
	
	private EnchantedEnderDiggerMarkerTile marker;
	
	public ContianerEnchantedEnderDiggerMarker(EntityPlayer player, EnchantedEnderDiggerMarkerTile markerTile) {
		super(player);
		marker = markerTile;
		this.setPlayerInvPosition(0, SLOT_SIZE*7);
		this.buildContianer();
	}
	
	
	@Override
	public void buildContianer(){
		super.buildContianer();
		int index = 0;
		for(int x = 0; index < marker.getSizeInventory();x++){
			for(int y = 0;y < 9 && index < marker.getSizeInventory();y++){
				this.addSlot(marker, index, y*SLOT_SIZE, x*SLOT_SIZE);
				index++;
			}
		}
		this.slotSetMap.put(MAIN_INV, new SlotSet(
				MAIN_INV,
				START_OF_SUBCLASS_SLOTS,
				START_OF_SUBCLASS_SLOTS+marker.getSizeInventory(),
				this.PLAYER_INV_SLOT_SET_NAME
				));
	}
	
	
	
	
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}
	
	
	
	
}
