package me.JJDev256.PracticalFun.commands;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.JJDev256.PracticalFun.Main;
import me.JJDev256.PracticalFun.utils.Utils;

public class HelloCommand implements CommandExecutor {
	
	private Main plugin;
	
	public HelloCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("testcommand").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(Utils.chatCol("&4no"));
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("testcommand.use")) {
			p.sendMessage(Utils.chatCol("&cYou don't have permission to use this command!"));
			return true;
		}
		String argsS = "&d"+(args.length > 0? args[0] : "");
		for (int i = 1; i < args.length; i++) {
			String arg = args[i];
			String str = "";
			Boolean isParsable = true;
			for (char c : arg.toCharArray()) {
				switch (c) {
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
				case '-':
				case '.':
					isParsable &= true;
					break;
				default:
					isParsable = false;
					break;
				}
			}
			if (isParsable) {
				str += Float.parseFloat(arg);
				argsS = argsS + "&f, &d" + str;
			}
			
		}
		
		p.sendMessage(Utils.chatCol("&2Hello there &a" + p.getName() + "&2! &e" + label + " " + argsS));
		
		
		return true;
	}
	
}
