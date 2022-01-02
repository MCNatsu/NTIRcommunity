package me.aq.plugin.ntirEco.Command;

import me.aq.plugin.ntirEco.NTIReco;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class admin implements CommandExecutor {

    private NTIReco plugin;


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        this.plugin = NTIReco.getPlugin();

        Player p = (Player) sender;
        Player target = Bukkit.getPlayerExact(args[1]);
        assert target != null;
        UUID uuid = target.getUniqueId();
        int points = Integer.parseInt(args[2]);

        plugin.data.addpoint(uuid, points);


        int CURpoint = plugin.data.getPoints(uuid);

        if(p.hasPermission("NTIR.admin")){
            if (args[0].equals("add")) {





                p.sendMessage(ChatColor.GREEN + "你已經成功將玩家 " + ChatColor.AQUA + target.getDisplayName() + ChatColor.GREEN + "的NTIR點數更變為" +ChatColor.LIGHT_PURPLE + CURpoint + ChatColor.GREEN + "點");

                target.sendMessage(ChatColor.RED + p.getDisplayName() + ChatColor.LIGHT_PURPLE + "已經將你的NTIR點數更變為" + ChatColor.GOLD + CURpoint + ChatColor.LIGHT_PURPLE + "點" );

                return true;
            }

            if(args[0].equalsIgnoreCase("set")){

                plugin.data.setPoint(uuid, points);
                p.sendMessage(ChatColor.GREEN + "你已經成功將玩家 " + ChatColor.AQUA + target.getDisplayName() + ChatColor.GREEN + "的NTIR點數更變為" + ChatColor.LIGHT_PURPLE + points + ChatColor.GREEN + "點");
                target.sendMessage(ChatColor.RED + p.getDisplayName() + ChatColor.LIGHT_PURPLE + "已經將你的NTIR點數更變為" + ChatColor.GOLD + points + ChatColor.LIGHT_PURPLE + "點" );

                return true;

            }

        }

        return false;
    }
}
