package me.aq.plugin.ntirEco.Command.ServerManagement;

import me.aq.plugin.ntirEco.utils.Item.CustomItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class getItem implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player p = (Player) sender;

        if(!p.isOp()){
            return false;
        }

        p.getInventory().addItem(CustomItem.selector);
        return true;
    }
}
