package me.aq.plugin.ntirEco.Command;

import me.aq.plugin.ntirEco.NTIReco;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ShopMenu implements CommandExecutor {

    private NTIReco plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        this.plugin = NTIReco.getPlugin();
        Player p = (Player) sender;

        if(args.length == 0){


            int point = plugin.data.getPoints(p.getUniqueId());
            String format = plugin.data.getPrefix(p.getUniqueId());

            Inventory menu = Bukkit.createInventory(p, 27, ChatColor.GOLD + "NTIR" + ChatColor.LIGHT_PURPLE + "官方商店選單");

            ItemStack playerINFO = new ItemStack(Material.BIRCH_SIGN);
            ItemStack air = new ItemStack(Material.AIR);

            ItemMeta INFO = playerINFO.getItemMeta();

            INFO.setDisplayName(ChatColor.LIGHT_PURPLE + p.getDisplayName());
            ArrayList<String> INFOlore = new ArrayList<>();
            INFOlore.add(ChatColor.GREEN + "NTIR點數餘額:" +ChatColor.GOLD + point );
            INFOlore.add(ChatColor.AQUA + "當前稱號:" + format);
            INFO.setLore(INFOlore);
            playerINFO.setItemMeta(INFO);

            menu.setItem(13, playerINFO);



            p.openInventory(menu);

            return true;

        }

        if(args.length == 1){

            if(args[0].equalsIgnoreCase("prefix")){

                Inventory prefix = Bukkit.createInventory(p, 27, ChatColor.LIGHT_PURPLE + "稱號設置與購買");

                ItemStack back = new ItemStack(Material.BARRIER);
                ItemStack owner = new ItemStack(Material.TOTEM_OF_UNDYING);
                ItemStack player = new ItemStack(Material.NAME_TAG);

                ItemMeta backmeta = back.getItemMeta();
                ItemMeta ownermeta = owner.getItemMeta();
                ItemMeta playermeta = player.getItemMeta();

                backmeta.setDisplayName(ChatColor.RED + "回上頁");
                back.setItemMeta(backmeta);

                ownermeta.setDisplayName(ChatColor.YELLOW + "稱號:" + ChatColor.LIGHT_PURPLE + "[服主]");
                ArrayList<String> ownerLore = new ArrayList<>();
                ownerLore.add(ChatColor.AQUA + "NTIR服主專用稱號");
                ownermeta.setLore(ownerLore);
                owner.setItemMeta(ownermeta);

                playermeta.setDisplayName(ChatColor.YELLOW + "稱號:" + ChatColor.AQUA + "[玩家]");
                ArrayList<String> playerLore = new ArrayList<>();
                playerLore.add(ChatColor.AQUA + "NTIR一般玩家稱號");
                playermeta.setLore(playerLore);
                player.setItemMeta(playermeta);

                prefix.setItem(0, back);
                prefix.setItem(2, owner);
                prefix.setItem(3, player);

                p.openInventory(prefix);
            }

        }

        return false;
    }
}
