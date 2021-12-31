package me.aq.plugin.ntirEco.Events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GuiSettings implements Listener {

    @EventHandler
    public void ClickGui(InventoryClickEvent e){

        Player p = (Player) e.getWhoClicked();

        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD + "NTIR" + ChatColor.LIGHT_PURPLE + "官方商店選單")){

            e.setCancelled(true);

        }
    }

}
