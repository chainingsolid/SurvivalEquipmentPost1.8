package chainingSolid.survivalEquipment.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import chainingSolid.survivalEquipment.loading.SULoader;
import chainingSolid.survivalEquipment.loading.loadable.LoadableBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ReplaceableBlock extends Block implements LoadableBlock {
	
	public ReplaceableBlock(Material materialIn) {
		super(materialIn);
		this.setHardness(0);
		this.setLightOpacity(0);
	}
	
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos){
		return NULL_AABB;
	}
	
	@Override
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos){
		return true;
	}
	
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    
    public int quantityDropped(Random random)
    {
        return 0;
    }
	
	@Override
	public String getIdentifier() {
		return "replaceable_block";
	}
	
	@Override
	public void register(FMLPreInitializationEvent event, SULoader loader) {
		loader.registerBlockWithItemVariant(this);
	}
	
	@Override
	public Block getInstance() {
		return this;
	}
	
}
