package me.aq.plugin.ntirEco.DiscordBot;

import me.aq.plugin.ntirEco.NTIReco;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.WidgetUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;


import javax.security.auth.login.LoginException;

public class DiscordBotMain implements Listener {

    private NTIReco plugin;

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        plugin = NTIReco.getPlugin();
        String contant = ":arrow_right: " + "**" + e.getPlayer().getDisplayName() +"**" + "加入了伺服器";


        plugin.jda.getTextChannelById("893472407485038622").sendMessage(contant).queue();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){

        plugin = NTIReco.getPlugin();
        String contant = ":arrow_left: " + "**" + e.getPlayer().getDisplayName() + "**" + "離開了伺服器";


        plugin.jda.getTextChannelById("893472407485038622").sendMessage(contant).queue();
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        plugin = NTIReco.getPlugin();

        String message = e.getPlayer().getDisplayName() + ">>" + e.getMessage();

        plugin.jda.getTextChannelById("893472407485038622").sendMessage(message).queue();

    }







}
