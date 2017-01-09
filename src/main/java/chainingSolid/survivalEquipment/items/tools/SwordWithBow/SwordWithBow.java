package chainingSolid.survivalEquipment.items.tools.SwordWithBow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import chainingSolid.survivalEquipment.SurvivalEquipment;
import chainingSolid.survivalEquipment.loading.SULoader;
import chainingSolid.survivalEquipment.loading.loadable.LoadableItem;

public class SwordWithBow extends ItemBow implements LoadableItem{
	
	public final EnumSwordWithBowBladeTypes BLADE_TYPE;
	
	public SwordWithBow(EnumSwordWithBowBladeTypes bladeType,String texture) {
		super();
		
		BLADE_TYPE = bladeType;
		this.bFull3D = true;
	}
	
	@Override
	public void register(FMLPreInitializationEvent event, SULoader loader) {
		
		
	}
	
	@Override
	public int getItemEnchantability(){
		return BLADE_TYPE.getMaterial().getEnchantability();
	}
	
	@Override
	public int getMaxDamage(ItemStack stack){
		return this.BLADE_TYPE.getMaterial().getMaxUses();
	}
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase e1, EntityLivingBase e2){
		stack.damageItem(1, e1);
		return false;
	}
	
	@Override
	public String getIdentifier() {
		return "Sword-With-Bow"+BLADE_TYPE.getTextureName();
	}
	
	@Override
	public String getUnlocalizedName(){
		return this.getIdentifier();
	}
	
	@Override
	public Item getInstance() {
		return this;
	}
	
	@Override
	public void registerModels(FMLPreInitializationEvent event, SULoader loader) {
		
		
	}
	
	
}
