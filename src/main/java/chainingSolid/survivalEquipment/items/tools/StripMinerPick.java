package chainingSolid.survivalEquipment.items.tools;

import java.util.List;

import chainingSolid.survivalEquipment.SurvivalEquipment;
import chainingSolid.survivalEquipment.achievement.SurvivalEquipmentAchievements;
import chainingSolid.survivalEquipment.loading.SULoader;
import chainingSolid.survivalEquipment.loading.loadable.LoadableItem;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class StripMinerPick extends ItemPickaxe implements LoadableItem{

	public StripMinerPick() {
		super(ToolMaterial.IRON);
		this.setMaxStackSize(1);
		this.setMaxDamage(2500);
	}
	
	@Override
	public void register(FMLPreInitializationEvent event, SULoader loader) {
		loader.registerItem(this);
	}
	
	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player){
		super.onCreated(stack, world, player);
		player.addStat(SurvivalEquipmentAchievements.stripMiningMachine, 1);
		
	}
	
	@Override
	public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, EntityPlayer player){
		int effcencyLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, stack);
		if(effcencyLevel > 0){
			player.addStat(SurvivalEquipmentAchievements.theFastMiner[effcencyLevel-1], 1);
		}
		
		int unbreakinglevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.UNBREAKING, stack);
		if(unbreakinglevel > 0){
			player.addStat(SurvivalEquipmentAchievements.theLongMiner[unbreakinglevel-1], 1);
		}
		
		stack.setRepairCost(0);
		
		return false;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_){
		list.add("can be repaied without increaing cost");
	}
	
	@Override
	public float getStrVsBlock(ItemStack stack, IBlockState state){
		if(this.isBlockMineAble(state.getBlock())){
			return 30F;
		}else{
			return .5F;
		}
	}
	
	@Override
	public boolean canHarvestBlock(IBlockState block){
		return this.isBlockMineAble(block.getBlock());
	}
	
	private boolean isBlockMineAble(Block block){
		if(Blocks.STONE == block || block == Blocks.COBBLESTONE || block == Blocks.NETHERRACK){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public String getIdentifier() {
		return "strip_miner_pick";
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
		loader.registerItemModelDefualt(this);
	}
	
}
