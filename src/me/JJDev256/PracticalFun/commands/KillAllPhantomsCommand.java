package me.JJDev256.PracticalFun.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;

import me.JJDev256.PracticalFun.Main;
import me.JJDev256.PracticalFun.utils.Utils;

public class KillAllPhantomsCommand implements CommandExecutor {

	private Main plugin;
	
	public KillAllPhantomsCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("killallphantoms").setExecutor(this);

	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player senderP = null;
		
		if (sender instanceof Player) {
			senderP = (Player) sender;
			if (!senderP.hasPermission("killallphantoms.use")) {
				senderP.sendMessage(Utils.chatCol(((String) plugin.getConfig().get("general.no_permission"))));
				return true;
			}
		} else {
			sender.sendMessage(Utils.chatCol(((String) plugin.getConfig().get("general.no_console"))));
			return true;
		}
		
		if (args.length != 0) {
			sender.sendMessage(Utils.chatCol(((String) plugin.getConfig().get("general.num_args_err")).replaceAll("<argslen>", ""+args.length).replaceAll("<reqlen>", "0").replaceAll("<cmd>", label)));
			return false;
		}
		
		Object[] targets = senderP.getWorld().getEntitiesByClass(Phantom.class).toArray();
    
		for (Object _target : targets) {
			Phantom target = (Phantom) _target;
			ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
			String command = "summon firework_rocket "+target.getLocation().getX()+" "+target.getLocation().getY()+" "+target.getLocation().getZ()+" {LifeTime:0,FireworksItem:{id:firework_rocket,Count:1,tag:{Fireworks:{Flight:1,Explosions:[{Type:0,Flicker:1,Trail:0,Colors:[I;11743532,2437522,4312372,14602026,12801229,15435844]}]}}}}";
			target.getWorld().playSound(target.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1f, 0.7f);
			Bukkit.dispatchCommand(console, command);
			
			target.setHealth(0);
		}
    
    return true;
	}
}
