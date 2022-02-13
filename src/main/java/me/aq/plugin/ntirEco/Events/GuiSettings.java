package me.aq.plugin.ntirEco.Events;

import me.aq.plugin.ntirEco.Command.ShopMenu;
import me.aq.plugin.ntirEco.NTIReco;
import org.bukkit.Bukkit;
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
                    if(p.hasPermission("NTIR.daddy")){
                        plugin.data.setPrefix(p.getUniqueId(),ChatColor.GOLD + "[乾爹]" );
                        p.sendMessage(ChatColor.GREEN + "你已成功將你的稱號設置為" + ChatColor.AQUA + "[乾爹]");
                    }else {
                        p.sendMessage(ChatColor.RED + "你無法使用這個稱號!");
                    }
                    p.closeInventory();
                    break;
                case EMERALD_BLOCK:
                    if(p.hasPermission("NTIR.sponser")){
                        plugin.data.setPrefix(p.getUniqueId(),ChatColor.GREEN + "[鈔能力]" );
                        p.sendMessage(ChatColor.GREEN + "你已成功將你的稱號設置為" + ChatColor.GREEN + "[鈔能力]");
                    }else {
                        p.sendMessage(ChatColor.RED + "你無法使用這個稱號!");
                    }
                    p.closeInventory();
                    break;
                case BEDROCK:
                    if(p.hasPermission("NTIR.tester")){
                        plugin.data.setPrefix(p.getUniqueId(),ChatColor.YELLOW + "[封弊者]" );
                        p.sendMessage(ChatColor.GREEN + "你已成功將你的稱號設置為" + ChatColor.GREEN + "[封弊者]");
                    }else {
                        p.sendMessage(ChatColor.RED + "你無法使用這個稱號!");
                    }
                    p.closeInventory();
                    break;
            }
            e.setCancelled(true);
        }

        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD + "NTIR" + ChatColor.LIGHT_PURPLE + "官方商店選單")){
            switch (e.getCurrentItem().getType()){
                case NAME_TAG:
                    Bukkit.dispatchCommand(p,"shop prefix");
                    p.playSound(p,Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1,1);
                    break;
                case GOLD_BLOCK:
                    if(plugin.getServer().getMotd() != "Eco"){
                        p.sendMessage(ChatColor.RED + "該選單只在經濟服有用!");
                        return;
                    }
                    Bukkit.dispatchCommand(p,"shop eco");
                    p.playSound(p,Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1,1);
                    break;
            }
            e.setCancelled(true);
        }

        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.GREEN + "經濟服幫助")){

            switch (e.getCurrentItem().getType()){
                case BARRIER:
                    Bukkit.dispatchCommand(p,"shop");
                    p.playSound(p,Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1,1);
                    break;
                case DIAMOND_BLOCK:
                    if(plugin.getEconomy().getBalance(p) > 300) {
                        plugin.getEconomy().withdrawPlayer(p, 300);
                        plugin.data.addMoney(100, p.getUniqueId().toString());
                        p.sendMessage(ChatColor.GREEN + "你成功將300點轉換為100元");
                    }else{
                        p.sendMessage(ChatColor.RED + "你的點數不足!");
                    }
                    break;
                case EMERALD_BLOCK:
                    if(plugin.getEconomy().getBalance(p) > 3000){
                        plugin.getEconomy().withdrawPlayer(p,3000);
                        plugin.data.addMoney(1000,p.getUniqueId().toString());
                        p.sendMessage(ChatColor.GREEN + "你成功將3000點轉換為1000元");
                    }else {
                        p.sendMessage(ChatColor.RED + "你的點數不足!");
                    }
                    break;
                case NETHERITE_BLOCK:
                    if(plugin.getEconomy().getBalance(p) > 30000){
                        plugin.getEconomy().withdrawPlayer(p,30000);
                        plugin.data.addMoney(10000,p.getUniqueId().toString());
                        p.sendMessage(ChatColor.GREEN + "你成功將30000點轉換為10000元");
                    }else {
                        p.sendMessage(ChatColor.RED + "你的點數不足!");
                    }
                    break;
            }
            e.setCancelled(true);
        }
    }

}
