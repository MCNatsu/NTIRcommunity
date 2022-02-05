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

    private NTIReco plugin;

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) throws IOException {
        plugin = NTIReco.getPlugin();

        DiscordWebhook webhook = new DiscordWebhook("https://discordapp.com/api/webhooks/939373174448095262/bk5Aayk1Mc2My-yCjbzmv4iqm1PegwdpTMSLWNEGKgT_mQi-Rv1ItTUEwn0Q0FUzoovy");
        webhook.setContent(e.getMessage());
        webhook.setAvatarUrl("https://crafatar.com/avatars/" + e.getPlayer().getUniqueId().toString());
        webhook.setUsername(e.getPlayer().getDisplayName());
        webhook.setTts(false);
        webhook.execute();

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        e.setJoinMessage("");
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        e.setQuitMessage("");
    }









}
