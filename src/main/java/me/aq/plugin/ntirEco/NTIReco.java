package me.aq.plugin.ntirEco;

import me.aq.plugin.ntirEco.Command.ShopMenu;
import me.aq.plugin.ntirEco.Command.admin;
import me.aq.plugin.ntirEco.Command.point;
import me.aq.plugin.ntirEco.Events.GuiSettings;
import me.aq.plugin.ntirEco.SQL.MySQL;
import me.aq.plugin.ntirEco.SQL.PlayerDefault;
import me.aq.plugin.ntirEco.SQL.SQLediter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class NTIReco extends JavaPlugin {

    private static NTIReco plugin;

    public MySQL SQL;
    public SQLediter data;


    public static NTIReco getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;

        this.SQL = new MySQL();
        this.data = new SQLediter();
        data.SQLGetter(this);

        try {
            SQL.connect();
            data.createTable();

        } catch (ClassNotFoundException | SQLException e) {
            Bukkit.getLogger().info("DB not connected");
            Bukkit.getLogger().info("資料庫是該插件的必要功能");
            Bukkit.getLogger().info("DISABLING THE PLUGIN...");
            this.getServer().getPluginManager().disablePlugin(this);
        }

        getServer().getPluginManager().registerEvents(new PlayerDefault(), this);
        getServer().getPluginManager().registerEvents(new GuiSettings(), this);
        getCommand("points").setExecutor(new point());
        getCommand("pointsadmin").setExecutor(new admin());
        getCommand("shop").setExecutor(new ShopMenu());

    }

    @Override
    public void onDisable() {
        SQL.disconnect();
        // Plugin shutdown logic
    }
}
