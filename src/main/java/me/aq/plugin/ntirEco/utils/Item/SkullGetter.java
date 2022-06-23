package me.aq.plugin.ntirEco.utils.Item;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullGetter {

    public ItemStack getPlayerSkull(Player p){

        ItemStack skull = new ItemStack(Material.PLAYER_HEAD,1);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwner(p.getName());
        skull.setItemMeta(meta);
        return skull;

    }

}

