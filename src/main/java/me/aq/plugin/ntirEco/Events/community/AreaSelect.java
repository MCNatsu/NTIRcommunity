package me.aq.plugin.ntirEco.Events.community;

import me.aq.plugin.ntirEco.NTIReco;
import me.aq.plugin.ntirEco.utils.Item.CustomItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.*;

public class AreaSelect implements Listener {

    private NTIReco plugin;
    ArrayList pos = new ArrayList();

    @EventHandler
    public void onClick(PlayerInteractEvent e){
        Player p = e.getPlayer();
        plugin = NTIReco.getPlugin();

        if(e.getItem() == null){
            return;
        }

        if(!e.getItem().getItemMeta().equals(CustomItem.selector.getItemMeta())){
            return;
        }

        if(!plugin.data.existsPlayerCommunity(p.getUniqueId())){
            return;
        }

        if(!plugin.data.getPost(p,plugin.data.getCommunity(p)).equalsIgnoreCase("Owner")){
            Bukkit.getConsoleSender().sendMessage("PostErr");
            return;
        }

        if(e.getAction().equals(Action.LEFT_CLICK_BLOCK)){

            pos.add(e.getClickedBlock().getLocation());

            p.sendMessage(ChatColor.GREEN + "成功設置點2");
            e.setCancelled(true);
            return;
        }

        if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            pos.add(e.getClickedBlock().getLocation());

            p.sendMessage(ChatColor.GREEN + "成功設置點1");
            e.setCancelled(true);
            return;

        }

        if(e.getAction().equals(Action.RIGHT_CLICK_AIR)){

            Location pos1 = (Location) pos.get(0);
            Location pos2 = (Location) pos.get(1);

            double pos1x = pos1.getX();
            double pos1z = pos1.getZ();
            double pos2x = pos2.getX();
            double pos2z = pos2.getZ();

            double area = (Math.max(pos1x,pos2x)-Math.min(pos1x,pos2x))*(Math.max(pos1z,pos2z)-Math.min(pos1z,pos2z));

            int levels = plugin.data.getLevels(plugin.data.getCommunity(p));

            if(levels == 1){
                if(area > 100*100){
                    p.sendMessage(ChatColor.RED + "你的社區等級不足以宣告如此龐大的區域!");
                    p.sendMessage(ChatColor.GRAY + "(當前等級:1 區域限制:10000格 你選擇了" + ChatColor.RED + area + ChatColor.GRAY + "格)");
                    pos.clear();
                    return;
                }
            }

            if(levels == 2){
                if(area > 200*200){
                    p.sendMessage(ChatColor.RED + "你的社區等級不足以宣告如此龐大的區域!");
                    p.sendMessage(ChatColor.GRAY + "(當前等級:2 區域限制:40000 你選擇了" + ChatColor.RED + area + ChatColor.GRAY + "格)");
                    pos.clear();
                    return;
                }
            }


            plugin.data.setPos1((Location) pos.get(0),plugin.data.getCommunity(p));
            plugin.data.setPos2((Location) pos.get(1),plugin.data.getCommunity(p));
            p.getInventory().remove(CustomItem.selector);
            pos.clear();
            p.sendMessage(ChatColor.GREEN + "社區範圍已設置完畢");
            return;
        }

    }
}
