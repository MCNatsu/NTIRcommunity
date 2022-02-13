package me.aq.plugin.ntirEco;

import me.aq.plugin.ntirEco.Command.*;
import me.aq.plugin.ntirEco.DiscordBot.DiscordBotMain;
import me.aq.plugin.ntirEco.DiscordBot.DiscordWebhook;
import me.aq.plugin.ntirEco.DiscordBot.DiscordtoMinecraft;
import me.aq.plugin.ntirEco.DiscordBot.Verify;
import me.aq.plugin.ntirEco.Events.AntiExplode;
import me.aq.plugin.ntirEco.Events.Chat;
import me.aq.plugin.ntirEco.Events.Death;
import me.aq.plugin.ntirEco.Events.GuiSettings;
import me.aq.plugin.ntirEco.SQL.MySQL;
import me.aq.plugin.ntirEco.SQL.PlayerDefault;
import me.aq.plugin.ntirEco.SQL.SQLediter;
import me.aq.plugin.ntirEco.utils.TempBanMessage;
import me.aq.plugin.ntirEco.utils.TempBanUtils;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;

public final class NTIReco extends JavaPlugin {

    private static NTIReco plugin;


    public MySQL SQL;
    public SQLediter data;
    public DiscordWebhook webhook;
    public TempBanUtils tempBanUtils;
    public TempBanMessage tempBanMessage;
    public ShopMenu menu;
    private static Economy econ = null;

    FileConfiguration config = getConfig();
    public JDA jda;
    String tonken = getConfig().getString("Bot");
    String chatchan = getConfig().getString("Discord.chan");
    String url = getConfig().getString("WebHook");


    public static NTIReco getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {

        plugin = this;

        this.SQL = new MySQL();
        this.data = new SQLediter();
        this.webhook = new DiscordWebhook(url);
        this.tempBanUtils = new TempBanUtils();
        this.tempBanMessage = new TempBanMessage();
        this.menu = new ShopMenu();

        data.SQLGetter(this);
        config.options().copyDefaults(true);
        this.saveConfig();

        if (!setupEconomy() ) {
            Bukkit.getLogger().info("DISABLING THE PLUGIN...");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

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
        getServer().getPluginManager().registerEvents(new Death(),this);
        getServer().getPluginManager().registerEvents(new AntiExplode(),this);
        getCommand("points").setExecutor(new point());
        getCommand("pointsadmin").setExecutor(new admin());
        getCommand("shop").setExecutor(new ShopMenu());
        getCommand("setprefix").setExecutor(new preflix());
        getCommand("createcommunity").setExecutor(new createCommunity());
        getCommand("setcommunity").setExecutor(new setCommunity());
        getCommand("verify").setExecutor(new verify());
        getCommand("msg").setExecutor(new msg());
        getCommand("tempban").setExecutor(new tempBan());
        getCommand("unban").setExecutor(new unBan());

        jda.addEventListener(new DiscordtoMinecraft());
        jda.addEventListener(new Verify());

        jda.getTextChannelById(plugin.getConfig().getString("ChatChannel")).sendMessage(getConfig().getString("OnlineMessage")).queue();

    }

    @Override
    public void onDisable() {
        SQL.disconnect();
        jda.shutdownNow();


        // Plugin shutdown logic
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }


}
