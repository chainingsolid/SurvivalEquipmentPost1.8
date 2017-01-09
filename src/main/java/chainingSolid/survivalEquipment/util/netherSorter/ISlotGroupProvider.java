package chainingSolid.survivalEquipment.util.netherSorter;

import java.util.List;


public interface ISlotGroupProvider {
	
	public List<ISlotGroupProvider> getAllKnowProviders();
	
	public List<SlotGroup> getThisProvidersSlotGroups();
	
	public String getUniqueId();
	
	public void onLinkCreated(SlotGroupProviderLink newLink);
	
	public void onLinkBroken(SlotGroupProviderLink link);
	
	public int getRange();
	
}
