package chainingSolid.survivalEquipment.items.shapeBuildingHelper;

import chainingSolid.survivalEquipment.loading.SULoader;
import chainingSolid.survivalEquipment.loading.loadable.LoadableItem;
import chainingSolid.survivalEquipment.util.Util;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentSelector;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ShapeBuildingHelper extends Item implements LoadableItem {
	
	
	
	public static final String BUILDING_SHAPE_TYPE_SAVE_KEY = "buildingShapeType";
	
	public ShapeBuildingHelper() {
		this.setMaxStackSize(1);
		
	}
	
	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack){
		if(entityLiving instanceof EntityPlayer && !entityLiving.worldObj.isRemote){
			EntityPlayer player = (EntityPlayer)entityLiving;
			player.addChatMessage(BuildingShape.getMenu(stack));
			return true;
		}
		return false;
	}
	
	public void onMenuCommand(EntityPlayer player, ItemStack stack, String[] commandArgs){
		if(commandArgs[0].equals(BuildingShape.COMMAND_ARG_DISPLAY_SET_SHAPE_MENU)){
			BuildingShape.displaySetShapeMenu(player);
			return;
		}else if(commandArgs[0].equals(BuildingShape.COMMAND_ARG_SET_SHAPE)){
			this.setBuildingShape(BuildingShape.BUILDING_SHAPE_REGISTERY.get(commandArgs[1]), stack);
			player.addChatMessage(new TextComponentString("Shape type changed to \""+commandArgs[1]+"\""));
			stack.setStackDisplayName("Shape:"+commandArgs[1]);
			player.addChatMessage(BuildingShape.getMenu(stack));
		}
		if(hasBuildingShapeSet(stack)){
			getBuildingShape(stack).onMenuCommand(player, stack, commandArgs);
		}
		
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand){
		return new ActionResult(EnumActionResult.PASS, itemStackIn);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entityIn, int itemSlot, boolean isSelected){
		if(world.isRemote){
			return;
		}
		if(isSelected && entityIn instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)entityIn;
			if(this.hasBuildingShapeSet(stack)){
				this.getBuildingShape(stack).onUpdate(stack, world, player);
			}
		}
	}
	
	public void clear(ItemStack stack){
		removeBuildingShape(stack);
	}
	
	public void setBuildingShape(BuildingShape shape, ItemStack stack){
		Util.nullNBTTagCheck(stack);
		stack.getTagCompound().setString(BUILDING_SHAPE_TYPE_SAVE_KEY, shape.getBuildingShapeUniquieId());
	}
	
	public BuildingShape getBuildingShape(ItemStack stack){
		Util.nullNBTTagCheck(stack);
		return BuildingShape.BUILDING_SHAPE_REGISTERY.get(stack.getTagCompound().getString(BUILDING_SHAPE_TYPE_SAVE_KEY));
	}
	
	public void removeBuildingShape(ItemStack stack){
		Util.nullNBTTagCheck(stack);
		stack.setTagCompound(new NBTTagCompound());
	}
	
	public boolean hasBuildingShapeSet(ItemStack stack){
		Util.nullNBTTagCheck(stack);//TODO check if an existing key is vaild
		return stack.getTagCompound().hasKey(BUILDING_SHAPE_TYPE_SAVE_KEY);
	}
	
	@Override
	public String getIdentifier() {
		return "shape_building_helper";
	}
	
	@Override
	public void register(FMLPreInitializationEvent event, SULoader loader) {
		loader.registerItem(this);
		BuildingShape.registerShapes();
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
