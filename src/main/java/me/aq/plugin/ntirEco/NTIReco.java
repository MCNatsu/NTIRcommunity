package me.aq.plugin.ntirEco;

import me.aq.plugin.ntirEco.Command.*;
import me.aq.plugin.ntirEco.Command.ServerManagement.*;
import me.aq.plugin.ntirEco.DiscordBot.DiscordBotMain;
import me.aq.plugin.ntirEco.DiscordBot.DiscordWebhook;
import me.aq.plugin.ntirEco.Events.EntityEvent.AntiExplode;
import me.aq.plugin.ntirEco.Events.EntityEvent.Death;
import me.aq.plugin.ntirEco.Events.EntityEvent.pvpDetect;
import me.aq.plugin.ntirEco.Events.gui.GuiSettings;
import me.aq.plugin.ntirEco.SQL.MySQL;
import me.aq.plugin.ntirEco.SQL.PlayerDefault;
import me.aq.plugin.ntirEco.SQL.SQLediter;
import me.aq.plugin.ntirEco.utils.BanMessage;
import me.aq.plugin.ntirEco.utils.Item.CustomItem;
import me.aq.plugin.ntirEco.utils.Item.SkullGetter;
import me.aq.plugin.ntirEco.utils.PluginMessage.PluginMsg;
import me.aq.plugin.ntirEco.utils.TempBanMessage;
import me.aq.plugin.ntirEco.utils.TempBanUtils;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;
import java.util.TimeZone;

public final class NTIReco extends JavaPlugin{

    private static NTIReco plugin;


    public MySQL SQL;
    public SQLediter data;
    public DiscordWebhook webhook;
    public TempBanUtils tempBanUtils;
    public TempBanMessage tempBanMessage;
    public ShopMenu menu;
    private static Permission perms = null;
    private static Economy econ = null;
    public BanMessage BanMessage;
    public PluginMsg pluginMsg = new PluginMsg();
    public SkullGetter skullGetter = new SkullGetter();

    FileConfiguration config = getConfig();
    public JDA jda;
    String tonken = getConfig().getString("Bot");
    String chatchan = getConfig().getString("Discord.chan");
    String url = "https://discordapp.com/api/webhooks/956882137141899265/EmWao3-wZvtljIH9opRznh5JFDfsLtEiyolw-8dV5GW-0n0rAhhVyfHSCArXRm1IAXql";


    public static NTIReco getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {

        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));

        plugin = this;

        this.SQL = new MySQL();
        this.data = new SQLediter();
        this.webhook = new DiscordWebhook(url);
        this.tempBanUtils = new TempBanUtils();
        this.tempBanMessage = new TempBanMessage();
        this.menu = new ShopMenu();
        this.BanMessage = new BanMessage();

        data.SQLGetter(this);
        config.options().copyDefaults(true);
        this.saveConfig();

        if (!setupEconomy() ) {
            Bukkit.getLogger().info("DISABLING THE PLUGIN...");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupPermissions();

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
        getServer().getPluginManager().registerEvents(new DiscordBotMain(), this);
        getServer().getPluginManager().registerEvents(new Death(),this);
        getServer().getPluginManager().registerEvents(new AntiExplode(),this);
        getServer().getMessenger().registerOutgoingPluginChannel(this,"BungeeCord");
        getServer().getMessenger().registerIncomingPluginChannel(this,"BungeeCord",new PluginMsg());
        getServer().getPluginManager().registerEvents(new pvpDetect(),this);
        /*
        getServer().getPluginManager().registerEvents(new AreaSelect(),this);
        getServer().getPluginManager().registerEvents(new EnterCommunity(), this);
        getServer().getPluginManager().registerEvents(new OnlineCheck(),this);
         */

        getCommand("points").setExecutor(new point());
        getCommand("pointsadmin").setExecutor(new admin());
        getCommand("shop").setExecutor(new ShopMenu());
        /*
        getCommand("createcommunity").setExecutor(new createCommunity());
        getCommand("setcommunity").setExecutor(new setCommunity());
        getCommand("cjoin").setExecutor(new requestJoin());
        getCommand("allow").setExecutor(new allow());
        */
        getCommand("tempban").setExecutor(new tempBan());
        getCommand("unban").setExecutor(new unBan());
        getCommand("pvp").setExecutor(new pvp());
        getCommand("getItem").setExecutor(new getItem());
        getCommand("bann").setExecutor(new Ban());


        CustomItem.init();

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

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    public static Economy getEconomy() {
        return econ;
    }

    public static Permission getPermissions() {
        return perms;
    }


}
