package me.aq.plugin.ntirEco.Command.Community;

import me.aq.plugin.ntirEco.NTIReco;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class allow implements CommandExecutor {

    private NTIReco plugin;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        plugin = NTIReco.getPlugin();

        Player p = (Player) sender;
        OfflinePlayer requester = Bukkit.getOfflinePlayer(args[0]);

        if(plugin.data.getCommunity(p) == null){
            p.sendMessage(ChatColor.RED + "你似乎還沒有加入社區呢!" + ChatColor.GRAY + "(沒有加入社區不能執行此操作喔!)");
            return false;
        }

        if(!plugin.data.getPost(p,plugin.data.getCommunity(p)).contains("Owner")){
            p.sendMessage(ChatColor.RED + "你似乎沒有權限在社區中這麼做!" + ChatColor.AQUA + "(請你的詢問社區管理員!)");
            return false;
        }

        if(!plugin.data.getRequestedCommunity(requester).equals(plugin.data.getCommunity(p)) ){
            p.sendMessage(ChatColor.RED + "該玩家沒有請求加入你的社區!");
            return false;
        }

        plugin.data.DelJoinRequest(requester);
        plugin.getPermissions().playerAdd(p,"NTIR.CommunityAdmin");
        Bukkit.dispatchCommand(p,"setcommunity " + requester.getName() + " " + plugin.data.getCommunity(p));
        plugin.getPermissions().playerRemove(p,"NTIR.CommunityAdmin");

        return true;
    }
}
