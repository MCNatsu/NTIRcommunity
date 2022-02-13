package me.aq.plugin.ntirEco.Command;

import me.aq.plugin.ntirEco.Events.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class msg implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        Player p = (Player) sender;

        if(args.length < 2){
            p.sendMessage(ChatColor.RED + "請輸入要發送的對象和訊息!");
            return false;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        String msg = args[1];

        if(target == null){
            p.sendMessage(ChatColor.RED + "該玩家不存在或不在線上");
            return false;
        }


        target.playSound(target, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 5,1);
        target.sendMessage("[" + ChatColor.YELLOW + p.getDisplayName() + ChatColor.RESET + "]" + ChatColor.GREEN + "->"
                + ChatColor.RESET + "[" + ChatColor.AQUA + "你" + ChatColor.RESET + "] " + msg );
        p.sendMessage("[" + ChatColor.YELLOW + "你" + ChatColor.RESET + "]" + ChatColor.GREEN + "->"
                + ChatColor.RESET + "[" + ChatColor.AQUA + target.getDisplayName() + ChatColor.RESET + "] " + msg);

        return true;
    }
}
