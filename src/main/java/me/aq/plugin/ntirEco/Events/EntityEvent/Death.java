package me.aq.plugin.ntirEco.Events.EntityEvent;

import me.aq.plugin.ntirEco.NTIReco;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Death implements Listener {

    private NTIReco plugin;

    @EventHandler
    public void Death(PlayerDeathEvent e){

        plugin = NTIReco.getPlugin();

        Player p = e.getEntity();
        Player killer = p.getKiller();

        if(killer == null){
            return;
        }
        if(killer.getType() == EntityType.PLAYER){
            for(Player pl : Bukkit.getOnlinePlayers()){

                if(!plugin.data.enableDeathMsg(pl)){
                    continue;
                }
                pl.sendMessage(ChatColor.RED + killer.getDisplayName() + ChatColor.LIGHT_PURPLE + "讓" + ChatColor.YELLOW + ChatColor.AQUA
                        + p.getDisplayName() + ChatColor.LIGHT_PURPLE + "登dua郎了!");

            }
        }

    }

}
