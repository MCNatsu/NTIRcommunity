package me.aq.plugin.ntirEco.Command;

import me.aq.plugin.ntirEco.NTIReco;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setCommunity implements CommandExecutor {

    private NTIReco plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        plugin = NTIReco.getPlugin();

        Player p = (Player) sender;
        Player target = Bukkit.getPlayerExact(args[0]);
        String community = args[1];

        if(p.hasPermission("NTIR.CommunityAdmin")) {

            if (args.length == 2) {

                if(plugin.data.Communityexists(community)) {


                    plugin.data.setCommunity(target.getUniqueId(), community);
                    p.sendMessage(ChatColor.GREEN + "你已成功將玩家" + ChatColor.AQUA + target.getDisplayName() + ChatColor.GREEN + "加入社區-" + ChatColor.LIGHT_PURPLE + community);
                    target.sendMessage(ChatColor.AQUA + "玩家" + ChatColor.LIGHT_PURPLE + p.getDisplayName() + ChatColor.GREEN + "將你加入社區-" + ChatColor.LIGHT_PURPLE + community);

                }else {
                    p.sendMessage(ChatColor.RED + "該社區不存在!");
                }
            }
        }


        return false;
    }
}
