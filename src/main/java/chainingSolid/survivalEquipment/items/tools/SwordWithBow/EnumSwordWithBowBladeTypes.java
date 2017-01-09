package chainingSolid.survivalEquipment.items.tools.SwordWithBow;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;

public enum EnumSwordWithBowBladeTypes {
	
	Wood(ToolMaterial.WOOD,"Wood",Block.getBlockFromName("planks")),
			
	Stone(ToolMaterial.STONE,"Stone",Block.getBlockFromName("stone")),
			
	Iron(ToolMaterial.IRON,"Iron",Items.IRON_INGOT),
			
	Gold(ToolMaterial.GOLD,"Gold",Items.GOLD_INGOT),
			
	Diamond(ToolMaterial.DIAMOND,"Diamond",Items.DIAMOND)
	;
	
	private ToolMaterial material;
	private String textureName;
	private Object craftingMaterial;
	
	private EnumSwordWithBowBladeTypes(ToolMaterial material,String textureName,Object craftingMaterial){
		this.material = material;
		this.textureName = textureName;
		this.craftingMaterial = craftingMaterial;
	}
	
	public ToolMaterial getMaterial(){
		return this.material;
	}
	
	public String getTextureName(){
		return this.textureName;
	}
	
	public Object getCraftingMaterial(){
		return craftingMaterial;
	}
}
