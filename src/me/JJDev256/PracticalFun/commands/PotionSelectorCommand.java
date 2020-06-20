package me.JJDev256.PracticalFun.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.JJDev256.PracticalFun.Main;
import me.JJDev256.PracticalFun.GUIs.PotionSelectorGUI;
import me.JJDev256.PracticalFun.utils.Utils;

public class PotionSelectorCommand implements CommandExecutor {

	private Main plugin;
	
	public PotionSelectorCommand(Main p) {
		plugin = p;
		plugin.getCommand("selectpotion").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player senderP = null;
		if (sender instanceof Player) {
			senderP = (Player) sender;
			if (!senderP.hasPermission("flarecommand.use")) {
				senderP.sendMessage(Utils.chatCol(((String) plugin.getConfig().get("general.no_permission"))));
				return true;
			}
		} else {
			sender.sendMessage(Utils.chatCol(((String) plugin.getConfig().get("general.no_console"))));
			return true;
		}
		
		senderP.openInventory(PotionSelectorGUI.GUI());
		
		return true;
	}
}
