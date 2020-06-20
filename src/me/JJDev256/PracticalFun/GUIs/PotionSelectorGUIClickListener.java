package me.JJDev256.PracticalFun.GUIs;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.JJDev256.PracticalFun.Main;
import me.JJDev256.PracticalFun.utils.Utils;

public class PotionSelectorGUIClickListener implements Listener {
	
	private Main plugin;
	
	public PotionSelectorGUIClickListener(Main p) {
		plugin = p;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {	
		String invName = e.getInventory().getName();
		if (invName.equals(PotionSelectorGUI.name)) {
			e.setCancelled(true);
			PotionSelectorGUI.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
		}
	}
	
}
