package me.JJDev256.PracticalFun.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import me.JJDev256.PracticalFun.Main;
import me.JJDev256.PracticalFun.utils.Utils;

public class LaunchCommand implements CommandExecutor {

	private Main plugin;
	
	public LaunchCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("launch").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			Player senderP = (Player) sender;
			if (!senderP.hasPermission("launchcommand.use")) {
				senderP.sendMessage(Utils.chatCol(((String) plugin.getConfig().get("general.no_permission"))));
				return true;
			}
		}
		
		if (args.length != 4) {
			sender.sendMessage(Utils.chatCol(((String) plugin.getConfig().get("general.num_args_err")).replaceAll("<argslen>", ""+args.length).replaceAll("<reqlen>", "4").replaceAll("<cmd>", label)));
            return false;
        }

		float x = Utils.parsable(args[1]) ? Float.parseFloat(args[1]) : Float.NaN;
		float y = Utils.parsable(args[2]) ? Float.parseFloat(args[2]) : Float.NaN;
		float z = Utils.parsable(args[3]) ? Float.parseFloat(args[3]) : Float.NaN;

		if (Float.isNaN(x)) {
			sender.sendMessage(Utils.chatCol(((String) plugin.getConfig().get("general.type_err")).replaceAll("<arg>", args[1]).replaceAll("<typ>", "number").replaceAll("<cmd>", label)));
			return false;
		}
		if (Float.isNaN(y)) {
			sender.sendMessage(Utils.chatCol(((String) plugin.getConfig().get("general.type_err")).replaceAll("<arg>", args[2]).replaceAll("<typ>", "number").replaceAll("<cmd>", label)));
			return false;
		}
		if (Float.isNaN(z)) {
			sender.sendMessage(Utils.chatCol(((String) plugin.getConfig().get("general.type_err")).replaceAll("<arg>", args[3]).replaceAll("<typ>", "number").replaceAll("<cmd>", label)));
			return false;
		}
		
        Player target = Bukkit.getServer().getPlayer(args[0]);
        
        if (target != null) {
        	target.setVelocity(target.getVelocity().add(new Vector(x, y, z)));
        } else {
			sender.sendMessage(Utils.chatCol(((String) plugin.getConfig().get("general.type_err")).replaceAll("<arg>", args[0]).replaceAll("<typ>", "player").replaceAll("<cmd>", label)));
        }
        
        return true;
	}
	
}
