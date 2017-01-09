package chainingSolid.survivalEquipment.items.shapeBuildingHelper;

import chainingSolid.survivalEquipment.util.Util;
import chainingSolid.survivalEquipment.util.math.Vector4f;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BuildingShapeCircle extends BuildingShape {
	
	public BuildingShapeCircle() {
		super("Circle");
		addTraitToShape(TRAIT_RADIUS, EnumTraitType.FLOAT);
		addTraitToShape(TRAIT_HEIGHT_UP, EnumTraitType.FLOAT);
		addTraitToShape(TRAIT_HEIGHT_DOWN, EnumTraitType.FLOAT);
	}
	
	@Override
	public boolean isBlockPosPartOfShape(BlockPos pos, ItemStack stack, World world) {
		Vector4f center = getTraitVector4f(stack, TRAIT_ORIGIN);
		float heightUp = getTraitFloat(stack, TRAIT_HEIGHT_UP);
		float heightDown = getTraitFloat(stack, TRAIT_HEIGHT_DOWN);
		float radius = getTraitFloat(stack, TRAIT_RADIUS);
		if(center.y + heightUp >= pos.getY() && center.y - heightDown <= pos.getY()){
			float distance = (float) Util.getDistance((int)center.x, (int)0, (int)center.z, pos.getX(), 0, pos.getZ());
			if(distance < radius+.5f && distance > radius -.5f){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String[] getTraits() {
		return new String[]{TRAIT_ORIGIN, TRAIT_RADIUS, TRAIT_HEIGHT_UP, TRAIT_HEIGHT_DOWN};
	}
	
}
