package me.JJDev256.PracticalFun.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.tags.CustomItemTagContainer;
import org.bukkit.inventory.meta.tags.ItemTagType;
import org.bukkit.material.MaterialData;

public class Utils {
	public static String chatCol (String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
	public static Boolean parsable (String s) {
		Boolean isParsable = true;
		for (char c : s.toCharArray()) {
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
		return isParsable;
	}
	
	public static ItemStack createAndAddToInv(Inventory inv, Material material, int amt, int invslot, String displayName, String... loreString) {
		ItemStack items;
		List<String> lore = new ArrayList();
		
		
		items = new ItemStack(material, amt);
		
		ItemMeta im = items.getItemMeta();
		im.setDisplayName(displayName);
		
		for (String s : loreString) {
			lore.add(s);
		}
		
		
		im.setLore(lore);
		
		items.setItemMeta(im);
		
		
		inv.setItem(invslot, items);
		
		return items;
		
	}
	
	public static ItemStack createItemStack(Material material, int amt, String displayName, String... loreString) {
		ItemStack items;
		List<String> lore = new ArrayList();
		
		
		items = new ItemStack(material, amt);
		
		ItemMeta im = items.getItemMeta();
		im.setDisplayName(displayName);
		
		for (String s : loreString) {
			lore.add(s);
		}
		
		
		im.setLore(lore);
		
		items.setItemMeta(im);
		
		
		return items;
		
	}
	
}
