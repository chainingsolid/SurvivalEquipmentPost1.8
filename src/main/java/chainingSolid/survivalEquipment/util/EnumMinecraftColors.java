package chainingSolid.survivalEquipment.util;

public enum EnumMinecraftColors {
	
	WHITE("White",0),
	ORANGE("Orange",1),
	MAGENTA("Magenta",2),
	LIGHT_BLUE("Light Blue",3),
	YELLOW("Yellow",4),
	LIME("Lime",5),
	PINK("Pink",6),
	GRAY("Gray",7),
	LIGHT_GRAY("Light Gray",8),
	CYAN("Cyan",9),
	PURPLE("Purple",10),
	BLUE("Blue",11),
	BROWN("Brown",12),
	GREEN("Green",13),
	RED("Red",14),
	BLACK("Black",15)
	;
	
	public final String NAME;
	
	public final int META;
	
	private EnumMinecraftColors(String name,int meta){
		this.NAME = name;
		this.META = meta;
	}
}
