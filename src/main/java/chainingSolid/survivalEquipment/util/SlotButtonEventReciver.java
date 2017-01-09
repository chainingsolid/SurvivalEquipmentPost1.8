package chainingSolid.survivalEquipment.util;

import net.minecraft.entity.player.EntityPlayer;

public interface SlotButtonEventReciver {
	
	public void onSlotButtonClicked(EntityPlayer player,SlotButton button,String returnMessage);
	
}
