package me.aq.plugin.ntirEco.Events;

import me.aq.plugin.ntirEco.NTIReco;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

public class Chat implements Listener {

    private NTIReco plugin;

    @EventHandler
    public void chat(AsyncPlayerChatEvent e){

        this.plugin = NTIReco.getPlugin();
        Player p = e.getPlayer();

        String format = plugin.data.getPrefix(p.getUniqueId());





        if(format!= null) {

            e.setFormat(format + ChatColor.WHITE + p.getName() + "：" + e.getMessage());

        }

    }
}
