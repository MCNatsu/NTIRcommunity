package me.aq.plugin.ntirEco.Command;

import me.aq.plugin.ntirEco.NTIReco;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class ShopMenu implements CommandExecutor {

    private NTIReco plugin;
    public Inventory menu;
    public Inventory prefix;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        this.plugin = NTIReco.getPlugin();
        Player p = (Player) sender;

        if(args.length == 0){


            int point = plugin.data.getPoints(p.getUniqueId());
            String format = plugin.data.getPrefix(p.getUniqueId());

            menu = Bukkit.createInventory(p, 27, ChatColor.GOLD + "NTIR" + ChatColor.LIGHT_PURPLE + "官方商店選單");

            ItemStack playerINFO = new ItemStack(Material.BIRCH_SIGN);
            ItemStack prefix = new ItemStack(Material.NAME_TAG);
            ItemStack eco = new ItemStack(Material.GOLD_BLOCK);

            ItemMeta INFO = playerINFO.getItemMeta();
            ItemMeta preinfo = prefix.getItemMeta();
            ItemMeta ecoMeta = eco.getItemMeta();

            INFO.setDisplayName(ChatColor.LIGHT_PURPLE + p.getDisplayName());
            ArrayList<String> INFOlore = new ArrayList<>();
            INFOlore.add(ChatColor.GREEN + "NTIR點數餘額:" +ChatColor.GOLD + point );
            INFOlore.add(ChatColor.YELLOW + "金錢餘額" + ChatColor.GREEN + plugin.getEconomy().getBalance(p));
            INFOlore.add(ChatColor.AQUA + "當前稱號:" + format);
            INFO.setLore(INFOlore);
            playerINFO.setItemMeta(INFO);

            preinfo.setDisplayName(ChatColor.LIGHT_PURPLE + "稱號設置");
            prefix.setItemMeta(preinfo);

            ecoMeta.setDisplayName(ChatColor.GREEN + "經濟服幫助");
            eco.setItemMeta(ecoMeta);

            menu.setItem(0,prefix);
            menu.setItem(13, playerINFO);
            menu.setItem(26,eco);



            p.openInventory(menu);
            p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1,1);

            return true;

        }

        if(args.length == 1){

            if(args[0].equalsIgnoreCase("prefix")){

                prefix = Bukkit.createInventory(p, 27, ChatColor.LIGHT_PURPLE + "稱號設置與購買");

                ItemStack back = new ItemStack(Material.BARRIER);
                ItemStack owner = new ItemStack(Material.TOTEM_OF_UNDYING);
                ItemStack player = new ItemStack(Material.NAME_TAG);
                ItemStack daddy = new ItemStack(Material.GOLD_BLOCK);
                ItemStack sponser = new ItemStack(Material.EMERALD_BLOCK);
                ItemStack tester = new ItemStack(Material.BEDROCK);

                ItemMeta backmeta = back.getItemMeta();
                ItemMeta ownermeta = owner.getItemMeta();
                ItemMeta playermeta = player.getItemMeta();
                ItemMeta daddymeta = daddy.getItemMeta();
                ItemMeta sponserMeta = sponser.getItemMeta();
                ItemMeta testerMeta = tester.getItemMeta();

                backmeta.setDisplayName(ChatColor.GRAY + "回上頁");
                back.setItemMeta(backmeta);

                ownermeta.setDisplayName(ChatColor.GRAY + "稱號:" + ChatColor.LIGHT_PURPLE + "[服主]");
                ArrayList<String> ownerLore = new ArrayList<>();
                ownerLore.add(ChatColor.AQUA + "NTIR服主專用稱號");
                ownermeta.setLore(ownerLore);
                owner.setItemMeta(ownermeta);

                playermeta.setDisplayName(ChatColor.GRAY + "稱號:" + ChatColor.AQUA + "[玩家]");
                ArrayList<String> playerLore = new ArrayList<>();
                playerLore.add(ChatColor.AQUA + "NTIR一般玩家稱號");
                playermeta.setLore(playerLore);
                player.setItemMeta(playermeta);

                daddymeta.setDisplayName(ChatColor.GRAY + "稱號:" + ChatColor.GREEN + "[乾爹]");
                ArrayList<String> daddylore = new ArrayList<>();
                daddylore.add(ChatColor.AQUA + "NTIR贊助爸爸稱號");
                daddymeta.setLore(daddylore);
                daddy.setItemMeta(daddymeta);

                sponserMeta.setDisplayName(ChatColor.GRAY + "稱號:" + ChatColor.LIGHT_PURPLE + "[超能力]");
                ArrayList<String> slore = new ArrayList<>();
                slore.add(ChatColor.AQUA + "NTIR鈔能力爸爸稱號");
                sponserMeta.setLore(slore);
                sponser.setItemMeta(sponserMeta);

                testerMeta.setDisplayName(ChatColor.GRAY + "稱號:" + ChatColor.YELLOW + "[封弊者]");
                ArrayList<String> tlore = new ArrayList<>();
                tlore.add(ChatColor.AQUA + "NTIR鈔能力爸爸稱號");
                testerMeta.setLore(tlore);
                tester.setItemMeta(testerMeta);

                prefix.setItem(0, back);
                prefix.setItem(2, owner);
                prefix.setItem(3, player);
                prefix.setItem(4,daddy);
                prefix.setItem(5,sponser);
                prefix.setItem(6,tester);

                p.openInventory(prefix);
            }

            if(args[0].equalsIgnoreCase("eco")){

                Inventory eco = Bukkit.createInventory(p,9,ChatColor.GREEN + "經濟服幫助");
                ItemStack back = new ItemStack(Material.BARRIER);
                ItemStack info = new ItemStack(Material.WRITABLE_BOOK);
                ItemStack sendmoney100 = new ItemStack(Material.DIAMOND_BLOCK);
                ItemStack sendmoney1000 = new ItemStack(Material.EMERALD_BLOCK);
                ItemStack sendmoney10000 = new ItemStack(Material.NETHERITE_BLOCK);

                ItemMeta backmeta = back.getItemMeta();
                ItemMeta infometa = info.getItemMeta();
                ItemMeta send100meta = sendmoney100.getItemMeta();
                ItemMeta send1000meta = sendmoney1000.getItemMeta();
                ItemMeta send10000meta = sendmoney10000.getItemMeta();

                backmeta.setDisplayName(ChatColor.RED + "回上頁");
                send100meta.setDisplayName(ChatColor.GREEN + "發送" + ChatColor.RED + "100" + ChatColor.GREEN + "元到主伺服");
                send1000meta.setDisplayName(ChatColor.GREEN + "發送" + ChatColor.RED + "1000" + ChatColor.GREEN + "元到主伺服");
                send10000meta.setDisplayName(ChatColor.GREEN + "發送" + ChatColor.RED + "10000" + ChatColor.GREEN + "元到主伺服");
                back.setItemMeta(backmeta);
                sendmoney100.setItemMeta(send100meta);
                sendmoney1000.setItemMeta(send1000meta);
                sendmoney10000.setItemMeta(send10000meta);
                infometa.setDisplayName(ChatColor.AQUA + "查看目前的任務狀態");
                info.setItemMeta(infometa);

                eco.setItem(0,back);
                eco.setItem(3,info);
                eco.setItem(6,sendmoney100);
                eco.setItem(7,sendmoney1000);
                eco.setItem(8,sendmoney10000);

                p.openInventory(eco);


            }

        }

        return false;
    }
}
