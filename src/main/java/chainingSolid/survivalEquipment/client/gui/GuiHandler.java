package chainingSolid.survivalEquipment.client.gui;

import chainingSolid.survivalEquipment.SurvivalEquipment;
import chainingSolid.survivalEquipment.tileEnties.EnchantedBagChestTile;
import chainingSolid.survivalEquipment.tileEnties.EnchantedEnderDiggerMarkerTile;
import chainingSolid.survivalEquipment.tileEnties.EnchantedEnderDiggerTile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GuiHandler implements net.minecraftforge.fml.common.network.IGuiHandler {
	
	
	public final static int ENCHANTED_BAG_ID = 0,
			ENCHANTED_ENDER_DIGGER_ID = 1,
			ITEM_STACK_PIPES = 2,
			ENCHANTED_ENDER_DIGGER_MARKER = 3,
			ENCHANTED_BAG_CHEST = 4,
			NETHER_SORTER = 5;
	
	public GuiHandler(){
		net.minecraftforge.fml.common.network.NetworkRegistry.INSTANCE.registerGuiHandler(SurvivalEquipment.instance, this);
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x,int y,int z) {
		BlockPos pos = new BlockPos(x,y,z);
		try{
			TileEntity te = world.getTileEntity(pos);
			switch(ID){
			case ENCHANTED_BAG_ID:
				if(player != null){
					return new EnchantedBagViewerContainer(player);
				}
				break;
			case ENCHANTED_ENDER_DIGGER_ID:
				EnchantedEnderDiggerTile tile = (EnchantedEnderDiggerTile)te;
				
				if(tile != null &&  player != null){
					return new ContainerEnchantedEnderDigger(tile,player);
				}
				break;
			case ENCHANTED_ENDER_DIGGER_MARKER:
				EnchantedEnderDiggerMarkerTile markerTile = (EnchantedEnderDiggerMarkerTile)te;
				if(markerTile != null || player != null){
					return new ContianerEnchantedEnderDiggerMarker(player, markerTile);
				}
				return null;
			case ENCHANTED_BAG_CHEST:
				if(player != null && te instanceof EnchantedBagChestTile){
					return new EnchantedBagChestContianer(player,(EnchantedBagChestTile)te);
				}
				break;
			}
		}catch(Exception exeption){
			exeption.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x,y,z);
		try{
			TileEntity te = world.getTileEntity(pos);
			switch(ID){
			case ENCHANTED_BAG_ID:
				if(player != null){
					return new GuiEnchantedBagViewer(new EnchantedBagViewerContainer(player),player);
				}
				break;
			case ENCHANTED_ENDER_DIGGER_ID:
				EnchantedEnderDiggerTile tile = (EnchantedEnderDiggerTile)te;
				if(tile != null && player != null){
					return new EnchantedEnderDiggerGui(new ContainerEnchantedEnderDigger(tile,player));
				}
				break;
			case ENCHANTED_BAG_CHEST:
				if(player != null && te instanceof EnchantedBagChestTile){
					return new EnchantedBagChestGUI(new EnchantedBagChestContianer(player,(EnchantedBagChestTile)te));
				}
			case ENCHANTED_ENDER_DIGGER_MARKER:
				EnchantedEnderDiggerMarkerTile markerTile = (EnchantedEnderDiggerMarkerTile)te;
				if(markerTile != null && player != null){
					return new SurvivalEquipmentGui(new ContianerEnchantedEnderDiggerMarker(player, markerTile));
				}
				break;
			}
		}catch(Exception exeption){
			exeption.printStackTrace();
		}
		return null;
	}
	
}
