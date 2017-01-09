package chainingSolid.survivalEquipment.entity;

import net.minecraft.entity.EntityCreature;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;

import com.mojang.authlib.GameProfile;

public class ProgrammableFakePlayer extends EntityCreature {
	
	private FakePlayer fakePlayer;
	
	public ProgrammableFakePlayer(World world) {
		super(world);
		
	}
	
	
	private void setUpFakePayer(){
		WorldServer world;
		GameProfile profile = new GameProfile(entityUniqueID, "programmablePlayer"+this.getEntityId());
		if(this.worldObj instanceof WorldServer && !this.worldObj.isRemote){
			fakePlayer = FakePlayerFactory.get((WorldServer)this.worldObj, profile);
		}
		
		
	}
}
