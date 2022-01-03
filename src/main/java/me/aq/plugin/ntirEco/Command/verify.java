package me.aq.plugin.ntirEco.Command;

import me.aq.plugin.ntirEco.NTIReco;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class verify implements CommandExecutor {

    private NTIReco plugin;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        plugin = NTIReco.getPlugin();

        Player p = (Player) sender;

        if(args.length > 1){
            p.sendMessage(ChatColor.RED + "請輸入你的驗證碼");
            return false;
        }

        String verifyCode = plugin.data.getVerifyCode(p.getUniqueId());

        if(args.length == 0) {

            if (verifyCode != null && !plugin.data.verified(p.getUniqueId())) {

                p.sendMessage(ChatColor.GREEN + "你的驗證碼為:" + ChatColor.LIGHT_PURPLE + verifyCode);

            }else if(verifyCode == null){
                p.sendMessage((ChatColor.RED + "你尚未取得驗證碼! 請至dc輸入!link取得驗證碼"));
            }else if(plugin.data.verified(p.getUniqueId())){
                p.sendMessage(ChatColor.AQUA + "你已經連結完成了!");
            }

            if(args.length == 1){
                if(args[0].equals(verifyCode)  && !plugin.data.verified(p.getUniqueId())){

                    plugin.data.verify2(p, true);
                    p.sendMessage(ChatColor.GREEN + "成功連結DC帳號");

                }
            }
        }

        return false;
    }
}
