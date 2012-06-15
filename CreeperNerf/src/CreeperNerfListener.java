public class CreeperNerfListener extends PluginListener
{
	int maxAltitude;
	String deathmessage;
	public void setAltAndDM(int maxalt, String msg)
	{
		maxAltitude = maxalt;
		deathmessage = msg;
	}
	public boolean onExplode(Block block, OEntity entity, @SuppressWarnings("rawtypes") java.util.HashSet blocksaffected)
	{
		if (block.getStatus() == 2 && block.getY() > maxAltitude)
		{ //if the block was exploded by creeper AND is placed above maxAltitude.
			 for (Player p : etc.getServer().getPlayerList()) 
			 {
				  if (CreeperNerf.isInExplosionRadius(p, block))
				  {
					  int damage = 5 + (int)(Math.random() * ((10 - 5) + 1)); //Random damage between 10 and 5.
					  if (p.getHealth() - damage < 1)
					  { //Drop items if player will die
						  p.dropInventory();
					  }
					  p.setHealth(p.getHealth() - damage);  
					  if (p.getHealth() < 1)
					  {  	
						  etc.getServer().messageAll(p.getName()+" "+deathmessage);
				      }
				  }
			 }
			 return true;
		}
		else 
		{
			return false;
		}
	}		
}