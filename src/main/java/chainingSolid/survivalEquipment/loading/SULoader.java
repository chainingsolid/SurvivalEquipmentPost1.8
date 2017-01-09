package chainingSolid.survivalEquipment.loading;

import java.util.ArrayList;

import com.google.common.util.concurrent.CycleDetectingLockFactory.PotentialDeadlockException;

import chainingSolid.survivalEquipment.SurvivalEquipment;
import chainingSolid.survivalEquipment.client.renders.GenericTileEntityRenderer;
import chainingSolid.survivalEquipment.loading.loadable.LoadableBlock;
import chainingSolid.survivalEquipment.loading.loadable.LoadableItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraftforge.client.event.GuiScreenEvent.PotionShiftEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;

public class SULoader {
	
	private ArrayList<LoadableItem> itemsToLoad;
	
	private ArrayList<LoadableBlock> blocksToLoad;
	
	private ArrayList<ItemBlock> itemBlocks = new ArrayList<ItemBlock>();
	
	public void preInit(FMLPreInitializationEvent event){
		itemsToLoad = new ArrayList<LoadableItem>();
		blocksToLoad = new ArrayList<LoadableBlock>();
		
		this.addItems();
		this.addBlocks();
		
		this.registerItemList(event);
		this.registerBlockList(event);
		
		
		
	}
	
	public void clientPreInint(FMLPreInitializationEvent event){
		
		for(LoadableItem item : itemsToLoad){
			item.registerModels(event, this);
		}
		
		for(LoadableBlock block : blocksToLoad){
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block.getInstance()), 0, new ModelResourceLocation(SurvivalEquipment.MOD_ID+":"+block.getIdentifier(), "inventory"));
		}
		
	}
	
	
	
	public void dumpIdsToConsole(){
		for(LoadableItem item : itemsToLoad){
			System.out.println(" "+item.getIdentifier());
		}
		for(LoadableBlock block : blocksToLoad){
			System.out.println(" "+block.getIdentifier());
		}
	}
	
	public void init(FMLInitializationEvent event){
		this.registerRecipes();
	}
	
	public void postInit(FMLPostInitializationEvent event){
		
	}
	
	public void addTileEntityRenderers(FMLPreInitializationEvent event) {
		
		GenericTileEntityRenderer renderer = new GenericTileEntityRenderer();
		
	}
	
	private void addItems(){
		itemsToLoad.add(ItemList.stripMinerPick);
		itemsToLoad.add(ItemList.enchantedBag);
		itemsToLoad.add(ItemList.horseUpgrader);
		itemsToLoad.add(ItemList.shapeBuildingHelper);
	}
	
	private void addBlocks(){
		//blocksToLoad.add(BlockList.enchatntedEnderDigger);
		//blocksToLoad.add(BlockList.enchantedEnderDiggerMarker);
		//blocksToLoad.add(BlockList.plainEnchantedEnderDiggerMarker);
		blocksToLoad.add(BlockList.mobFarmFloor);
		blocksToLoad.add(BlockList.enchantedBagChest);
		blocksToLoad.add(BlockList.lightProjector);
		blocksToLoad.add(BlockList.replaceableBlock);
	}
	
	private void registerItemList(FMLPreInitializationEvent event){
		for(LoadableItem item : itemsToLoad){
			item.register(event, this);
		}
	}
	
	public void registerBlockList(FMLPreInitializationEvent event){
		for(LoadableBlock item : blocksToLoad){
			item.register(event, this);
		}
	}
	
	public void registerItem(LoadableItem item){
		item.getInstance().setRegistryName(SurvivalEquipment.MOD_ID, item.getIdentifier());
		item.getInstance().setUnlocalizedName(SurvivalEquipment.MOD_ID+":"+item.getIdentifier());
		GameRegistry.register(item.getInstance());
		item.getInstance().setCreativeTab(SurvivalEquipment.tab);
	}
	
	public void registerBlockWithItemVariant(LoadableBlock block){
		registerBlock(block);
		
		ItemBlock tempItem = new ItemBlock(block.getInstance());
		tempItem.setRegistryName(SurvivalEquipment.MOD_ID, block.getIdentifier());
		tempItem.setUnlocalizedName(SurvivalEquipment.MOD_ID);
		GameRegistry.register(tempItem);
		tempItem.setCreativeTab(SurvivalEquipment.tab);
		
	}
	
	public void registerBlock(LoadableBlock block){
		block.getInstance().setRegistryName(SurvivalEquipment.MOD_ID, block.getIdentifier());
		block.getInstance().setUnlocalizedName(SurvivalEquipment.MOD_ID+":"+block.getIdentifier());
		GameRegistry.register(block.getInstance());
		block.getInstance().setCreativeTab(SurvivalEquipment.tab);
	}
	
	public void registerItemModelDefualt(LoadableItem item){
		registerItemModel(item, 0, SurvivalEquipment.MOD_ID+":"+item.getIdentifier());
	}
	
	public void registerItemModel(LoadableItem item, int metaData, String location){
		ModelLoader.setCustomModelResourceLocation(item.getInstance(), metaData, new ModelResourceLocation(location, "inventory"));
	}
	
	public void registerBlockModelDefualt(LoadableBlock block){
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block.getInstance()), 0, new ModelResourceLocation(SurvivalEquipment.MOD_ID+":"+block.getIdentifier(), "inventory"));
	}
	
	
	
	private void registerRecipes(){
		GameRegistry.addRecipe(new ItemStack(ItemList.stripMinerPick,1),"sis"," i "," i ",'s',Blocks.STONE,'i',Items.IRON_INGOT);
		GameRegistry.addRecipe(new ItemStack(Blocks.NETHERRACK,8),"ccc","cbc","ccc",'c',Blocks.COBBLESTONE,'b',Items.BLAZE_POWDER);
		GameRegistry.addRecipe(new ItemStack(Blocks.SOUL_SAND,8),new Object[]{"sss","sws","sss",'s',Blocks.SAND,'w',new ItemStack(Items.SKULL, 1, 1)});
		
		GameRegistry.addRecipe(new ItemStack(ItemList.enchantedBag),"lsl","lsl","bgb",'l',Items.LEATHER,'s',Items.STRING,'b',Items.ENCHANTED_BOOK,'g',Items.GOLD_INGOT);
		GameRegistry.addRecipe(new ItemStack(BlockList.enchantedBagChest),"lbl","lcl","lbl",'l',Items.LEATHER,'c',Blocks.CHEST,'b',Items.ENCHANTED_BOOK);
		
		
		GameRegistry.addRecipe(new ItemStack(BlockList.mobFarmFloor,16),"pdp","sis","srs",'p',Blocks.WOODEN_PRESSURE_PLATE,'d',Blocks.TRAPDOOR,'s',Items.STICK,'i',Blocks.IRON_BARS,'r',Items.REDSTONE);
		
		GameRegistry.addSmelting(new ItemStack(Items.WHEAT,1), new ItemStack(Items.STRING,1), 0);
		
		GameRegistry.addRecipe(new ItemStack(Items.DIAMOND_HORSE_ARMOR,1),"  d","dsd","ddd",'d',Items.DIAMOND,'s',Items.SADDLE);
		GameRegistry.addRecipe(new ItemStack(Items.GOLDEN_HORSE_ARMOR,1),"  g","gsg","ggg",'g',Items.GOLD_INGOT,'s',Items.SADDLE);
		GameRegistry.addRecipe(new ItemStack(Items.IRON_HORSE_ARMOR,1),"  i","isi","iii",'i',Items.IRON_INGOT,'s',Items.SADDLE);
		RecipeSorter.INSTANCE.register("SurvivalEquipment", ShapelessNBTSpecificRecipe.class, Category.SHAPELESS, "");
		registerHorseUpgraderRecipe();
		
		GameRegistry.addRecipe(new ItemStack(ItemList.shapeBuildingHelper,1), "i i"," d "," b ", 'i', Items.IRON_INGOT, 'd', Items.DIAMOND, 'b', Items.WRITABLE_BOOK);
		
		//GameRegistry.addRecipe(new ItemStack(BlockList.plainEnchantedEnderDiggerMarker,8),"ooo","oco","ooo",'c',Items.COMPASS,'o',Blocks.OBSIDIAN);
		//GameRegistry.addRecipe(new ItemStack(BlockList.enchantedEnderDiggerMarker),"c","m","s",'s',Blocks.CHEST,'c',Items.COMPARATOR,'m',BlockList.plainEnchantedEnderDiggerMarker);
		//GameRegistry.addRecipe(new ItemStack(BlockList.enchatntedEnderDigger),"bpb","cmc","oto",'b',Items.BLAZE_ROD,'p',Items.ENDER_PEARL,'c',Items.COMPARATOR,'m',BlockList.enchantedEnderDiggerMarker,'o',Blocks.OBSIDIAN,'t',Blocks.ENCHANTING_TABLE);
		
	}
	
	private void registerHorseUpgraderRecipe(){
		
		ItemStack jumpBoostPotionLong = getPotionItemStackFromPotionRegistryName("long_leaping");
		ItemStack jumpBoostPotionStrong = getPotionItemStackFromPotionRegistryName("strong_leaping");
		ItemStack swiftnessPotionLong = getPotionItemStackFromPotionRegistryName("long_swiftness");
		ItemStack swiftnessPotionStrong = getPotionItemStackFromPotionRegistryName("strong_swiftness");
		ItemStack instantHealth1 = getPotionItemStackFromPotionRegistryName("healing");
		ItemStack instantHealth2 = getPotionItemStackFromPotionRegistryName("strong_healing");
		ItemStack diamondHorseArmor = new ItemStack(Items.DIAMOND_HORSE_ARMOR,1);
		ItemStack goldHorseArmor = new ItemStack(Items.GOLDEN_HORSE_ARMOR,1);
		ItemStack ironHorseArmor = new ItemStack(Items.IRON_HORSE_ARMOR,1);
		
		ArrayList<ItemStack> ingrediants = new ArrayList<ItemStack>();
		ingrediants.add(jumpBoostPotionLong);
		ingrediants.add(jumpBoostPotionStrong);
		ingrediants.add(swiftnessPotionLong);
		ingrediants.add(swiftnessPotionStrong);
		ingrediants.add(instantHealth1);
		ingrediants.add(instantHealth2);
		ingrediants.add(diamondHorseArmor);
		ingrediants.add(goldHorseArmor);
		ingrediants.add(ironHorseArmor);
		
		GameRegistry.addRecipe(new ShapelessNBTSpecificRecipe(new ItemStack(ItemList.horseUpgrader,1), ingrediants));
		
	}
	
	private ItemStack getPotionItemStackFromPotionRegistryName(String potionName){
		ItemStack stack = new ItemStack(Items.POTIONITEM, 1);
		PotionUtils.addPotionToItemStack(stack, PotionType.getPotionTypeForName(potionName));
		return stack;
	}
	
	
	
	
	
}
