package chainingSolid.survivalEquipment.items;

import chainingSolid.survivalEquipment.loading.SULoader;
import chainingSolid.survivalEquipment.loading.loadable.LoadableItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.HorseType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class HorseUpgrader extends Item implements LoadableItem {
	
	public static final IAttribute COPY_OF_HORSE_JUMP_ATTRIBUTE = (new RangedAttribute((IAttribute)null, "horse.jumpStrength", 0.7D, 0.0D, 2.0D)).setDescription("Jump Strength").setShouldWatch(true);;
	
	public static final double BEST_HORSE_HEALTH = 30,
			BEST_HORSE_MOVEMENT_SPEED = 0.3375,
			BEST_HORSE_JUMP_HIEGHT = 1.0,
			
			BEST_MULE_AND_DONKEY_SPEED = 0.175,
			BEST_MULE_AND_DONKEY_JUMP_HIEGHT = .5;
	
	public HorseUpgrader() {
		this.setMaxStackSize(1);
		
	}
	
	@Override
	public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer player, EntityLivingBase entity, EnumHand hand){
		if(player.worldObj.isRemote){
			return false;
		}
		if(entity instanceof EntityHorse){
			EntityHorse horse = (EntityHorse) entity;
			if(horse.getType() == HorseType.HORSE || horse.getType() == HorseType.SKELETON || horse.getType() == HorseType.ZOMBIE){
				if(!isPerfectHorse(horse)){
					upgradeHorse(horse);
					itemstack.stackSize--;
					horse.worldObj.spawnParticle(
							EnumParticleTypes.SPELL,
							horse.posX, horse.posY, horse.posZ,
							horse.worldObj.rand.nextDouble()-.5d, horse.worldObj.rand.nextDouble()-.5d, horse.worldObj.rand.nextDouble()-.5d, 0
							);
					return true;
				}else{
					return false;
				}
			}else if(horse.getType() == HorseType.DONKEY || horse.getType() == HorseType.MULE){
				if(!isPerfectMuleOrDonkey(horse)){
					upgradeMuleOrDonkey(horse);
					itemstack.stackSize--;
					horse.worldObj.spawnParticle(
							EnumParticleTypes.SPELL,
							horse.posX, horse.posY, horse.posZ,
							horse.worldObj.rand.nextDouble()-.5d, horse.worldObj.rand.nextDouble()-.5d, horse.worldObj.rand.nextDouble()-.5d, 0
							);
					return true;
				}else{
					return false;
				}
			}
		}
		return false;
	}
	
	
	
	public boolean isPerfectHorse(EntityHorse horse){
		if(
			horse.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getAttributeValue() >= BEST_HORSE_HEALTH
			&& horse.getEntityAttribute(COPY_OF_HORSE_JUMP_ATTRIBUTE).getAttributeValue() >= BEST_HORSE_JUMP_HIEGHT
			&& horse.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue() >= BEST_HORSE_MOVEMENT_SPEED
		){
			return true;
		}else{
			return false;
		}
	}
	
	public void upgradeHorse(EntityHorse horse){
		horse.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(BEST_HORSE_HEALTH);
		horse.getEntityAttribute(COPY_OF_HORSE_JUMP_ATTRIBUTE).setBaseValue(BEST_HORSE_JUMP_HIEGHT);
		horse.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(BEST_HORSE_MOVEMENT_SPEED);
	}
	
	public boolean isPerfectMuleOrDonkey(EntityHorse horse){
		if(
				horse.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getAttributeValue() >= BEST_HORSE_HEALTH
				&& horse.getEntityAttribute(COPY_OF_HORSE_JUMP_ATTRIBUTE).getAttributeValue() >= BEST_MULE_AND_DONKEY_JUMP_HIEGHT
				&& horse.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue() >= BEST_MULE_AND_DONKEY_SPEED
			){
				return true;
			}else{
				return false;
			}
	}
	
	public void upgradeMuleOrDonkey(EntityHorse horse){
		horse.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(BEST_HORSE_HEALTH);
		horse.getEntityAttribute(COPY_OF_HORSE_JUMP_ATTRIBUTE).setBaseValue(BEST_MULE_AND_DONKEY_JUMP_HIEGHT);
		horse.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(BEST_MULE_AND_DONKEY_SPEED);
	}
	
	@Override
	public String getIdentifier() {
		return "horse_upgrader";
	}
	
	@Override
	public void register(FMLPreInitializationEvent event, SULoader loader) {
		loader.registerItem(this);
	}
	
	@Override
	public Item getInstance() {
		return this;
	}
	
	@Override
	public void registerModels(FMLPreInitializationEvent event, SULoader loader) {
		loader.registerItemModelDefualt(this);
	}
	
}
