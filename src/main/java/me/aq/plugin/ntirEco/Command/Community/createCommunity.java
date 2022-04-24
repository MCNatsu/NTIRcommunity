package me.aq.plugin.ntirEco.Command.Community;

import me.aq.plugin.ntirEco.NTIReco;
import me.aq.plugin.ntirEco.utils.Item.CustomItem;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class createCommunity implements CommandExecutor {

    private NTIReco plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        plugin = NTIReco.getPlugin();
        Player p = (Player) sender;
        String community = args[0];

        if(args.length == 1){
            if(!plugin.data.Communityexists(community)){
                if(plugin.data.getCommunity(p) != null){
                    p.sendMessage(ChatColor.RED + "你已經在一個社區中了!");
                    return false;
                }
                plugin.data.createCommunity(p, community);

                p.sendMessage(ChatColor.GREEN + "你已成功創建社區");
                p.getInventory().addItem(CustomItem.selector);

            }else {
                p.sendMessage(ChatColor.RED + "該社區名稱已被使用");
            }
        }

        return false;
    }
}
