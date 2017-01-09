package chainingSolid.survivalEquipment.blocks;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;

import chainingSolid.survivalEquipment.loading.SULoader;
import chainingSolid.survivalEquipment.loading.loadable.LoadableBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.BlockStateContainer.StateImplementation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MobFarmFloor extends Block implements LoadableBlock{
	
	public static  final AxisAlignedBB noHitBox = new AxisAlignedBB(0,0,0,0,0,0);
	
	public MobFarmFloor(Material par2Material) {
		super(par2Material);
		this.setHardness(1);
		
	}
	
	@Override
	public void register(FMLPreInitializationEvent event, SULoader loader) {
		loader.registerBlockWithItemVariant(this);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced){
		tooltip.add("All non player entities fall thru this block, and mobs can spawn on it");
	}
	
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer(){
		return BlockRenderLayer.CUTOUT;
	}
	
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos){
		if(worldIn.getClosestPlayer(pos.getX()+.5d, pos.getY()+.5D, pos.getZ()+.5D, 2D, false) != null){
			return this.FULL_BLOCK_AABB;
		}else{
			return noHitBox;
		}
	}
	
	@Override
	public String getIdentifier() {
		return "mob_farm_floor";
	}
	
	@Override
	public Block getInstance() {
		return this;
	}
	
}
