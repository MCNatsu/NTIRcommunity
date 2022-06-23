package me.aq.plugin.ntirEco.Command.ServerManagement;

import me.aq.plugin.ntirEco.NTIReco;
import net.dv8tion.jda.api.EmbedBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.text.ParseException;
import java.util.UUID;

public class Ban implements CommandExecutor {

    private NTIReco plugin;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        plugin = NTIReco.getPlugin();

        Player p = (Player) sender;

        if(!p.hasPermission("NTIR.Admin")){
            p.sendMessage(ChatColor.RED + "你沒有此權限!");
            return false;
        }

        if(args.length != 2){
            p.sendMessage(ChatColor.RED + "變數不足!");
            return false;
        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

        if(target == null){
            p.sendMessage(ChatColor.RED + "該玩家不存在或不在線上!");
            return false;
        }


        plugin.data.Ban(target,args[1]);

        EmbedBuilder eb = new EmbedBuilder();
        eb.addField("事由", args[1], true);
        eb.addField("執行者:",p.getDisplayName(),true);
        eb.addField("懲處時長", "永久",false);
        eb.setAuthor("玩家" + target.getName() + "已被封鎖",null,"https://crafatar.com/avatars/" + target.getUniqueId().toString());
        eb.setColor(Color.RED);
        eb.setTimestamp(null);
        eb.setFooter("若需了解為何被暫時封鎖請至#⚖申訴提問區提問");

        plugin.jda.getNewsChannelById("893709458528665621").sendMessageEmbeds(eb.build()).queue();

        eb.clear();

        return true;
    }
}
