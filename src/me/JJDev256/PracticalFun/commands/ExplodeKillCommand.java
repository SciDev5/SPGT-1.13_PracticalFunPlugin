package me.JJDev256.PracticalFun.commands;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.util.Vector;

import me.JJDev256.PracticalFun.Main;
import me.JJDev256.PracticalFun.utils.Utils;

public class ExplodeKillCommand implements CommandExecutor {

	private Main plugin;
	
	public ExplodeKillCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("explodekill").setExecutor(this);

	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			Player senderP = (Player) sender;
			if (!senderP.hasPermission("explodekillcommand.use")) {
				senderP.sendMessage(Utils.chatCol(((String) plugin.getConfig().get("general.no_permission"))));
				return true;
			}
		}
		
		if (args.length != 2) {
			sender.sendMessage(Utils.chatCol(((String) plugin.getConfig().get("general.num_args_err")).replaceAll("<argslen>", ""+args.length).replaceAll("<reqlen>", "2").replaceAll("<cmd>", label)));
			return false;
		}
		
		float x = Utils.parsable(args[1]) ? Float.parseFloat(args[1]) : Float.NaN;

		if (Float.isNaN(x)) {
			sender.sendMessage(Utils.chatCol(((String) plugin.getConfig().get("general.type_err")).replaceAll("<arg>", args[1]).replaceAll("<typ>", "number").replaceAll("<cmd>", label)));
			return false;
		}

		Player target = Bukkit.getServer().getPlayer(args[0]);
    
		if (target != null) {
			ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
			String command = "summon firework_rocket "+target.getLocation().getX()+" "+target.getLocation().getY()+" "+target.getLocation().getZ()+" {LifeTime:0,FireworksItem:{id:firework_rocket,Count:1,tag:{Fireworks:{Flight:1,Explosions:[{Type:4,Flicker:1,Trail:1,Colors:[I;14602026,15435844],FadeColors:[I;1973019,11743532]}]}}}}";
			Bukkit.dispatchCommand(console, command);
			
			target.getWorld().createExplosion(target.getLocation(), x);
		} else {
			sender.sendMessage(Utils.chatCol(((String) plugin.getConfig().get("general.type_err")).replaceAll("<arg>", args[0]).replaceAll("<typ>", "player").replaceAll("<cmd>", label)));
		}
    
    return true;
	}
}
