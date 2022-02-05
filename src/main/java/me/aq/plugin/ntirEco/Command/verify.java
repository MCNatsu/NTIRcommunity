package me.aq.plugin.ntirEco.Command;

import me.aq.plugin.ntirEco.NTIReco;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class verify implements CommandExecutor {

    private NTIReco plugin;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        plugin = NTIReco.getPlugin();

        Player p = (Player) sender;

        if (args.length == 0) {

            if(plugin.data.getVerifyCode(p.getUniqueId()) != null){
                p.sendMessage(ChatColor.GREEN + "你的驗證碼為:" + ChatColor.LIGHT_PURPLE + plugin.data.getVerifyCode(p.getUniqueId()) + ChatColor.AQUA + "請至dc輸入!link<驗證碼>來連結");
                return false;
            }

            if(plugin.data.verified(p.getUniqueId())){
                p.sendMessage(ChatColor.GREEN + "你已綁定過帳號了!輸入/verify info查看連結狀態");
                return false;
            }

            String code = new Random().nextInt(8000000) + 48763 + "NTIR";
            p.sendMessage(ChatColor.GREEN + "你的驗證碼為:" + ChatColor.LIGHT_PURPLE + code + ChatColor.AQUA + "請至dc輸入!link<驗證碼>來連結");
            plugin.data.verify(p,code);
            return true;
        }

        return false;
    }
}
