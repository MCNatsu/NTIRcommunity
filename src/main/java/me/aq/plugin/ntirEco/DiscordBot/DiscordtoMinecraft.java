package me.aq.plugin.ntirEco.DiscordBot;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

public final class DiscordtoMinecraft extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent e) {

        if(!e.getChannel().getId().equals("893472407485038622")) return;

        Member member = e.getMember();

        if(member == null || member.getUser().isBot())return;

        String message = e.getMessage().getContentDisplay();

        Bukkit.broadcastMessage("[" + ChatColor.AQUA + "Discord" + ChatColor.RESET + "]" + member.getEffectiveName() + ChatColor.RESET + "：" + message);



    }
}
