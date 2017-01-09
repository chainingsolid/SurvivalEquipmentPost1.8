package chainingSolid.survivalEquipment.achievement;

import java.util.ArrayList;

import chainingSolid.survivalEquipment.loading.BlockList;
import chainingSolid.survivalEquipment.loading.ItemList;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class SurvivalEquipmentAchievements {
	
	public static Achievement stripMiningMachine;
	public static Achievement slipperyFast;
	public static Achievement heavyHitting;
	public static Achievement[] theFastMiner;
	public static Achievement[] theLongMiner;
	
	public static AchievementPage survivalEquipmentAchievemntPage;
	public static ArrayList<Achievement> achievement = new ArrayList<Achievement>();
	
	public static void addAchievements(){
		ArrayList<Achievement> achievement = new ArrayList<Achievement>();
		
		stripMiningMachine = makeAchievment("strip mining machine", 0, 0, new ItemStack(ItemList.stripMinerPick), null);
		achievement.add(stripMiningMachine);
		
		theFastMiner = new Achievement[Enchantments.EFFICIENCY.getMaxLevel()];
		for(int i = 0; i < theFastMiner.length;i++){
			Achievement prev = null;
			if(i == 0){
				prev = stripMiningMachine;
			}else{
				prev = theFastMiner[i - 1];
			}
			theFastMiner[i] = makeAchievment("The Fast Miner " + (i+1) , 2, i*2, new ItemStack(ItemList.stripMinerPick),prev);
			achievement.add(theFastMiner[i]);
		}
		
		theLongMiner = new Achievement[Enchantments.UNBREAKING.getMaxLevel()];
		for(int i = 0; i < theLongMiner.length;i++){
			Achievement prev = null;
			if(i == 0){
				prev = stripMiningMachine;
			}else{
				prev = theLongMiner[i - 1];
			}
			theLongMiner[i] = makeAchievment("The Long Miner " + (i+1) , -2, i*2, new ItemStack(ItemList.stripMinerPick),prev);
			achievement.add(theLongMiner[i]);
		}
		
		survivalEquipmentAchievemntPage = new AchievementPage("Survival Equpiment", achievement.toArray(new Achievement[achievement.size()]));
		survivalEquipmentAchievemntPage.registerAchievementPage(survivalEquipmentAchievemntPage);
	}
	
	public static Achievement makeAchievment(String name,int x,int y,ItemStack stack,Achievement requyiered){
		Achievement a = new Achievement(name, name, x, y, stack, requyiered);
		achievement.add(a);
		return a;
	}
	
	
	
	
}
