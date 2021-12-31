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

        if(args.length == 0){

            Player p = (Player) sender;
            int point = plugin.data.getPoints(p.getUniqueId());

            Inventory menu = Bukkit.createInventory(p, 27, ChatColor.GOLD + "NTIR" + ChatColor.LIGHT_PURPLE + "官方商店選單");

            ItemStack playerINFO = new ItemStack(Material.BIRCH_SIGN);
            ItemStack air = new ItemStack(Material.AIR);

            ItemMeta INFO = playerINFO.getItemMeta();

            INFO.setDisplayName(ChatColor.LIGHT_PURPLE + p.getDisplayName());
            ArrayList<String> INFOlore = new ArrayList<>();
            INFOlore.add(ChatColor.GREEN + "NTIR點數餘額:" +ChatColor.GOLD + point );
            INFO.setLore(INFOlore);
            playerINFO.setItemMeta(INFO);

            menu.setItem(13, playerINFO);



            p.openInventory(menu);

            return true;

        }

        return false;
    }
}
