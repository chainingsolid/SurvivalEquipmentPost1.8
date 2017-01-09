package chainingSolid.survivalEquipment.event;

import chainingSolid.survivalEquipment.loading.BlockList;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SurvivalEquipmentEventHandler {
	
	@SubscribeEvent
	public void onEntityGetHurt(LivingHurtEvent e){
		if(e.getSource() == DamageSource.inWall){
			IBlockState state = e.getEntityLiving().worldObj.getBlockState(
					new BlockPos(
							e.getEntityLiving().posX, 
							e.getEntityLiving().posY+e.getEntityLiving().height,
							e.getEntityLiving().posZ
							)
					);
			if(state.getBlock() == BlockList.mobFarmFloor){
				e.setCanceled(true);
			}
			
		}
		
	}
	
	
	
}
