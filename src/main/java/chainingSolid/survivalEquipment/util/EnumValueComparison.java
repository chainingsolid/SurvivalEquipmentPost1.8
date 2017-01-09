package chainingSolid.survivalEquipment.util;

public enum EnumValueComparison {
	
	EQUAL,
	GREATER_THAN,
	LESS_THAN,
	NOT_EQUAL
	;
	
	
	
	public boolean compare(int valueOne, int valueTwo){
		switch(this){
		case EQUAL:
			return valueOne == valueTwo ? true : false;
		case GREATER_THAN:
			return valueOne > valueTwo ? true : false;
		case LESS_THAN:
			return valueOne < valueTwo ? true : false;
		case NOT_EQUAL:
			return valueOne != valueTwo ? true : false;
		}
		return false;
	}
	
	
}
