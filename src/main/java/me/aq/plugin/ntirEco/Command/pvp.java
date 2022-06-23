package me.aq.plugin.ntirEco.Command;

import me.aq.plugin.ntirEco.NTIReco;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class pvp implements CommandExecutor {

    private NTIReco plugin;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        plugin = NTIReco.getPlugin();

        Player p = (Player) sender;

        if(args.length != 0){
            p.sendMessage(ChatColor.RED + "變數過多!");
            return false;
        }

        if(plugin.data.enablePVP(p)){
            plugin.data.setpvp(p, false);
            p.sendMessage(ChatColor.GREEN + "你已將pvp關閉");
            return true;
        }

        if(!plugin.data.enablePVP(p)){
            plugin.data.setpvp(p,true);
            p.sendMessage(ChatColor.GREEN + "你已將pvp開啟");
            return true;
        }

        return false;
    }
}
