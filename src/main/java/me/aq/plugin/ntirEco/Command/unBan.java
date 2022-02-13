package me.aq.plugin.ntirEco.Command;

import me.aq.plugin.ntirEco.NTIReco;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class unBan implements CommandExecutor {
    private NTIReco plugin;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        plugin = NTIReco.getPlugin();

        Player p = (Player) sender;

        if (!p.hasPermission("NTIR.Admin")) {
            p.sendMessage(ChatColor.RED + "你沒有此權限!");
            return false;
        }

        if (args.length != 1) {
            p.sendMessage(ChatColor.RED + "請輸入你要解除封鎖的玩家!");
            return false;
        }

        Player target = (Player) Bukkit.getOfflinePlayer(args[0]);

        if(target == null){
            p.sendMessage(ChatColor.RED + "該玩家並不存在!");
            return false;
        }

        if(!plugin.data.tempBanned(target)){
            p.sendMessage(ChatColor.RED + "該玩家沒被封鎖!");
            return false;
        }

        plugin.data.unBan(target);
        p.sendMessage(ChatColor.YELLOW + "你已成功解封該玩家!");

        return true;
    }
}
