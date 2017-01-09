package chainingSolid.survivalEquipment.items.shapeBuildingHelper;

import chainingSolid.survivalEquipment.util.Util;
import chainingSolid.survivalEquipment.util.math.Vector4f;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BuildingShapeDome extends BuildingShape {
	
	
	
	public BuildingShapeDome() {
		super("Dome");
		this.addTraitToShape(TRAIT_RADIUS, EnumTraitType.FLOAT);
		this.addTraitToShape(TRAIT_THICKNESS, EnumTraitType.FLOAT);
	}
	
	@Override
	public boolean isBlockPosPartOfShape(BlockPos pos, ItemStack stack, World world) {
		Vector4f center = getTraitVector4f(stack, TRAIT_ORIGIN);
		float radius = getTraitFloat(stack, TRAIT_RADIUS);
		float half_thickness = getTraitFloat(stack, TRAIT_THICKNESS)/2;
		float distance = (float) Util.getDistance((int)center.x, (int)center.y, (int)center.z, pos.getX(), pos.getY(), pos.getZ());
		if(distance < radius+half_thickness && distance > radius-half_thickness){
			return true;
		}
		return false;
	}
	
	@Override
	public String[] getTraits() {
		return new String[]{TRAIT_ORIGIN, TRAIT_RADIUS, TRAIT_THICKNESS};
	}
	
}
