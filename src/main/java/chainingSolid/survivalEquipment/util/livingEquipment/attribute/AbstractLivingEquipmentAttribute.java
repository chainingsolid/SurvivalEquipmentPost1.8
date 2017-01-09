package chainingSolid.survivalEquipment.util.livingEquipment.attribute;

public abstract class AbstractLivingEquipmentAttribute {
	
	public final String ID;
	
	public final static String 
			MINING_SPEED="MiningSpeed",
			DURABLITY="Durablity",
			DAMADGE="Damadge"
			;
	
	public AbstractLivingEquipmentAttribute(String attributeId){
		ID = attributeId;
	}
	
}
