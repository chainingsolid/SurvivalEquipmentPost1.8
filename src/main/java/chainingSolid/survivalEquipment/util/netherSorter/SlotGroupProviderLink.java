package chainingSolid.survivalEquipment.util.netherSorter;

public class SlotGroupProviderLink {
	
	public ISlotGroupProvider first, second;
	
	public SlotGroupProviderLink(ISlotGroupProvider firstProvider, ISlotGroupProvider secondProvider){
		first = firstProvider;
		second = secondProvider;
		
	}
	
	public void makeLink(){
		first.onLinkCreated(this);
		second.onLinkCreated(this);
		System.out.println("made link beteween "+first.getUniqueId() +"  "+second.getUniqueId());
	}
	
	public void breakLink(){
		first.onLinkBroken(this);
		second.onLinkBroken(this);
		System.out.println("destroied link beteween "+first.getUniqueId() +"  "+second.getUniqueId());
	}
	
	
}
