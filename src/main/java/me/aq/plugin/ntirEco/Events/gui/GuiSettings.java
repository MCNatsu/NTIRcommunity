package me.aq.plugin.ntirEco.Events.gui;

import me.aq.plugin.ntirEco.Command.ShopMenu;
import me.aq.plugin.ntirEco.NTIReco;
import me.aq.plugin.ntirEco.utils.Item.CustomItem;
import me.aq.plugin.ntirEco.utils.PluginMessage.PluginMsg;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GuiSettings implements Listener {

    private NTIReco plugin;

    @EventHandler
    public void ClickGui(InventoryClickEvent e){

        plugin = NTIReco.getPlugin();

        Player p = (Player) e.getWhoClicked();


        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + "稱號設置與購買")){
            e.setCancelled(true);

            switch (e.getCurrentItem().getType()) {

                case BARRIER:
                  Bukkit.dispatchCommand(p,"shop");
                  break;
                case TOTEM_OF_UNDYING:
                    if(p.hasPermission("NTIROwner")){
                        plugin.data.setPrefix(p.getUniqueId(), ChatColor.LIGHT_PURPLE + "服主" + ChatColor.RESET);
                        p.sendMessage(ChatColor.GREEN + "你已成功將你的稱號設置為" + ChatColor.LIGHT_PURPLE + "[服主]");
                    }else {
                        p.sendMessage(ChatColor.RED + "你無法使用這個稱號!");
                    }
                    p.closeInventory();
                    break;

                case NAME_TAG:
                    plugin.data.setPrefix(p.getUniqueId(),ChatColor.AQUA + "玩家" );
                    p.sendMessage(ChatColor.GREEN + "你已成功將你的稱號設置為" + ChatColor.AQUA + "[玩家]" + ChatColor.RESET);
                    p.closeInventory();
                    break;

                case GOLD_BLOCK:
                    if(p.hasPermission("NTIR.daddy")){
                        plugin.data.setPrefix(p.getUniqueId(),ChatColor.GOLD + "乾爹" );
                        p.sendMessage(ChatColor.GREEN + "你已成功將你的稱號設置為" + ChatColor.GOLD + "[乾爹]" + ChatColor.RESET);
                    }else {
                        p.sendMessage(ChatColor.RED + "你無法使用這個稱號!");
                    }
                    p.closeInventory();
                    break;
                case EMERALD_BLOCK:
                    if(p.hasPermission("NTIR.sponser")){
                        plugin.data.setPrefix(p.getUniqueId(),ChatColor.GREEN + "鈔能力" );
                        p.sendMessage(ChatColor.GREEN + "你已成功將你的稱號設置為" + ChatColor.GREEN + "[鈔能力]" + ChatColor.RESET);
                    }else {
                        p.sendMessage(ChatColor.RED + "你無法使用這個稱號!");
                    }
                    p.closeInventory();
                    break;
                case BEDROCK:
                    if(p.hasPermission("NTIR.tester")){
                        plugin.data.setPrefix(p.getUniqueId(),ChatColor.YELLOW + "封弊者" );
                        p.sendMessage(ChatColor.GREEN + "你已成功將你的稱號設置為" + ChatColor.YELLOW + "[封弊者]");
                    }else {
                        p.sendMessage(ChatColor.RED + "你無法使用這個稱號!");
                    }
                    p.closeInventory();
                    break;
            }

        }

        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.BOLD + "" + ChatColor.GOLD + "NTIR" + ChatColor.LIGHT_PURPLE + "官方商店選單")){
            e.setCancelled(true);

            if(e.getCurrentItem() == null){
                return;
            }
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
                case LECTERN:
                    Bukkit.dispatchCommand(p,"shop shop");
                    p.playSound(p,Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1,1);
                    break;
                case LANTERN:
                    Bukkit.dispatchCommand(p,"shop community");
                    p.playSound(p,Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1,1);
                    break;
                case COMPASS:
                    plugin.pluginMsg.connect(p,"Lobby");
                case ENCHANTED_BOOK:
                    Bukkit.dispatchCommand(p,"shop ChatSettings");
                    p.playSound(p,Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1,1);
                    break;
                case MOJANG_BANNER_PATTERN:
                    if(plugin.data.UPtoDATE()){
                        plugin.data.UPDATE();
                    }
                    if(plugin.data.signed(p)){
                        p.sendMessage(ChatColor.RED + "你今天已經簽到過了!");
                        break;
                    }

                    if(plugin.data.getSignedDays(p) >= 7){
                        p.sendMessage(ChatColor.GOLD + "你已經連續簽到7日!" + ChatColor.GREEN + "真是個活躍的玩家!");

                    }

                    plugin.data.sign(p);
                    if(plugin.data.getSignedDays(p) > 5){
                        plugin.data.addpoint(p.getUniqueId(),10);
                        p.sendMessage(ChatColor.GREEN + "成功簽到" +ChatColor.LIGHT_PURPLE +  "你已獲得今日獎勵" + ChatColor.GOLD + "10點NTIR點數");
                        p.playSound(p,Sound.ENTITY_PLAYER_LEVELUP,1,1);
                        break;
                    }
                    if(plugin.data.getSignedDays(p) > 3){
                        plugin.data.addMoney(200, p.getUniqueId().toString());
                        p.sendMessage(ChatColor.GREEN + "成功簽到" +ChatColor.LIGHT_PURPLE +  "你已獲得今日獎勵" + ChatColor.GOLD + "200元NTIR幣");
                        p.playSound(p,Sound.ENTITY_PLAYER_LEVELUP,1,1);
                        break;
                    }
                    p.getInventory().addItem(new ItemStack(Material.COOKED_BEEF,64));
                    p.playSound(p,Sound.ENTITY_PLAYER_LEVELUP,1,1);
                    p.sendMessage(ChatColor.GREEN + "成功簽到" +ChatColor.LIGHT_PURPLE +  "你已獲得今日獎勵" + ChatColor.GOLD + "豬排1組");
                    break;
                case WRITABLE_BOOK:
                    TextComponent openURL = new TextComponent(ChatColor.AQUA + "官方社群邀請碼 " + ChatColor.RED + "https://discord.gg/bv5emFs4eM");
                    openURL.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL,"https://discord.gg/bv5emFs4eM"));
                    p.spigot().sendMessage(openURL);
                    break;
                case TRADER_LLAMA_SPAWN_EGG:
                    if(plugin.data.getted(p)){
                        break;
                    }
                    plugin.data.getReward(p);
                    p.getInventory().addItem(CustomItem.easterEGG);
                    break;

            }

        }

        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.BOLD + "" + ChatColor.GREEN + "經濟服幫助")){

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
                case WRITABLE_BOOK:
                    Bukkit.dispatchCommand(p,"qs stats");
                    break;

            }
            e.setCancelled(true);
        }

        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.BOLD + "" + ChatColor.GOLD + "社區設置選單")){
            switch (e.getCurrentItem().getType()){
                case BARRIER:
                    Bukkit.dispatchCommand(p,"shop");
                    p.playSound(p,Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1,1);
                    break;
                case COMPASS:
                    p.sendMessage(ChatColor.GREEN + "當前社區列表:");
                    p.sendMessage(ChatColor.BLUE + plugin.data.getCommunitys().toString());
                    p.sendMessage(ChatColor.GRAY + "當前社區總量:" + plugin.data.CommunityCount());
                    break;

                case REDSTONE_TORCH:
                    String community = plugin.data.getCommunity(p);
                    if(community == null){
                        p.closeInventory();
                        p.sendMessage(ChatColor.RED + "你尚未加入任何社區!");
                        break;
                    }
                    if(p.getUniqueId() != plugin.data.getOwner(community).getUniqueId()){
                        p.closeInventory();
                        p.sendMessage(ChatColor.RED + "你無法設置不是你擁有的社區!");
                        break;
                    }
                    Bukkit.dispatchCommand(p,"shop cs");
                    break;
            }
            e.setCancelled(true);
        }

        if(e.getView().getTitle().contains(ChatColor.BOLD + "" + ChatColor.GREEN + "的社區設定選單")){
            switch (e.getCurrentItem().getType()){
                case BARRIER:
                    Bukkit.dispatchCommand(p,"shop");
                    p.playSound(p,Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1,1);
                    break;
                case RESPAWN_ANCHOR:
                    List list = plugin.data.inCommunity(plugin.data.getCommunity(p));
                    p.sendMessage(ChatColor.YELLOW + "目前有" + list.size() + "位玩家在社區的宣告範圍內!");
                    p.sendMessage(ChatColor.BLUE + list.toString());
                    break;
                case PAPER:
                    List rlist = plugin.data.getRequest(plugin.data.getCommunity(p));
                    p.sendMessage(ChatColor.GREEN + "有" + ChatColor.LIGHT_PURPLE + rlist.size() + ChatColor.GREEN + "位玩家申請加入你的社區" + ChatColor.GRAY + "(/allow <玩家>同意)");
                    p.sendMessage(ChatColor.BLUE + rlist.toString());
                    break;
            }
            e.setCancelled(true);
        }

        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.BOLD + "" + ChatColor.GOLD + "NTIR伺服器" + ChatColor.BLUE + "聊天插件設定")){
            if(e.getCurrentItem() == null){
                return;
            }
            switch (e.getCurrentItem().getType()){
                case NAME_TAG:
                    Bukkit.dispatchCommand(p,"shop prefix");
                    p.playSound(p,Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1,1);
                    break;
            }
            e.setCancelled(true);
        }
    }

}
