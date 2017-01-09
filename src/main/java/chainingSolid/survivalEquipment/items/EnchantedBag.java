package chainingSolid.survivalEquipment.items;

import chainingSolid.survivalEquipment.SurvivalEquipment;
import chainingSolid.survivalEquipment.loading.SULoader;
import chainingSolid.survivalEquipment.loading.loadable.LoadableItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;

public class EnchantedBag extends Item implements LoadableItem{
	
	public static final String IS_OPEN = "is_open";
	
	public EnchantedBag(){
		super();
		this.setMaxStackSize(1);
		
	}
	
	@Override
	public void register(FMLPreInitializationEvent event, SULoader loader) {
		loader.registerItem(this);
	}
	
	@Override
	public void registerModels(FMLPreInitializationEvent event, SULoader loader) {
		loader.registerItemModel(this, 0, SurvivalEquipment.MOD_ID+":"+getIdentifier()+"_closed");
		loader.registerItemModel(this, 1, SurvivalEquipment.MOD_ID+":"+getIdentifier()+"_open");
	}
	
	@Override
	public String getIdentifier() {
		return "enchanted_bag";
	}
	
	@Override
	public Item getInstance() {
		return this;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand){
		if(player.isSneaking()){
			return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
		}else{
			if(!world.isRemote){
				FMLNetworkHandler.openGui(player, SurvivalEquipment.instance, SurvivalEquipment.guiHandler.ENCHANTED_BAG_ID, world, 0, 0, 0);
			}
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack); 
		}
		
		/*if(world.isRemote){
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
		}else{
			if(!player.isSneaking()){
				FMLNetworkHandler.openGui(player, SurvivalEquipment.instance, SurvivalEquipment.guiHandler.ENCHANTED_BAG_ID, world, 0, 0, 0);
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
			}
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
		}*/
	}
	
	@Override
	public boolean hasEffect(ItemStack stack){
		return true;
	}
	
	public int getMetadata(ItemStack stack){
		if(this.isOpen(stack)){
			return 1;
		}else{
			return 0;
		}
	}
	
	@Override
	public void onCreated(ItemStack stack, World par2World, EntityPlayer par3EntityPlayer){
		stack.setTagCompound(new NBTTagCompound());
		setIsOpen(stack, false);
	}
	
	public boolean isOpen(ItemStack stack){
		if(!stack.hasTagCompound()){
			return false;
		}
		return stack.getTagCompound().getBoolean(IS_OPEN);
	}
	
	public void setIsOpen(ItemStack stack, boolean isOpen){
		stack.getTagCompound().setBoolean(IS_OPEN, isOpen);
	}

	
	
}
