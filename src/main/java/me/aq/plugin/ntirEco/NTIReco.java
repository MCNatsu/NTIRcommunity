package me.aq.plugin.ntirEco;

import me.aq.plugin.ntirEco.Command.*;
import me.aq.plugin.ntirEco.DiscordBot.DiscordBotMain;
import me.aq.plugin.ntirEco.DiscordBot.DiscordtoMinecraft;
import me.aq.plugin.ntirEco.DiscordBot.Verify;
import me.aq.plugin.ntirEco.Events.Chat;
import me.aq.plugin.ntirEco.Events.GuiSettings;
import me.aq.plugin.ntirEco.SQL.MySQL;
import me.aq.plugin.ntirEco.SQL.PlayerDefault;
import me.aq.plugin.ntirEco.SQL.SQLediter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;

public final class NTIReco extends JavaPlugin {

    private static NTIReco plugin;


    public MySQL SQL;
    public SQLediter data;
    FileConfiguration config = getConfig();
    public JDA jda;
    String tonken = getConfig().getString("Bot.token");
    String chatchan = getConfig().getString("Discord.chan");


    public static NTIReco getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {

        plugin = this;

        this.SQL = new MySQL();
        this.data = new SQLediter();
        data.SQLGetter(this);
        config.options().copyDefaults(true);
        this.saveConfig();

        try {
            jda = JDABuilder.createDefault(tonken).build().awaitReady();
        }catch (LoginException | InterruptedException e){
            e.printStackTrace();

        }
        if(jda == null){
            getServer().getPluginManager().disablePlugin(this);
        }



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
        getServer().getPluginManager().registerEvents(new Chat(), this);
        getServer().getPluginManager().registerEvents(new DiscordBotMain(), this);
        getCommand("points").setExecutor(new point());
        getCommand("pointsadmin").setExecutor(new admin());
        getCommand("shop").setExecutor(new ShopMenu());
        getCommand("setprefix").setExecutor(new preflix());
        getCommand("createcommunity").setExecutor(new createCommunity());
        getCommand("setcommunity").setExecutor(new setCommunity());
        getCommand("verify").setExecutor(new verify());

        jda.addEventListener(new DiscordtoMinecraft());
        jda.addEventListener(new Verify());

    }

    @Override
    public void onDisable() {
        SQL.disconnect();
        jda.shutdown();


        // Plugin shutdown logic
    }



}
