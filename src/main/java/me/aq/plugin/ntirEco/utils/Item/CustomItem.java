package me.aq.plugin.ntirEco.utils.Item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class CustomItem {

    public static ItemStack selector;
    public static ItemStack easterEGG;

    public static void init(){
        createSelector();
        createeasterEGG();
    }

    private static void createSelector(){
        ItemStack item = new ItemStack(Material.STICK);

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§e區域選擇器");
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§a社區區域標記用");
        lore.add("§c右鍵方塊選擇點1|左鍵方塊選擇點2|右鍵空氣確認");
        lore.add("§7NTIRCommunity社區系統");
        meta.setLore(lore);
        item.setItemMeta(meta);
        selector = item;
    }

    private static void createeasterEGG(){
        ItemStack item = new ItemStack(Material.TRADER_LLAMA_SPAWN_EGG);

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.RED + "復" + ChatColor.GOLD +  "活" + ChatColor.GREEN + "節"
                + ChatColor.BLUE + "彩" + ChatColor.LIGHT_PURPLE + "蛋");
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.RED + "復活節週限定!" + ChatColor.GRAY + "(2022/04/17)");
        lore.add(ChatColor.GOLD + "沒什麼特別的用途 就只是裝飾用");
        lore.add("§7NTIRCommunity節慶系統");
        meta.setLore(lore);
        item.setItemMeta(meta);
        easterEGG = item;
    }

}
