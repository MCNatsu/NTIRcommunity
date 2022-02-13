package me.aq.plugin.ntirEco.Events;

import me.aq.plugin.ntirEco.NTIReco;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;

public class AntiExplode implements Listener {

    private NTIReco plugin;

    @EventHandler
    public void onExplode(EntityExplodeEvent e){

        plugin = NTIReco.getPlugin();

        if(plugin.getServer().getMotd().equalsIgnoreCase("Eco")){
            return;
        }

        if(e.getEntity().getType() == EntityType.WITHER){
            return;
        }

        e.setCancelled(true);

        String world = e.getEntity().getWorld().getName();
        Location location = e.getLocation();

        Bukkit.getWorld(world).createExplosion(location.getX(),location.getY(),location.getZ(),3,false,false);
        e.getEntity().remove();



    }

    @EventHandler
    public void TNT(ExplosionPrimeEvent e){

        if(plugin.getServer().getMotd().equalsIgnoreCase("Eco")){
            return;
        }

        e.setCancelled(true);
        String world = e.getEntity().getWorld().getName();
        Location location = e.getEntity().getLocation();
        Bukkit.getWorld(world).createExplosion(location.getX(),location.getY(),location.getZ(),4,false,false);
        e.getEntity().remove();
    }

}
