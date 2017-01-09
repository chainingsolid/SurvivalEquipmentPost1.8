package chainingSolid.survivalEquipment.items.shapeBuildingHelper;

import chainingSolid.survivalEquipment.loading.ItemList;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;

public class ShapeBuildingHelperCommand extends CommandBase {
	
	public static final ShapeBuildingHelperCommand INSTANCE = new ShapeBuildingHelperCommand();
	
	public static final String COMMAND_NAME = "ShapeBuildingHelperCommand";
	
	public ShapeBuildingHelperCommand() {
		
	}
	
	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}
	
	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "You shouldn't be using this, just clicking the links in chat from using(swinging) ShapeBuildingHelper item";
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if(sender instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)sender;
			if(ifStackIsShapeBuilderForwardCommand(player, player.getHeldItemMainhand(), args)){
				return;
			}else{
				ifStackIsShapeBuilderForwardCommand(player, player.getHeldItemOffhand(), args);
			}
		}else{
			return;
		}
	}
	
	public boolean ifStackIsShapeBuilderForwardCommand(EntityPlayer player, ItemStack stack, String[] commandArgs){
		if(stack != null && stack.getItem() == ItemList.shapeBuildingHelper){
			ItemList.shapeBuildingHelper.onMenuCommand(player, stack, commandArgs);
			return true;
		}else{
			return false;
		}
	}
	
	public static String getCommand(String... args){
		StringBuilder builder = new StringBuilder();
		builder.append("/"+ShapeBuildingHelperCommand.COMMAND_NAME+" ");
		for(int i = 0; i < args.length; i++){
			builder.append(args[i]);
			builder.append(" ");
		}
		return builder.toString();
	}
	
}
