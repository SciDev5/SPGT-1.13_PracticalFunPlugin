package me.JJDev256.PracticalFun.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import me.JJDev256.PracticalFun.Main;
import me.JJDev256.PracticalFun.utils.Utils;

public class NudgeStickClickListener implements Listener {
	
	private Main plugin;
	
	public NudgeStickClickListener(Main p) {
		plugin = p;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		ItemStack hand = e.getItem();
		
		if (!e.getPlayer().hasGravity() && hand.getItemMeta().getDisplayName().equals(Utils.chatCol((String) plugin.getConfig().get("nudge_stick.name")))) {
			//System.out.print("STICK CLICK");
			e.setCancelled(true);
			Location pl = e.getPlayer().getLocation();
			Vector facing = new Vector(0,
					-Math.sin(pl.getPitch()/180*Math.PI),
					Math.cos(pl.getPitch()/180*Math.PI));
			facing = new Vector(
					-facing.getZ()*Math.sin(pl.getYaw()/180*Math.PI),
					facing.getY(),
					facing.getZ()*Math.cos(pl.getYaw()/180*Math.PI));
			e.getPlayer().setVelocity(e.getPlayer().getVelocity().add(facing).multiply(0.4));
		}
	}
	
}
