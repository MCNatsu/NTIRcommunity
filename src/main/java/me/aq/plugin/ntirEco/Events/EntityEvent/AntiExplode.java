package me.aq.plugin.ntirEco.Events.EntityEvent;

import me.aq.plugin.ntirEco.NTIReco;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;

public class AntiExplode implements Listener {

    private NTIReco plugin;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onExplode1(EntityExplodeEvent e){

        plugin = NTIReco.getPlugin();

        e.blockList().clear();
        return;


    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onExplode1(BlockExplodeEvent e){

        plugin = NTIReco.getPlugin();

        e.blockList().clear();
        return;


    }

}
