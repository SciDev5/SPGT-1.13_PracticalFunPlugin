package me.JJDev256.PracticalFun.commands;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.util.Vector;

import me.JJDev256.PracticalFun.Main;
import me.JJDev256.PracticalFun.utils.Utils;

public class GetNudgeStickCommand implements CommandExecutor {

	
	private Main plugin;
	
	public GetNudgeStickCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("getnudgestick").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player senderP = null;
		if (sender instanceof Player) {
			senderP = (Player) sender;
			if (!senderP.hasPermission("getnudgestick.use")) {
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
		
		senderP.getInventory().addItem(Utils.createItemStack(Material.STICK, 1, Utils.chatCol((String) plugin.getConfig().get("nudge_stick.name")), "Pow!!!"));
		
		return true;
	}
	
	
}
