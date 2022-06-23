package me.aq.plugin.ntirEco.Command;

import me.aq.plugin.ntirEco.NTIReco;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShopMenu implements CommandExecutor {

    private NTIReco plugin;
    public Inventory menu;
    public Inventory prefix;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        this.plugin = NTIReco.getPlugin();
        Player p = (Player) sender;

        if(args.length == 0){


            int point = plugin.data.getPoints(p.getUniqueId());
            String format = plugin.data.getPrefix(p.getUniqueId());
            String community = plugin.data.getCommunity(p);
            if(community == null){
                community = "無";
            }

            menu = Bukkit.createInventory(p, 27, ChatColor.BOLD + "" + ChatColor.GOLD + "NTIR" + ChatColor.LIGHT_PURPLE + "官方商店選單");

            ItemStack playerINFO = plugin.skullGetter.getPlayerSkull(p);

            ItemStack glass = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
            ItemStack eco = new ItemStack(Material.GOLD_BLOCK);
            ItemStack shop = new ItemStack(Material.LECTERN);
            ItemStack Community = new ItemStack(Material.LANTERN);
            ItemStack BacktoLobby = new ItemStack(Material.COMPASS);
            ItemStack ChatSettings = new ItemStack(Material.ENCHANTED_BOOK);
            ItemStack daily = new ItemStack(Material.MOJANG_BANNER_PATTERN);
            ItemStack discord = new ItemStack(Material.WRITABLE_BOOK);

            ItemMeta glassMeta = glass.getItemMeta();
            ItemMeta INFO = playerINFO.getItemMeta();
            ItemMeta ecoMeta = eco.getItemMeta();
            ItemMeta shopmeta = shop.getItemMeta();
            ItemMeta CMeta = Community.getItemMeta();
            ItemMeta LBMeta = BacktoLobby.getItemMeta();
            ItemMeta ChatMeta = ChatSettings.getItemMeta();
            ItemMeta dailyMeta = daily.getItemMeta();
            ItemMeta discordMeta  = discord.getItemMeta();

            INFO.setDisplayName(ChatColor.LIGHT_PURPLE + p.getDisplayName());
            ArrayList<String> INFOlore = new ArrayList<>();
            INFOlore.add(ChatColor.GREEN + "NTIR點數餘額:" +ChatColor.RED + point );
            INFOlore.add(ChatColor.YELLOW + "金錢餘額" + ChatColor.GREEN + plugin.getEconomy().getBalance(p));
            INFOlore.add(ChatColor.AQUA + "當前稱號:" + format);
            INFOlore.add(ChatColor.LIGHT_PURPLE + "你目前所加入的社區:" + ChatColor.GOLD + community);
            INFO.setLore(INFOlore);
            playerINFO.setItemMeta(INFO);

            ecoMeta.setDisplayName(ChatColor.GREEN + "經濟服幫助");
            eco.setItemMeta(ecoMeta);

            shopmeta.setDisplayName(ChatColor.GREEN + "官方商店");
            shop.setItemMeta(shopmeta);

            CMeta.setDisplayName(ChatColor.GOLD + "社區選單");
            ArrayList<String> Clore = new ArrayList<>();
            Clore.add(ChatColor.RED +"該頁面可進行社區的相關設置");
            Clore.add(ChatColor.GRAY + "NTIR社區系統");
            CMeta.setLore(Clore);
            Community.setItemMeta(CMeta);

            LBMeta.setDisplayName(ChatColor.GRAY + "返回大廳");
            BacktoLobby.setItemMeta(LBMeta);

            ChatMeta.setDisplayName(ChatColor.RED + "聊" + ChatColor.GOLD +  "天" + ChatColor.GREEN + "插"
                    + ChatColor.BLUE + "件" + ChatColor.LIGHT_PURPLE + "設" + ChatColor.DARK_PURPLE + "定");
            ArrayList<String> ChatLore = new ArrayList<>();
            ChatLore.add(ChatColor.GRAY + "聊天插件相關設定");
            ChatLore.add(ChatColor.GOLD + "名稱顏色 " + ChatColor.RED + "稱號設置 " + ChatColor.LIGHT_PURPLE + "稱號顏色" + ChatColor.GRAY + "等...");
            ChatMeta.setLore(ChatLore);
            ChatSettings.setItemMeta(ChatMeta);

            glassMeta.setDisplayName(null);
            glass.setItemMeta(glassMeta);

            SimpleDateFormat SF = new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss.SSS");
            Date cur = new Date();

            dailyMeta.setDisplayName(ChatColor.RED + "每" + ChatColor.GOLD +  "日" + ChatColor.GREEN + "簽" + ChatColor.BLUE + "到");
            ArrayList<String> dailyLore = new ArrayList<>();
            dailyLore.add(ChatColor.GRAY + "點我每日簽到!" + ChatColor.GRAY + "(你已連續簽到" + ChatColor.RED + plugin.data.getSignedDays(p) + ChatColor.GRAY + "日)");
            dailyLore.add(ChatColor.GOLD + "每日簽到規則:");
            dailyLore.add(ChatColor.LIGHT_PURPLE + "連續天數最多累計至7日" + ChatColor.YELLOW + "超出將會重置");
            dailyLore.add(ChatColor.RED + "若一日未簽到則累計天數將會歸零");
            dailyLore.add(ChatColor.GRAY + "每日凌晨6:00更新簽到進度");
            dailyLore.add(ChatColor.GRAY + "系統時間" + ChatColor.RED + SF.format(cur));
            dailyMeta.setLore(dailyLore);
            dailyMeta.removeItemFlags();
            daily.setItemMeta(dailyMeta);

            discordMeta.setDisplayName(ChatColor.GOLD + "官方玩家社群");
            ArrayList<String> discordLore = new ArrayList<>();
            discordLore.add(ChatColor.GRAY + "點我加入官方Discord玩家社群");
            discordLore.add(ChatColor.RED + "所有與伺服器相關的事務與公告都在這裡");
            discordLore.add(ChatColor.GOLD + "==============================");
            discordLore.add(ChatColor.AQUA + "加入後請按照訊息所述綁定後即可存取社群!");
            discordLore.add(ChatColor.GREEN + "https://discord.gg/bv5emFs4eM");
            discordMeta.setLore(discordLore);
            discord.setItemMeta(discordMeta);

            menu.setItem(0,glass);
            menu.setItem(1,glass);
            menu.setItem(2,discord);
            menu.setItem(3,glass);
            menu.setItem(4,glass);
            menu.setItem(5,glass);
            menu.setItem(6,glass);
            menu.setItem(7,glass);
            menu.setItem(16,daily);
            menu.setItem(17,glass);
            menu.setItem(26,glass);
            menu.setItem(8,BacktoLobby);
            menu.setItem(12,Community);
            menu.setItem(13, playerINFO);
            menu.setItem(14,ChatSettings);
            menu.setItem(18,shop);
            if(plugin.getServer().getMotd().equalsIgnoreCase("eco")){
                menu.setItem(26,eco);
            }



            p.openInventory(menu);
            p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1,1);

            return true;

        }

        if(args.length == 1){

            if(args[0].equalsIgnoreCase("prefix")){

                prefix = Bukkit.createInventory(p, 27, ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + "稱號設置與購買");

                ItemStack back = new ItemStack(Material.BARRIER);
                ItemStack owner = new ItemStack(Material.TOTEM_OF_UNDYING);
                ItemStack player = new ItemStack(Material.NAME_TAG);
                ItemStack daddy = new ItemStack(Material.GOLD_BLOCK);
                ItemStack sponser = new ItemStack(Material.EMERALD_BLOCK);
                ItemStack tester = new ItemStack(Material.BEDROCK);
                ItemStack admin = new ItemStack(Material.AMETHYST_SHARD);

                ItemMeta backmeta = back.getItemMeta();
                ItemMeta ownermeta = owner.getItemMeta();
                ItemMeta playermeta = player.getItemMeta();
                ItemMeta daddymeta = daddy.getItemMeta();
                ItemMeta sponserMeta = sponser.getItemMeta();
                ItemMeta testerMeta = tester.getItemMeta();
                ItemMeta adminMeta = admin.getItemMeta();

                backmeta.setDisplayName(ChatColor.GRAY + "回上頁");
                back.setItemMeta(backmeta);

                ownermeta.setDisplayName(ChatColor.GRAY + "稱號:" + ChatColor.LIGHT_PURPLE + "服主");
                ArrayList<String> ownerLore = new ArrayList<>();
                ownerLore.add(ChatColor.AQUA + "NTIR服主專用稱號");
                ownermeta.setLore(ownerLore);
                owner.setItemMeta(ownermeta);

                playermeta.setDisplayName(ChatColor.GRAY + "稱號:" + ChatColor.AQUA + "玩家");
                ArrayList<String> playerLore = new ArrayList<>();
                playerLore.add(ChatColor.AQUA + "NTIR一般玩家稱號");
                playermeta.setLore(playerLore);
                player.setItemMeta(playermeta);

                daddymeta.setDisplayName(ChatColor.GRAY + "稱號:" + ChatColor.GREEN + "乾爹");
                ArrayList<String> daddylore = new ArrayList<>();
                daddylore.add(ChatColor.AQUA + "NTIR贊助爸爸稱號");
                daddymeta.setLore(daddylore);
                daddy.setItemMeta(daddymeta);

                sponserMeta.setDisplayName(ChatColor.GRAY + "稱號:" + ChatColor.LIGHT_PURPLE + "鈔能力");
                ArrayList<String> slore = new ArrayList<>();
                slore.add(ChatColor.AQUA + "NTIR鈔能力爸爸稱號");
                sponserMeta.setLore(slore);
                sponser.setItemMeta(sponserMeta);

                testerMeta.setDisplayName(ChatColor.GRAY + "稱號:" + ChatColor.YELLOW + "封弊者");
                ArrayList<String> tlore = new ArrayList<>();
                tlore.add(ChatColor.AQUA + "NTIR鈔能力爸爸稱號");
                testerMeta.setLore(tlore);
                tester.setItemMeta(testerMeta);

                adminMeta.setDisplayName(ChatColor.GRAY + "稱號:" + ChatColor.GOLD + "管理員");
                ArrayList<String> adminLore = new ArrayList<>();
                adminLore.add(ChatColor.AQUA + "NTIR管理員稱號");
                adminMeta.setLore(adminLore);
                admin.setItemMeta(adminMeta);

                prefix.setItem(0, back);
                prefix.setItem(2, owner);
                prefix.setItem(3, admin);
                prefix.setItem(4,player);
                prefix.setItem(5,daddy);
                prefix.setItem(6,sponser);
                prefix.setItem(7,tester);

                p.openInventory(prefix);
            }

            if(args[0].equalsIgnoreCase("color")){

                prefix = Bukkit.createInventory(p, 9, ChatColor.BOLD + "" + ChatColor.BLUE + "名稱顏色與稱號顏色");

                ItemStack back = new ItemStack(Material.BARRIER);
                ItemStack prefixColor = new ItemStack(Material.TOTEM_OF_UNDYING);
                ItemStack nameColor = new ItemStack(Material.NAME_TAG);


                ItemMeta backmeta = back.getItemMeta();
                ItemMeta pColorMeta = back.getItemMeta();
                ItemMeta nColorMeta = back.getItemMeta();


                backmeta.setDisplayName(ChatColor.GRAY + "回上頁");
                back.setItemMeta(backmeta);

                pColorMeta.setDisplayName(ChatColor.GOLD + "稱號顏色設定");

                nColorMeta.setDisplayName(ChatColor.AQUA + "名稱顏色設定");

                prefix.setItem(0, back);
                prefix.setItem(2, prefixColor);
                prefix.setItem(6, nameColor);

                p.openInventory(prefix);
            }

            if(args[0].equalsIgnoreCase("eco")){

                Inventory eco = Bukkit.createInventory(p,9,ChatColor.BOLD + "" + ChatColor.GREEN + "經濟服幫助");
                ItemStack back = new ItemStack(Material.BARRIER);
                ItemStack info = new ItemStack(Material.WRITABLE_BOOK);
                ItemStack sendmoney100 = new ItemStack(Material.DIAMOND_BLOCK);
                ItemStack sendmoney1000 = new ItemStack(Material.EMERALD_BLOCK);
                ItemStack sendmoney10000 = new ItemStack(Material.NETHERITE_BLOCK);

                ItemMeta backmeta = back.getItemMeta();
                ItemMeta infometa = info.getItemMeta();
                ItemMeta send100meta = sendmoney100.getItemMeta();
                ItemMeta send1000meta = sendmoney1000.getItemMeta();
                ItemMeta send10000meta = sendmoney10000.getItemMeta();

                backmeta.setDisplayName(ChatColor.RED + "回上頁");
                send100meta.setDisplayName(ChatColor.GREEN + "發送" + ChatColor.RED + "100" + ChatColor.GREEN + "元到主伺服");
                send1000meta.setDisplayName(ChatColor.GREEN + "發送" + ChatColor.RED + "1000" + ChatColor.GREEN + "元到主伺服");
                send10000meta.setDisplayName(ChatColor.GREEN + "發送" + ChatColor.RED + "10000" + ChatColor.GREEN + "元到主伺服");
                back.setItemMeta(backmeta);
                sendmoney100.setItemMeta(send100meta);
                sendmoney1000.setItemMeta(send1000meta);
                sendmoney10000.setItemMeta(send10000meta);
                infometa.setDisplayName(ChatColor.AQUA + "查看目前的任務狀態");
                info.setItemMeta(infometa);

                eco.setItem(0,back);
                eco.setItem(3,info);
                eco.setItem(6,sendmoney100);
                eco.setItem(7,sendmoney1000);
                eco.setItem(8,sendmoney10000);

                p.openInventory(eco);


            }

            if(args[0].equalsIgnoreCase("community")){

                String community = plugin.data.getCommunity(p);
                if(community == null){
                    community = "無";
                }

                Inventory Community = Bukkit.createInventory(p,9,ChatColor.BOLD + "" + ChatColor.GOLD + "社區設置選單");
                ItemStack back = new ItemStack(Material.BARRIER);
                ItemStack info = new ItemStack(Material.WRITABLE_BOOK);
                ItemStack ShowLIst = new ItemStack(Material.COMPASS);
                ItemStack communitySettings = new ItemStack(Material.REDSTONE_TORCH);

                ItemMeta backmeta = back.getItemMeta();
                ItemMeta infometa = info.getItemMeta();
                ItemMeta listmeta = ShowLIst.getItemMeta();
                ItemMeta csmeta = communitySettings.getItemMeta();

                backmeta.setDisplayName(ChatColor.RED + "回上頁");

                infometa.setDisplayName(ChatColor.LIGHT_PURPLE + "你目前所加入的社區:" + ChatColor.GOLD + community);
                if(community != "無" | plugin.data.getOwner(community).getUniqueId() == p.getUniqueId()){
                    ArrayList<String> infoLore = new ArrayList<>();
                    infoLore.add(ChatColor.GREEN + "社區職位:" + ChatColor.RED + "擁有者");
                    infoLore.add(ChatColor.GRAY + "社區等級:" + ChatColor.YELLOW +plugin.data.getLevels(community));
                    infoLore.add(ChatColor.AQUA + "你可以使用社區設定選單進行相關設置");
                    infometa.setLore(infoLore);
                }
                info.setItemMeta(infometa);

                listmeta.setDisplayName(ChatColor.LIGHT_PURPLE + "查看當前社區列表!");



                csmeta.setDisplayName(ChatColor.AQUA + "社區設定選單");
                ArrayList<String> clore = new ArrayList<>();
                clore.add(ChatColor.GREEN + "社區設定選單");
                clore.add(ChatColor.RED + "僅擁有者可使用!");

                back.setItemMeta(backmeta);
                ShowLIst.setItemMeta(listmeta);
                communitySettings.setItemMeta(csmeta);



                Community.setItem(0,back);
                Community.setItem(1,info);
                Community.setItem(4,ShowLIst);
                Community.setItem(8,communitySettings);

                p.openInventory(Community);


            }

            if(args[0].equalsIgnoreCase("cs")) {

                List<String> Pos1L = plugin.data.getPos1();
                List<String> Pos2L = plugin.data.getPos2();
                List<String> Communits = plugin.data.getCommunitys();
                String community = plugin.data.getCommunity(p);
                String c = null;

                double Pos1x = 0;
                double Pos2x = 0;
                double Pos1z = 0;
                double Pos2z = 0;
                double area = 0;
                String Pos1 = null;
                String Pos2 = null;
                for (int cur = 0; cur < plugin.data.CommunityCount(); cur++) {
                    c = Communits.get(cur);
                    if (c == community) {
                        Pos1 = Pos1L.get(cur);
                        Pos2 = Pos2L.get(cur);
                        if (Pos1 == null | Pos2 == null) {
                            return false;
                        }
                        try {
                            double x1 = NumberFormat.getInstance().parse(Pos1.substring(Pos1.indexOf("=") + 1, Pos1.indexOf("z"))).intValue();
                            double z1 = NumberFormat.getInstance().parse(Pos1.substring((Pos1.indexOf("z") + 2))).intValue();

                            double x2 = NumberFormat.getInstance().parse(Pos2.substring(Pos1.indexOf("=") + 1, Pos1.indexOf("z"))).intValue();
                            double z2 = NumberFormat.getInstance().parse(Pos2.substring((Pos1.indexOf("z") + 2))).intValue();
                            Pos1x = Math.max(x1, x2);
                            Pos2x = Math.min(x1, x2);
                            Pos1z = Math.max(z1, z2);
                            Pos2z = Math.min(z1, z2);
                            area = (Pos1x - Pos2x) * (Pos1z - Pos2z);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }


                Inventory Community = Bukkit.createInventory(p, 27, ChatColor.BOLD + "" + ChatColor.GOLD + community + ChatColor.GREEN + "的社區設定選單");
                ItemStack back = new ItemStack(Material.BARRIER);
                ItemStack info = new ItemStack(Material.WRITABLE_BOOK);
                ItemStack ShowLIst = new ItemStack(Material.RESPAWN_ANCHOR);
                ItemStack request = new ItemStack(Material.PAPER);

                ItemMeta backmeta = back.getItemMeta();
                ItemMeta infometa = info.getItemMeta();
                ItemMeta listmeta = ShowLIst.getItemMeta();
                ItemMeta rmeta = request.getItemMeta();

                backmeta.setDisplayName(ChatColor.RED + "回上頁");

                infometa.setDisplayName(ChatColor.LIGHT_PURPLE + "社區:" + ChatColor.GOLD + community);
                ArrayList<String> infoLore = new ArrayList<>();
                infoLore.add(ChatColor.GRAY + "社區等級:" + ChatColor.YELLOW + plugin.data.getLevels(community));
                infoLore.add(ChatColor.GREEN + "社區範圍大小" + ChatColor.GRAY + area + "格");
                infoLore.add(ChatColor.YELLOW + "Pos1:" + ChatColor.GOLD + Pos1);
                infoLore.add(ChatColor.YELLOW + "Pos2:" + ChatColor.GOLD + Pos2);
                infoLore.add(ChatColor.AQUA + "你可以使用社區設定選單進行相關設置");
                infometa.setLore(infoLore);
                info.setItemMeta(infometa);

                listmeta.setDisplayName(ChatColor.YELLOW + "查看社區範圍內的玩家");

                rmeta.setDisplayName(ChatColor.GREEN + "社區加入請求列表");
                ArrayList<String> clore = new ArrayList<>();
                clore.add(ChatColor.GREEN + "社區設定選單");
                clore.add(ChatColor.RED + "僅擁有者可使用!");

                back.setItemMeta(backmeta);
                ShowLIst.setItemMeta(listmeta);
                request.setItemMeta(rmeta);


                Community.setItem(0, back);
                Community.setItem(1, info);
                Community.setItem(4, ShowLIst);
                Community.setItem(8, request);

                p.openInventory(Community);


            }

            if(args[0].equalsIgnoreCase("shop")){
                Inventory shop = Bukkit.createInventory(null,9,ChatColor.BOLD + "" + ChatColor.GREEN + "官方商店");

                ItemStack pointShop = new ItemStack(Material.BELL);
                ItemStack moneyShop = new ItemStack(Material.BARREL);

                ItemMeta pointShopMeta = pointShop.getItemMeta();
                ItemMeta moneyShopMeta = moneyShop.getItemMeta();

                pointShopMeta.setDisplayName(ChatColor.GOLD + "NTIR點數商店");
                ArrayList<String> pointShopLore = new ArrayList<>();
                pointShopLore.add(ChatColor.GRAY + "NTIR點數商店 可使用NTIR點進行消費");
                pointShopMeta.setLore(pointShopLore);
                pointShop.setItemMeta(pointShopMeta);

                moneyShopMeta.setDisplayName(ChatColor.AQUA + "NTIR金錢商店");
                ArrayList<String> moneyShopLore = new ArrayList<>();
                moneyShopLore.add(ChatColor.GRAY + "NTIR金錢商店 可使用NTIR幣進行消費");
                moneyShopMeta.setLore(moneyShopLore);
                moneyShop.setItemMeta(moneyShopMeta);

                shop.setItem(2,pointShop);
                shop.setItem(6,moneyShop);

                p.openInventory(shop);
            }

            if(args[0].equalsIgnoreCase("pointShop")){
                Inventory pointShop = Bukkit.createInventory(null,27,ChatColor.BOLD + "" +ChatColor.GOLD + "NTIR點數商店");

                ItemStack back = new ItemStack(Material.BARRIER);
                ItemStack exchange = new ItemStack(Material.GOLD_INGOT);
                ItemStack effect = new ItemStack(Material.ARMOR_STAND);

                ItemMeta backmeta = back.getItemMeta();
                ItemMeta exchangeMeta = exchange.getItemMeta();
                ItemMeta effectMeta = effect.getItemMeta();

                backmeta.setDisplayName(ChatColor.GRAY + "回上頁");
                back.setItemMeta(backmeta);

                exchangeMeta.setDisplayName(ChatColor.GREEN + "將NTIR點數轉換為NTIR幣");
                ArrayList<String> list = new ArrayList<>();
                list.add(ChatColor.GRAY + "以1:3的比例兌換NTIR幣" + ChatColor.RED + "(每次轉換都至少要消耗10點)");
                list.add(ChatColor.GOLD + "你擁有的點數" + ChatColor.RED + plugin.data.getPoints(p.getUniqueId())*3  + ChatColor.GOLD + "點");
                list.add(ChatColor.GREEN + "你最多可兌換出" + ChatColor.RED + plugin.data.getPoints(p.getUniqueId())*3  + ChatColor.GREEN + "元");
                exchangeMeta.setLore(list);
                exchange.setItemMeta(exchangeMeta);

                effectMeta.setDisplayName(ChatColor.GOLD + "特效時裝商店");
                List<String> effectLore = new ArrayList<>();
                effectLore.add(ChatColor.GRAY + "使用NTIR點數購買特效時裝");
                effectMeta.setLore(effectLore);

                pointShop.setItem(0, back);
                pointShop.setItem(2,exchange);
                pointShop.setItem(5,effect);

                p.openInventory(pointShop);
            }

            if(args[0].equalsIgnoreCase("ChatSettings")){
                Inventory ChatSettings = Bukkit.createInventory(null,9,
                        ChatColor.BOLD + "" + ChatColor.GOLD + "NTIR伺服器" + ChatColor.BLUE + "聊天插件設定");

                ItemStack prefix = new ItemStack(Material.NAME_TAG);
                ItemStack systemSettings =  new ItemStack(Material.TRIPWIRE_HOOK);

                ItemMeta preinfo = prefix.getItemMeta();
                ItemMeta systemSettingMeta = systemSettings.getItemMeta();

                preinfo.setDisplayName(ChatColor.LIGHT_PURPLE + "稱號設置");
                prefix.setItemMeta(preinfo);

                systemSettingMeta.setDisplayName(ChatColor.GOLD + "系統訊息設定");
                List<String> systemLore = new ArrayList<>();
                systemLore.add(ChatColor.GRAY + "設定系統訊息 ex:死亡訊息的顯示...");
                systemSettingMeta.setLore(systemLore);
                systemSettings.setItemMeta(systemSettingMeta);

                ChatSettings.setItem(1,prefix);
                ChatSettings.setItem(3,systemSettings);

                p.openInventory(ChatSettings);
            }

            if(args[0].equalsIgnoreCase("systemSettings")){
                Inventory ChatSettings = Bukkit.createInventory(null,9,
                        ChatColor.BOLD + "" + ChatColor.GOLD + "NTIR伺服器" + ChatColor.BLUE + "系統訊息設定");

                ItemStack deathMsg = new ItemStack(Material.TOTEM_OF_UNDYING);
                ItemStack onlineMsg =  new ItemStack(Material.CLOCK);

                ItemMeta deathMsgMeta = deathMsg.getItemMeta();
                ItemMeta onlineMsgMeta = onlineMsg.getItemMeta();

                deathMsgMeta.setDisplayName(ChatColor.GOLD + "是否顯示死亡訊息");
                List<String> deathLore = new ArrayList<>();
                deathLore.add(ChatColor.GRAY + "當前值: " + ChatColor.RED + plugin.data.enableDeathMsg(p));
                onlineMsgMeta.setLore(deathLore);
                deathMsg.setItemMeta(deathMsgMeta);

                onlineMsgMeta.setDisplayName(ChatColor.GOLD + "是否顯示上下線訊息");
                List<String> onlineLore = new ArrayList<>();
                onlineLore.add(ChatColor.GRAY + "當前值: " + ChatColor.RED + plugin.data.enableOnlineMsg(p));
                onlineMsgMeta.setLore(onlineLore);
                onlineMsg.setItemMeta(onlineMsgMeta);

                ChatSettings.setItem(1,deathMsg);
                ChatSettings.setItem(3,onlineMsg);

                p.openInventory(ChatSettings);
            }

        }

        return false;
    }
}
