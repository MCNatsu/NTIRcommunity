package me.aq.plugin.ntirEco.Events;

import me.aq.plugin.ntirEco.Command.ShopMenu;
import me.aq.plugin.ntirEco.NTIReco;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GuiSettings implements Listener {

    private NTIReco plugin;

    @EventHandler
    public void ClickGui(InventoryClickEvent e){

        plugin = NTIReco.getPlugin();

        Player p = (Player) e.getWhoClicked();

        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD + "NTIR" + ChatColor.LIGHT_PURPLE + "官方商店選單")){

            e.setCancelled(true);

        }

        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.LIGHT_PURPLE + "稱號設置與購買")){

            switch (e.getCurrentItem().getType()) {

                case BARRIER:
                  p.closeInventory();
                  break;
                case TOTEM_OF_UNDYING:
                    if(p.hasPermission("NTIROwner")){
                        plugin.data.setPrefix(p.getUniqueId(), ChatColor.LIGHT_PURPLE + "[服主]");
                        p.sendMessage(ChatColor.GREEN + "你已成功將你的稱號設置為" + ChatColor.LIGHT_PURPLE + "[服主]");
                    }else {
                        p.sendMessage(ChatColor.RED + "你無法使用這個稱號!");
                    }
                    p.closeInventory();
                    break;

                case NAME_TAG:
                    plugin.data.setPrefix(p.getUniqueId(),ChatColor.AQUA + "[玩家]" );
                    p.sendMessage(ChatColor.GREEN + "你已成功將你的稱號設置為" + ChatColor.AQUA + "[玩家]");
                    p.closeInventory();
                    break;

                case GOLD_BLOCK:
                    plugin.data.setPrefix(p.getUniqueId(),ChatColor.GOLD + "[乾爹]" );
                    p.sendMessage(ChatColor.GREEN + "你已成功將你的稱號設置為" + ChatColor.AQUA + "[乾爹]");
                    p.closeInventory();
                    break;
                case EMERALD_BLOCK:
                    plugin.data.setPrefix(p.getUniqueId(),ChatColor.GREEN + "[鈔能力]" );
                    p.sendMessage(ChatColor.GREEN + "你已成功將你的稱號設置為" + ChatColor.GREEN + "[鈔能力]");
                    p.closeInventory();
                    break;
            }

            e.setCancelled(true);

        }
    }

}
