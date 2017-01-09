package chainingSolid.survivalEquipment.items.shapeBuildingHelper;

import java.util.HashMap;

import chainingSolid.survivalEquipment.loading.BlockList;
import chainingSolid.survivalEquipment.loading.ItemList;
import chainingSolid.survivalEquipment.util.Util;
import chainingSolid.survivalEquipment.util.math.Vector4f;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentSelector;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraft.world.World;

public abstract class BuildingShape{
	
	//TODO add code to allow default values for traits
	//TODO Longterm goal add visual line to show the shape roughly
	
	
	public static int radiusToPlaceReplacebleBlocks = 8;
	
	public static final HashMap<String, BuildingShape> BUILDING_SHAPE_REGISTERY = new HashMap<String, BuildingShape>();
	
	public static final String COMMAND_ARG_CLEAR = "clear",
			COMMAND_ARG_DISPLAY_SET_SHAPE_MENU = "setShapeMenu", COMMAND_ARG_SET_SHAPE = "setShape",
			COMMAND_ARG_DISPLAY_SET_TRAIT_MENU = "setTraitMenu", COMMAND_ARG_SET_TRAIT = "setTrait",
			COMMAND_ARG_CHANGE_TRAIT = "changeTrait";
	
	public static final String COMMAND_ARG_TRAIT_TYPE_POSITION = "position";
	
	public static final String TRAIT_ORIGIN = "traitOrigin",
			TRAIT_RADIUS = "traitRadius",
			TRAIT_THICKNESS = "traitThickness",
			TRAIT_HIEGHT = "traitHeight",
			TRAIT_HEIGHT_UP = "traitHeightUp",
			TRAIT_HEIGHT_DOWN = "traitHeightDown";
	
	
	public static final String COMMAND_ARG_CONST_Y_AXIS = "yAxis", COMMAND_ARG_CONST_X_AXIS = "xAxis", COMMAND_ARG_CONST_Z_AXIS = "zAxis",
			COMMAND_ARG_CONST_PLUS_ONE = "+1", COMMAND_ARG_CONST_PLUS_TEN = "+10",COMMAND_ARG_CONST_PLUS_ONE_HUNDRED = "+100",
			COMMAND_ARG_CONST_MINUS_ONE = "-1", COMMAND_ARG_CONST_MINUS_TEN = "-10",COMMAND_ARG_CONST_MINUS_ONE_HUNDRED = "-100",
			COMMAND_ARG_CONST_SET_POSITION_HERE = "setPositionHere";
	
	public final String UNIQUE_ID;
	
	public final HashMap<String, EnumTraitType> traitListOnShapeAndTraitToItsType = new HashMap<String, EnumTraitType>();
	
	public BuildingShape(String shapeUniqueId){
		UNIQUE_ID = shapeUniqueId;
		addTraitToShape(TRAIT_ORIGIN, EnumTraitType.POSITION);
	}
	
	public void addTraitToShape(String trait, EnumTraitType type){
		traitListOnShapeAndTraitToItsType.put(trait, type);
	}
	
	public abstract boolean isBlockPosPartOfShape(BlockPos pos, ItemStack stack, World world);
	
	public String getBuildingShapeUniquieId(){
		return UNIQUE_ID;
	}
	
	public abstract String[] getTraits();
	
	public void onMenuCommand(EntityPlayer player, ItemStack stack, String[] commandArgs){
		if(commandArgs[0].equals(COMMAND_ARG_CLEAR)){
			ItemList.shapeBuildingHelper.clear(stack);
			player.addChatMessage(new TextComponentString("Cleared shape settings on held ShapeBuildingHelper"));
		}else if(commandArgs[0].equals(COMMAND_ARG_DISPLAY_SET_TRAIT_MENU)){
			displayTraitListMenu(player, stack);
		}else if(commandArgs[0].equals(COMMAND_ARG_SET_TRAIT)){
			handleCommandSetTrait(player, stack, commandArgs);
		}else if(commandArgs[0].equals(COMMAND_ARG_CHANGE_TRAIT)){
			handleCommandChangeTrait(player, stack, commandArgs);
		}
		
	}
	
	public void handleCommandSetTrait(EntityPlayer player, ItemStack stack, String[] commandArgs){
		if(traitListOnShapeAndTraitToItsType.containsKey(commandArgs[1])){
			EnumTraitType type = traitListOnShapeAndTraitToItsType.get(commandArgs[1]);
			switch(type){
				case POSITION:
					displayPositionTraitMenu(player, stack, commandArgs);
				break;
				case FLOAT:
					displayFloatTraitMenu(player, stack, commandArgs);
				break;
			}
		}
		
	}
	
	public void handleCommandChangeTrait(EntityPlayer player, ItemStack stack, String[] commandArgs){
		if(traitListOnShapeAndTraitToItsType.containsKey(commandArgs[1])){
			EnumTraitType type = traitListOnShapeAndTraitToItsType.get(commandArgs[1]);
			switch(type){
				case POSITION:
					changePositionTrait(player, stack, commandArgs);
				break;
				case FLOAT:
					changeFloatTrait(player, stack, commandArgs);
				break;
			}
		}
	}
	
	public void changePositionTrait(EntityPlayer player, ItemStack stack, String[] commandArgs){
		String trait = commandArgs[1];
		if(commandArgs[2].equals(COMMAND_ARG_CONST_SET_POSITION_HERE)){
			setTraitVector4f(stack, trait, new Vector4f((float)Math.floor(player.posX), (float)Math.floor(player.posY), (float)Math.floor(player.posZ),1));
		}else if(commandArgs[2].equals(COMMAND_ARG_CONST_X_AXIS)){
			Vector4f position = getTraitVector4f(stack, trait);
			position.x += getNumberConst(commandArgs[3]);
			setTraitVector4f(stack,trait, position);
		}else if(commandArgs[2].equals(COMMAND_ARG_CONST_Y_AXIS)){
			Vector4f position = getTraitVector4f(stack, trait);
			position.y += getNumberConst(commandArgs[3]);
			setTraitVector4f(stack,trait, position);
		}else if(commandArgs[2].equals(COMMAND_ARG_CONST_Z_AXIS)){
			Vector4f position = getTraitVector4f(stack, trait);
			position.z += getNumberConst(commandArgs[3]);
			setTraitVector4f(stack,trait, position);
		}
		
		displayPositionTraitMenu(player, stack, commandArgs);
	}
	
	public void changeFloatTrait(EntityPlayer player, ItemStack stack, String[] commandArgs){
		float trait = getTraitFloat(stack, commandArgs[1]);
		trait += getNumberConst(commandArgs[2]);
		setTraitFloat(stack, commandArgs[1], trait);
		displayFloatTraitMenu(player, stack, commandArgs);
	}
	
	public float getNumberConst(String constArg){
		if(constArg.equals(COMMAND_ARG_CONST_MINUS_ONE)){
			return -1;
		}else if(constArg.equals(COMMAND_ARG_CONST_MINUS_TEN)){
			return -10;
		}else if(constArg.equals(COMMAND_ARG_CONST_MINUS_ONE_HUNDRED)){
			return -100;
		}else if(constArg.equals(COMMAND_ARG_CONST_PLUS_ONE)){
			return 1;
		}else if(constArg.equals(COMMAND_ARG_CONST_PLUS_TEN)){
			return 10;
		}else if(constArg.equals(COMMAND_ARG_CONST_PLUS_ONE_HUNDRED)){
			return 100;
		}
		return 0;
	}
	
	public void onUpdate(ItemStack stack, World world, EntityPlayer player){
		for(int xOff = -radiusToPlaceReplacebleBlocks; xOff <= radiusToPlaceReplacebleBlocks; xOff++){
			for(int yOff = -radiusToPlaceReplacebleBlocks; yOff <= radiusToPlaceReplacebleBlocks; yOff++){
				for(int zOff = -radiusToPlaceReplacebleBlocks; zOff <= radiusToPlaceReplacebleBlocks; zOff++){
					BlockPos checkingPos = new BlockPos(player.posX+xOff, player.posY+yOff, player.posZ+zOff);
					IBlockState state = world.getBlockState(checkingPos);
					if(state.getBlock().isAir(state, world, checkingPos) || state.getBlock().isReplaceable(world, checkingPos)){
						if(this.isBlockPosPartOfShape(checkingPos, stack, world)){
							placeReplacableBlock(checkingPos, world);
						}
					}
					
				}
			}
		}
	}
	
	public void placeReplacableBlock(BlockPos pos, World world){
		world.setBlockState(pos, BlockList.replaceableBlock.getDefaultState());
	}
	
	public void displayTraitListMenu(EntityPlayer player, ItemStack stack){
		for(String trait : getTraits()){
			player.addChatMessage(getMenuComponent(trait, ShapeBuildingHelperCommand.getCommand(COMMAND_ARG_SET_TRAIT, trait), "Click to change "+trait));
		}
	}
	
	public void displayPositionTraitMenu(EntityPlayer player, ItemStack stack, String[] commandArgs){
		String trait = commandArgs[1];
		Vector4f position = getTraitVector4f(stack, trait);
		ITextComponent headerLine = getMenuComponent(trait+"   "+position.xyzToString(), ShapeBuildingHelperCommand.getCommand(COMMAND_ARG_CHANGE_TRAIT, trait, COMMAND_ARG_CONST_SET_POSITION_HERE), "Set "+trait+" to where your standing");
		player.addChatMessage(headerLine);
		player.addChatMessage(getNumberAdjustLine("X ", COMMAND_ARG_CHANGE_TRAIT+" "+trait+" "+COMMAND_ARG_CONST_X_AXIS,"X"));
		player.addChatMessage(getNumberAdjustLine("Y ", COMMAND_ARG_CHANGE_TRAIT+" "+trait+" "+COMMAND_ARG_CONST_Y_AXIS,"Y"));
		player.addChatMessage(getNumberAdjustLine("Z ", COMMAND_ARG_CHANGE_TRAIT+" "+trait+" "+COMMAND_ARG_CONST_Z_AXIS,"Z"));
		
	}
	
	public void displayFloatTraitMenu(EntityPlayer player, ItemStack stack, String[] commandArgs){
		String trait = commandArgs[1];
		float value = getTraitFloat(stack, trait);
		player.addChatMessage(new TextComponentString(trait+": "+value));
		player.addChatMessage(getNumberAdjustLine(":", COMMAND_ARG_CHANGE_TRAIT+" "+trait, "Change "+trait));
	}
	
	public ITextComponent getNumberAdjustLine(String start, String startOfCommand, String description){
		ITextComponent numberAdjustLine = new TextComponentSelector(start);
		numberAdjustLine.appendText(" ");
		numberAdjustLine.appendSibling(getMenuComponent(COMMAND_ARG_CONST_PLUS_ONE_HUNDRED, ShapeBuildingHelperCommand.getCommand(startOfCommand, COMMAND_ARG_CONST_PLUS_ONE_HUNDRED), description));
		numberAdjustLine.appendText(" ");
		numberAdjustLine.appendSibling(getMenuComponent(COMMAND_ARG_CONST_PLUS_TEN, ShapeBuildingHelperCommand.getCommand(startOfCommand, COMMAND_ARG_CONST_PLUS_TEN), description));
		numberAdjustLine.appendText(" ");
		numberAdjustLine.appendSibling(getMenuComponent(COMMAND_ARG_CONST_PLUS_ONE, ShapeBuildingHelperCommand.getCommand(startOfCommand, COMMAND_ARG_CONST_PLUS_ONE), description));
		numberAdjustLine.appendText(" ");
		numberAdjustLine.appendSibling(getMenuComponent(COMMAND_ARG_CONST_MINUS_ONE, ShapeBuildingHelperCommand.getCommand(startOfCommand, COMMAND_ARG_CONST_MINUS_ONE), description));
		numberAdjustLine.appendText(" ");
		numberAdjustLine.appendSibling(getMenuComponent(COMMAND_ARG_CONST_MINUS_TEN, ShapeBuildingHelperCommand.getCommand(startOfCommand, COMMAND_ARG_CONST_MINUS_TEN), description));
		numberAdjustLine.appendText(" ");
		numberAdjustLine.appendSibling(getMenuComponent(COMMAND_ARG_CONST_MINUS_ONE_HUNDRED, ShapeBuildingHelperCommand.getCommand(startOfCommand, COMMAND_ARG_CONST_MINUS_ONE_HUNDRED), description));
		numberAdjustLine.appendText(" ");
		return numberAdjustLine;
	}
	
	
	public static ITextComponent getMenu(ItemStack stack){
		ITextComponent menu = new TextComponentSelector("");
		if(ItemList.shapeBuildingHelper.hasBuildingShapeSet(stack)){
			ITextComponent clear = getMenuComponent("clear", ShapeBuildingHelperCommand.getCommand(COMMAND_ARG_CLEAR), "Clear the settings on held ShapeBuildingHelper");
			menu.appendSibling(clear);
			menu.appendText(" ");
		}
		
		ITextComponent setShape = getMenuComponent("set Shape", ShapeBuildingHelperCommand.getCommand(COMMAND_ARG_DISPLAY_SET_SHAPE_MENU), "Set shape on held ShapeBuildingHelper");
		menu.appendSibling(setShape);
		menu.appendText(" ");
		
		if(ItemList.shapeBuildingHelper.hasBuildingShapeSet(stack)){
			ITextComponent setTrait = getMenuComponent("set Trait", ShapeBuildingHelperCommand.getCommand(COMMAND_ARG_DISPLAY_SET_TRAIT_MENU), "Brings up trait selector list");
			menu.appendSibling(setTrait);
		}
		
		return menu;
	}
	
	public static ITextComponent getMenuComponent(String name, String command, String description){
		ITextComponent component = new TextComponentString("["+name+"]");
		Style s = new Style();
		s.setBold(true);
		s.setColor(TextFormatting.BLUE);
		s.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
		HoverEvent toolTip = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString(description));
		s.setHoverEvent(toolTip);
		component.setStyle(s);
		return component;
	}
	
	public static Style getNormalStyle(){
		return new Style().setBold(false).setColor(TextFormatting.WHITE);
	}
	
	public static void displaySetShapeMenu(EntityPlayer player){
		for(String shapeId : BUILDING_SHAPE_REGISTERY.keySet()){
			player.addChatMessage(getMenuComponent(shapeId, ShapeBuildingHelperCommand.getCommand(COMMAND_ARG_SET_SHAPE, shapeId), "Change shape to "+shapeId));
		}
	}
	
	public float getTraitFloat(ItemStack stack, String traitId){
		Util.nullNBTTagCheck(stack);
		return stack.getTagCompound().getFloat(traitId);
	}
	
	public void setTraitFloat(ItemStack stack, String traitId, float trait){
		Util.nullNBTTagCheck(stack);
		stack.getTagCompound().setFloat(traitId, trait);
	}
	
	public Vector4f getTraitVector4f(ItemStack stack, String traitId){
		Util.nullNBTTagCheck(stack);
		Vector4f vector = new Vector4f();
		vector.x = stack.getTagCompound().getFloat(traitId+"_x");
		vector.y = stack.getTagCompound().getFloat(traitId+"_y");
		vector.z = stack.getTagCompound().getFloat(traitId+"_z");
		vector.w = stack.getTagCompound().getFloat(traitId+"_w");
		return vector;
	}
	
	public void setTraitVector4f(ItemStack stack, String traitId, Vector4f vector){
		Util.nullNBTTagCheck(stack);
		stack.getTagCompound().setFloat(traitId+"_x", vector.x);
		stack.getTagCompound().setFloat(traitId+"_y", vector.y);
		stack.getTagCompound().setFloat(traitId+"_z", vector.z);
		stack.getTagCompound().setFloat(traitId+"_w", vector.w);
	}
	
	public static void registerShapes(){
		registerBuildingShape(new BuildingShapeCircle());
		registerBuildingShape(new BuildingShapeDome());
		
	}
	
	public static void registerBuildingShape(BuildingShape shape){
		BUILDING_SHAPE_REGISTERY.put(shape.UNIQUE_ID, shape);
	}
	
	public enum EnumTraitType{
		POSITION,
		FLOAT
		;
	} 
	
}
