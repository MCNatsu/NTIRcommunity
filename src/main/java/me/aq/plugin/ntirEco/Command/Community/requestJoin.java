package me.aq.plugin.ntirEco.Command.Community;

import me.aq.plugin.ntirEco.NTIReco;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.C;
import org.jetbrains.annotations.NotNull;

public class requestJoin implements CommandExecutor {

    private NTIReco plugin;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        plugin = NTIReco.getPlugin();
        Player p = (Player) sender;
        String community = args[0];
        if(plugin.data.getCommunity(p) != null){
            p.sendMessage(ChatColor.RED + "你已經在一個社區中了!");
            return false;
        }

        if(plugin.data.haveRequest(p)){
            p.sendMessage(ChatColor.RED + "你有待同意的請求!");
            return false;
        }

        if(!plugin.data.existsCommunity(community)){
            p.sendMessage(ChatColor.RED + "該社區不存在");
            return false;
        }

        plugin.data.JoinRequest(p,community);
        p.sendMessage(ChatColor.GREEN + "你已成功發送加入請求至社區-" + ChatColor.LIGHT_PURPLE + community + ChatColor.GREEN + "請稍後回覆!");
        p.sendMessage(ChatColor.GRAY + "可使用/rcancel取消請求");
        return true;
    }
}
