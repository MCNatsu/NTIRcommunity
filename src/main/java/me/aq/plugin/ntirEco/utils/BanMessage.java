package me.aq.plugin.ntirEco.utils;

import org.bukkit.ChatColor;

public class BanMessage {
    public String banMessage(String reason){

        String msg = ChatColor.RED + "你已被永久封鎖!\n\n" + ChatColor.LIGHT_PURPLE + "原因: " + ChatColor.GREEN + reason + "\n"
                + ChatColor.GOLD + "如你覺得這是誤判或是你想知道如何解除封鎖...請至DC申訴\n" +
                ChatColor.AQUA + "DC連結: " + ChatColor.RED + "https://discord.gg/bv5emFs4eM";
        return msg;
    }
}
