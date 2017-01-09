package chainingSolid.survivalEquipment.blocks;

import chainingSolid.survivalEquipment.SurvivalEquipment;
import chainingSolid.survivalEquipment.loading.SULoader;
import chainingSolid.survivalEquipment.loading.loadable.LoadableBlock;
import chainingSolid.survivalEquipment.tileEnties.EnchantedEnderDiggerTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
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

public class EnchantedEnderDigger extends BlockContainer implements LoadableBlock{
	
	public EnchantedEnderDigger(Material par2Material) {
		super(par2Material);
		this.blockResistance=500;
		this.setHardness(4);
		this.setLightLevel(0F);
	}
	
	@Override
	public void register(FMLPreInitializationEvent event, SULoader loader) {
		loader.registerBlockWithItemVariant(this);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world,int meta) {
		return new EnchantedEnderDiggerTile();
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state){
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public String getIdentifier() {
		return "enchanted_ender_digger";
	}
	
	@Override
	public String getUnlocalizedName(){
		return this.getIdentifier();
	}
	
	@Override
	public Block getInstance() {
		return this;
	}
	
	@Override
	public boolean onBlockActivated(World world,BlockPos pos,IBlockState state, EntityPlayer player,EnumHand hand, ItemStack heldItem, EnumFacing side, float par7, float par8, float par9){
		if(world.isRemote){
			return false;
		}else{
			FMLNetworkHandler.openGui(player, SurvivalEquipment.instance, SurvivalEquipment.guiHandler.ENCHANTED_ENDER_DIGGER_ID, world, pos.getX(), pos.getY(), pos.getZ());
			return true;
		}
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos,IBlockState state){
		if(world.isRemote){
			return;
		}
		EnchantedEnderDiggerTile tile = (EnchantedEnderDiggerTile) world.getTileEntity(pos);
		tile.onDestroyed();
		super.breakBlock(world, pos,state);
	}
	
}
