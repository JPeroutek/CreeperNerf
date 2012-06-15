//Changelog:
//--1.2--
//Just because.. github
//--1.1--
//New random damage
//--1.0--
//Ready for release. Properties working fine
//--0.4--
//Started work on properties file
//--0.3--
//Made players drop items if they'll die
//--0.2--
//Rewriting all (aka copy pasting and some rewriting)
//--0.1--
//Works!

import java.util.logging.Logger; 

public class CreeperNerf extends Plugin { 

	private Logger log = Logger.getLogger("Minecraft");
	public static CreeperNerfListener listener;
	public static String name = "CreeperNerf"; 
	public static String version = "1.2"; 
	public static String author = "oleerik"; 
	//Props file!
	public static PropertiesFile props = new PropertiesFile("plugins/config/CreeperNerf.properties");
	//Setting for this plugin
	static int explosionRad = 4;
	static int maxAltitude;
	static String deathmessage;
	

	public CreeperNerf()
	{ 
		listener = new CreeperNerfListener(); 
	}//initializing the listener

	public void disable()
	{ 
		log.info(name + " disabled."); 
	}

	public void enable()
	{
		log.info(name + " enabled.");
	}

	public void initialize()
	{
		etc.getLoader().addListener(PluginLoader.Hook.EXPLODE, listener, this, PluginListener.Priority.MEDIUM);
		log.info(name + " v" + version + " updated by " + author + " initialized.");
		
		if (!props.containsKey("heightlimit"))
		{ //Check if hightlimit is set
			props.setInt("heightlimit", 55); //If not, booya! hightlimit default is set
		}
		if (!props.containsKey("deathmessage"))
		{ //same as above
			props.setString("deathmessage", "tried to hug a creeper..");
		}
		maxAltitude = props.getInt("heightlimit"); //Set these values as variables for ease of use
		deathmessage = props.getString("deathmessage");
		listener.setAltAndDM(maxAltitude, deathmessage);
	}
	
	//Checks if players is hit by explosion - Thanks to whoever made this... :D
	public static boolean isInExplosionRadius(Player a, Block b)
	{

	    return Math.sqrt(Math.pow(a.getX() - b.getX(), 2.0D) + Math.pow(a.getY() - b.getY(), 2.0D) + Math.pow(a.getZ() - b.getZ(), 2.0D)) <= explosionRad;

	}
}