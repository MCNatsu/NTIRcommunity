package me.aq.plugin.ntirEco.DiscordBot;

import me.aq.plugin.ntirEco.NTIReco;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyAction;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

public final class DiscordtoMinecraft extends ListenerAdapter {

    private NTIReco plugin;

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent e) {

        plugin = NTIReco.getPlugin();

        if(!e.getChannel().getId().equals(plugin.getConfig().getString("ChatChannel")))return;

        Member member = e.getMember();

        if(member == null || member.getUser().isBot())return;

        String message = e.getMessage().getContentDisplay();



        Bukkit.broadcastMessage("[" + ChatColor.AQUA + "Discord" + ChatColor.RESET + "]" + member.getEffectiveName() + ChatColor.RESET + "：" + message);

    }
}
