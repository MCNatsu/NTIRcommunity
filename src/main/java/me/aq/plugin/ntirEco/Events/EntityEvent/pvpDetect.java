package me.aq.plugin.ntirEco.Events.EntityEvent;

import me.aq.plugin.ntirEco.NTIReco;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class pvpDetect implements Listener {

    private NTIReco plugin;

    @EventHandler
    public void onpvp(EntityDamageByEntityEvent e){

        plugin = NTIReco.getPlugin();

        if(e.getEntity().getType() != EntityType.PLAYER | e.getDamager().getType() != EntityType.PLAYER){
            return;
        }

        Player target = (Player) e.getEntity();

        Player attacker = (Player) e.getDamager();


        if(!plugin.data.enablePVP(target) && plugin.data.enablePVP(attacker)){
            e.setCancelled(true);
            attacker.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.DARK_RED  + "對方關閉了pvp!"));
        }

        if(!plugin.data.enablePVP(attacker) && !plugin.data.enablePVP(target)){
            e.setCancelled(true);
            attacker.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.DARK_RED  + "你和對方都關閉了pvp!"));
        }

        if(!plugin.data.enablePVP(attacker) && plugin.data.enablePVP(target)){
            e.setCancelled(true);
            attacker.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.DARK_RED  + "你關閉了pvp!"));
        }
    }
}
