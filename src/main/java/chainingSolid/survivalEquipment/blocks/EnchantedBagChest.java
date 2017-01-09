package chainingSolid.survivalEquipment.blocks;

import chainingSolid.survivalEquipment.SurvivalEquipment;
import chainingSolid.survivalEquipment.items.EnchantedBag;
import chainingSolid.survivalEquipment.loading.SULoader;
import chainingSolid.survivalEquipment.loading.loadable.LoadableBlock;
import chainingSolid.survivalEquipment.tileEnties.EnchantedBagChestTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;

public class EnchantedBagChest extends BlockContainer implements LoadableBlock {
	
	public final static PropertyBool IS_OPEN = PropertyBool.create("is_open");
	
	public EnchantedBagChest(Material m) {
		super(m);
		this.setHardness(2F);
		this.setResistance(1F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(IS_OPEN, false));
	}
	
	@Override
	public void register(FMLPreInitializationEvent event, SULoader loader) {
		loader.registerBlockWithItemVariant(this);
	}
	
	@Override
	public int getMetaFromState(IBlockState state){
		if(state.getValue(IS_OPEN)){
			return 1;
		}else{
			return 0;
		}
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta){
		if(meta == 1){
			return this.getDefaultState().withProperty(IS_OPEN, true);
		}
		return this.getDefaultState().withProperty(IS_OPEN, false);
	}
	
	@Override
	protected BlockStateContainer createBlockState(){
		return new BlockStateContainer(this, IS_OPEN);
	}
	
	@Override
	public String getIdentifier() {
		return "enchanted_bag_chest";
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state){
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public boolean onBlockActivated(World world,BlockPos pos,IBlockState state, EntityPlayer player,EnumHand hand, ItemStack heldItem, EnumFacing side, float par7, float par8, float par9){
		if(world.isRemote){
			return false;
		}else{
			ItemStack stack = heldItem;
			if(stack != null){
				if(stack.getItem() instanceof EnchantedBag){
					EnchantedBagChestTile tile = ((EnchantedBagChestTile)world.getTileEntity(pos));
					if(tile.addBagToChest(stack.copy())){
						stack.stackSize--;
						return true;
					}
				}
			}
			
			if(!player.isSneaking()){
				FMLNetworkHandler.openGui(player, SurvivalEquipment.instance, SurvivalEquipment.guiHandler.ENCHANTED_BAG_CHEST, world, pos.getX(), pos.getY(), pos.getZ());
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state){
		if(!world.isRemote){
			EnchantedBagChestTile tile = (EnchantedBagChestTile) world.getTileEntity(pos);
			tile.onDestroyed();
		}
		super.breakBlock(world, pos,state);
		
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new EnchantedBagChestTile();
	}
	
	@Override
	public Block getInstance() {
		return this;
	}

	


	
	
}
