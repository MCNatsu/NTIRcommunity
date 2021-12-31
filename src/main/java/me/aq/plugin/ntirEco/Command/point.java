package me.aq.plugin.ntirEco.Command;

import me.aq.plugin.ntirEco.NTIReco;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class point implements CommandExecutor {

    private NTIReco plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        this.plugin = NTIReco.getPlugin();

        if(args.length == 0){

            Player p = (Player) sender;
            UUID uuid = p.getUniqueId();

            int points = plugin.data.getPoints(uuid);
            p.sendMessage(ChatColor.GREEN + "你的NTIR點數剩餘:" +ChatColor.LIGHT_PURPLE +  points + ChatColor.GREEN + "點");

        }

        return false;
    }
}
