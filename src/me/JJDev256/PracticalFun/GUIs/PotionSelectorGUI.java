package me.JJDev256.PracticalFun.GUIs;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.JJDev256.PracticalFun.Main;
import me.JJDev256.PracticalFun.utils.Utils;


public class PotionSelectorGUI {

	public static Inventory inv;
	public static String name;
	public static int slots;
	private static Main plugin;
	
	public static void init(Main plugin) {
		PotionSelectorGUI.plugin = plugin;
		name = (String) plugin.getConfig().get("potion_sel_gui.name");
		slots = ((int) plugin.getConfig().get("potion_sel_gui.rows")) * 9;
		inv = Bukkit.createInventory(null, slots);
		Utils.createAndAddToInv(inv, Material.SAND, 2,  4, "Apply gravity", "");
		Utils.createAndAddToInv(inv, Material.EXPERIENCE_BOTTLE, 2, 13, "Apply antigravity", "");
	}
	
	public static Inventory GUI() {
		Inventory retinv = Bukkit.createInventory(null, slots, name);
		retinv.setContents(inv.getContents());
		return retinv;
	}

	public static void clicked(Player p, int slot, ItemStack clickeditem, Inventory inv) {
		switch (clickeditem.getItemMeta().getDisplayName()) {
			case "Apply antigravity":
				p.setGravity(false);
				p.sendMessage(Utils.chatCol("&1&lGRAVITY: &cOFF"));
			break;
			case "Apply gravity":
				p.setGravity(true);
				p.sendMessage(Utils.chatCol("&1&lGRAVITY: &aON"));
			break;
			default: break;
		}
	}
	
}
