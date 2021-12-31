package me.aq.plugin.ntirEco.SQL;

import me.aq.plugin.ntirEco.NTIReco;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class PlayerDefault implements Listener {

    private NTIReco plugin;

    @EventHandler
    public void onJoin(PlayerJoinEvent e){

        this.plugin = NTIReco.getPlugin();
        plugin.data.createPlayer(e.getPlayer());

    }
}
