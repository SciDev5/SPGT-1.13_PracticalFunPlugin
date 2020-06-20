package me.JJDev256.PracticalFun.commands;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
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

public class FlareCommand implements CommandExecutor {

	
	private Main plugin;
	
	public FlareCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("flare").setExecutor(this);
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
		
		
		
		if (args.length != 1) {
			sender.sendMessage(Utils.chatCol(((String) plugin.getConfig().get("general.num_args_err")).replaceAll("<argslen>", ""+args.length).replaceAll("<reqlen>", "1").replaceAll("<cmd>", label)));
			return false;
		}
		
		Color col;
		
		switch (args[0]) {
			case "aqua": col = Color.AQUA; break;
			case "black": col = Color.BLACK; break;
			case "blue": col = Color.BLUE; break;
			case "magenta": col = Color.FUCHSIA; break;
			case "gray": col = Color.GRAY; break;
			case "green": col = Color.GREEN; break;
			case "lime": col = Color.LIME; break;
			case "dark_red": col = Color.MAROON; break;
			case "maroon": col = Color.MAROON; break;
			case "dark_blue": col = Color.NAVY; break;
			case "olive": col = Color.OLIVE; break;
			case "gold": col = Color.ORANGE; break;
			case "purple": col = Color.PURPLE; break;
			case "red": col = Color.RED; break;
			case "light_gray": col = Color.SILVER; break;
			case "dark_aqua": col = Color.TEAL; break;
			case "white": col = Color.WHITE; break;
			case "yellow": col = Color.YELLOW; break;
			default: 
				sender.sendMessage(Utils.chatCol(((String) plugin.getConfig().get("general.type_err")).replaceAll("<arg>", args[0]).replaceAll("<typ>", "color").replaceAll("<cmd>", label)));
				return false;
		}
		
		
		Firework fw = (Firework) senderP.getWorld().spawn(senderP.getLocation().add(new Vector(0,1.5,0)), Firework.class);
		FireworkMeta fwm = fw.getFireworkMeta();
		
		fwm.addEffect(FireworkEffect.builder()
				.trail(true)
				.flicker(true)
				.with(FireworkEffect.Type.BALL_LARGE)
				.withColor(col)
				.build()
			);
		
		fwm.setPower(3);
		
		fw.setFireworkMeta(fwm);
		
		senderP.getWorld().playSound(senderP.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1, 3);
		
		return true;
	}
	
	
}
