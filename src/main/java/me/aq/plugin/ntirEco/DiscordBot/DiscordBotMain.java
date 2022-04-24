package me.aq.plugin.ntirEco.DiscordBot;


import me.aq.plugin.ntirEco.NTIReco;
import net.dv8tion.jda.api.entities.Webhook;
import net.dv8tion.jda.api.entities.WebhookClient;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.IOException;

public class DiscordBotMain implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        e.setJoinMessage("");
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        e.setQuitMessage("");
    }









}
