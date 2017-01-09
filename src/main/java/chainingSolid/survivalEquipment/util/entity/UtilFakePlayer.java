package chainingSolid.survivalEquipment.util.entity;

import java.util.HashMap;
import java.util.UUID;

import com.mojang.authlib.GameProfile;

import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;

public class UtilFakePlayer extends FakePlayer{
	
	private static HashMap<String ,UtilFakePlayer> fakePlayers = new HashMap<String , UtilFakePlayer>();
	
	
	public UtilFakePlayer(WorldServer world, GameProfile name) {
		super(world, name);
		this.connection = new FakeNetServerHandler(mcServer, this);
	}
	
	public static UtilFakePlayer getFakePlayer(String name,WorldServer world){
		UtilFakePlayer player = fakePlayers.get(name);
		
		if(player == null){
			fakePlayers.put(name, new UtilFakePlayer(world, new GameProfile(new UUID(0,0), name)));
			player = fakePlayers.get(name);
		}
		
		return player;
	}
	
	public static void killFakePlayer(String name){
		fakePlayers.put(name, null);
	}
	
}
