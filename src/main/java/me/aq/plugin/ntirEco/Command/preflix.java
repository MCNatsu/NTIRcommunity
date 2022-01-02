package me.aq.plugin.ntirEco.Command;

import me.aq.plugin.ntirEco.NTIReco;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class preflix implements CommandExecutor {

    private NTIReco plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        this.plugin = NTIReco.getPlugin();
        Player p = (Player) sender;


        if(args[0].equalsIgnoreCase("owner") && p.hasPermission("NTIR.Owner") ){

            plugin.data.setPrefix(p.getUniqueId(), ChatColor.LIGHT_PURPLE + "[服主]");


        }else {
            p.sendMessage(ChatColor.RED + "你無法使用這個權限");
        }

        if(args.length == 2 && p.hasPermission("NTIR.Admin")) {

            Player target = Bukkit.getPlayerExact(args[0]);
            if(args[1].equalsIgnoreCase("player")){

                plugin.data.setPrefix(target.getUniqueId(), ChatColor.AQUA + "[玩家]");

            }

        }

        return false;
    }
}
