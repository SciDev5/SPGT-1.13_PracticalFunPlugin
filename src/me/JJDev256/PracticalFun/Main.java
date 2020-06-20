package me.JJDev256.PracticalFun;

import org.bukkit.plugin.java.JavaPlugin;

import me.JJDev256.PracticalFun.GUIs.PotionSelectorGUI;
import me.JJDev256.PracticalFun.GUIs.PotionSelectorGUIClickListener;
import me.JJDev256.PracticalFun.Listeners.NudgeStickClickListener;
import me.JJDev256.PracticalFun.commands.ExplodeKillCommand;
import me.JJDev256.PracticalFun.commands.FlareCommand;
import me.JJDev256.PracticalFun.commands.GetNudgeStickCommand;
import me.JJDev256.PracticalFun.commands.KillAllPhantomsCommand;
import me.JJDev256.PracticalFun.commands.LaunchCommand;
import me.JJDev256.PracticalFun.commands.PotionSelectorCommand;

public class Main extends JavaPlugin{
	
	@Override
	public void onEnable() {
		System.out.print("[JP1] JoeyPlugin1 booting up!");
		
		saveDefaultConfig();
		
		///////////////// COMMANDS //////////////////
		//new HelloCommand(this);
		new LaunchCommand(this);
		new ExplodeKillCommand(this);
		new FlareCommand(this);
		new KillAllPhantomsCommand(this);
		new PotionSelectorCommand(this);
		new GetNudgeStickCommand(this);
		
		//////////////// LISTENERS //////////////////
		new PotionSelectorGUIClickListener(this);
		new NudgeStickClickListener(this);
		
		////////////////// GUIS /////////////////////
		PotionSelectorGUI.init(this);
		
		
		getConfig().get("general.num_args_err");
	}
	
}
