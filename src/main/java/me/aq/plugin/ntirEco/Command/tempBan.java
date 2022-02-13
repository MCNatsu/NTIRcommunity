package me.aq.plugin.ntirEco.Command;

import me.aq.plugin.ntirEco.NTIReco;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Activity;
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
import java.util.Date;
import java.util.UUID;

public class tempBan implements CommandExecutor {

    private NTIReco plugin;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        plugin = NTIReco.getPlugin();

        Player p = (Player) sender;

        if(!p.hasPermission("NTIR.Admin")){
            p.sendMessage(ChatColor.RED + "你沒有此權限!");
            return false;
        }

        if(args.length != 3){
            p.sendMessage(ChatColor.RED + "變數不足!");
            return false;
        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(UUID.fromString( plugin.data.getuuid(args[0])));

        if(target == null){
            p.sendMessage(ChatColor.RED + "該玩家不存在或不在線上!");
            return false;
        }

        long time = 0;
        try {
            time = plugin.tempBanUtils.getLong(args[2]);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        plugin.data.TempBan(target,args[1],time);

        EmbedBuilder eb = new EmbedBuilder();
        eb.addField("事由", args[1], true);
        eb.addField("執行者:",p.getDisplayName(),true);
        eb.addField("懲處時長", args[2],false);
        eb.addField("解除封鎖日期",plugin.data.getUnBanDate(target),true);
        eb.setAuthor("玩家" + target.getName() + "已被封鎖",null,"https://crafatar.com/avatars/" + target.getUniqueId().toString());
        eb.setColor(Color.RED);
        eb.setTimestamp(null);
        eb.setFooter("若需了解為何被暫時封鎖請至#⚖申訴提問區提問");

        plugin.jda.getNewsChannelById("893709458528665621").sendMessageEmbeds(eb.build()).queue();

        eb.clear();

        return true;
    }
}
