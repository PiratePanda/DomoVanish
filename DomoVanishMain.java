package com.beastmc.astrocraft;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class DomoVanishMain extends JavaPlugin implements Listener{
	public static DomoVanishMain plugin;
	
	public void onEnable(){
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}//onEnable
	
	private ArrayList<Player> vanished = new ArrayList<Player>();
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		Player player = (Player) sender;
		
		if(!(sender instanceof Player)){
			sender.sendMessage("§4" + "" + "You cannot vanish!");
			return true;
		}
		
		
		if(cmd.getName().equalsIgnoreCase("vanish")){
			if(player.hasPermission("domovanish.vanish") || sender.isOp()){
				
				if(!vanished.contains(player)){
					for(Player pl : Bukkit.getServer().getOnlinePlayers()){
						pl.hidePlayer(player);
					}//vanishAction
					
					vanished.add(player);
					player.sendMessage("§2" + "" + "You are now Vanished!");
					return true;
					
				}//vanishToggle
				
				else{
					for(Player pl : Bukkit.getServer().getOnlinePlayers()){
						pl.showPlayer(player);
					}//vanishAction
					
					vanished.remove(player);
					player.sendMessage("§4" + "" + "You are now unvanished!");
					return true;
					
				}//else
				
			}//permission
				
		}//commandLabel
		
		if(cmd.getName().equalsIgnoreCase("showvanished")){
			if(player.hasPermission("domovanish.showvanished") || sender.isOp()){
				for(Player v : vanished){
					player.showPlayer(v);
				}//for statement
			}//permission
		}//showvanshed
		
		if(cmd.getName().equalsIgnoreCase("checkv")){
			if(player.hasPermission("domovanish.checkv") || sender.isOp()){
				if(vanished.contains(player)){
					player.sendMessage("§2" + "" + "You are vanished!");
					return true;
				}
				
				else{
					player.sendMessage("§4" + "" + "You are NOT vanished!");
					return true;
				}//else
				
			}//permission
		}//checkv
		return false;
		
	}//public boolean
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
			if(e.getPlayer().hasPermission("domovanish.joinvanished") || e.getPlayer().isOp()){
				for(Player pl : Bukkit.getServer().getOnlinePlayers()){
					pl.hidePlayer(e.getPlayer());
					vanished.add(e.getPlayer());
					e.getPlayer().sendMessage("§4§l" + "" + "You have joined vanished! Do /vanish to unvanish!");
				}
			}
	}//player join
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e){
		vanished.remove(e.getPlayer());
	}//player leave
	
}//end class

/*
 * Made by Cambrianite
 * Adaptation from PogoStick29Dev's video
 * "Bukkit Coding ~ Episode 40: Vanish"
 */
