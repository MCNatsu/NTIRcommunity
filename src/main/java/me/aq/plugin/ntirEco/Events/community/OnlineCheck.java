package me.aq.plugin.ntirEco.Events.community;

import me.aq.plugin.ntirEco.NTIReco;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.*;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;

public class OnlineCheck implements Listener {

    private NTIReco plugin;

    @EventHandler
    public void onJoin(PlayerJoinEvent e) throws ParseException {
        Player p = e.getPlayer();

        plugin = NTIReco.getPlugin();

        List Pos1List = plugin.data.getPos1();
        List Pos2List = plugin.data.getPos2();
        List Communits = plugin.data.getCommunitys();

        int Pos2x = 0;
        int px1 = 0;
        int Pos1x = 0;
        int Pos2z = 0;
        int pz1 = 0;
        int Pos1z = 0;
        int px2 =0;
        int pz2 = 0;
        String community = "";

        if(plugin.data.getRequest(plugin.data.getCommunity(p)) != null){
            if(plugin.data.getPost(p,plugin.data.getCommunity(p)) != "Owner"){
                return;
            }
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR , new TextComponent(ChatColor.GOLD + "你有待確認的社區加入請求!"));
        }

        for (int cur = 0; cur < plugin.data.CommunityCount(); cur++) {
            String Pos1 = (String) Pos1List.get(cur);
            String Pos2 = (String) Pos2List.get(cur);
            if (Pos1 == null | Pos2 == null) {
                continue;
            }
            int x1 = NumberFormat.getInstance().parse(Pos1.substring(Pos1.indexOf("=") + 1, Pos1.indexOf("z"))).intValue();
            int z1 = NumberFormat.getInstance().parse(Pos1.substring((Pos1.indexOf("z") + 2))).intValue();

            int x2 = NumberFormat.getInstance().parse(Pos2.substring(Pos1.indexOf("=") + 1, Pos1.indexOf("z"))).intValue();
            int z2 = NumberFormat.getInstance().parse(Pos2.substring((Pos1.indexOf("z") + 2))).intValue();
            Pos1x = Math.max(x1, x2);
            Pos2x = Math.min(x1, x2);
            Pos1z = Math.max(z1, z2);
            Pos2z = Math.min(z1, z2);

            px1 = (int) p.getLocation().getX();
            pz1 = (int) p.getLocation().getZ();

            community = String.valueOf(Communits.get(cur));
            if (Pos2x <= px1 && px1 <= Pos1x && Pos2z <= pz1 && pz1 <= Pos1z) {
                community = String.valueOf(Communits.get(cur));
                break;
            }
        }

        if (Pos1x <= px1 && px1 <= Pos2x && Pos1z <= pz1 && pz1 <= Pos2z) {
            return;
        }

        if (px1 < Pos2x | px1 > Pos1x | pz1 > Pos1z | pz1 < Pos2z) {
            if (plugin.data.existDATAinCommunity(p, community)) {
                plugin.data.LeaveCommunity(p, community);
                p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
            }
            return;
        }

        if (Pos2x <= px1 && px1 <= Pos1x && Pos2z <= pz1 && pz1 <= Pos1z) {
            if (plugin.data.getCommunity(p) != community && !plugin.data.existDATAinCommunity(p,community)) {
                plugin.data.visitCommunity(p, community, "Visitor");
            }
            plugin.data.EnterCommunity(p,community);

            ScoreboardManager Manager = Bukkit.getScoreboardManager();
            Scoreboard Info = Manager.getNewScoreboard();
            Objective InfoO = Info.registerNewObjective("info", "01", ChatColor.YELLOW + ":社區資訊:");
            InfoO.setDisplaySlot(DisplaySlot.SIDEBAR);

            Score NAME = InfoO.getScore(ChatColor.GOLD + "社區:" + ChatColor.AQUA + community);
            Score Owner = InfoO.getScore(ChatColor.GREEN + "擁有者:" + ChatColor.LIGHT_PURPLE + plugin.data.getOwner(community).getName());
            Score Lelves = InfoO.getScore(ChatColor.GRAY + "社區等級:" + ChatColor.YELLOW + plugin.data.getLevels(community));


            if (Objects.equals(plugin.data.getCommunity(p), community)) {
                Score isMember = InfoO.getScore(ChatColor.GREEN + "你是該社區的成員!");
                isMember.setScore(1);
            }

            if (!Objects.equals(plugin.data.getCommunity(p), community)) {
                Score isMember = InfoO.getScore(ChatColor.RED + "你不是該社區的成員!");
                isMember.setScore(1);
            }
            NAME.setScore(4);
            Owner.setScore(3);
            Lelves.setScore(2);

            if (Pos2x <= px2 && px2 <= Pos1x && Pos2z <= pz2 && pz2 <= Pos1z) {
                if(p.getScoreboard().getObjective(DisplaySlot.SIDEBAR) == Info.getObjective(DisplaySlot.SIDEBAR)){
                    return;
                }

                p.setScoreboard(Info);

                return;
            }


            p.sendTitle(ChatColor.RED + "你已經進入" + ChatColor.YELLOW + community + ChatColor.AQUA + "所宣告的地區內", ChatColor.GREEN + "擁有者:" + ChatColor.LIGHT_PURPLE + plugin.data.getOwner(community).getPlayer().getDisplayName(), 20, 60, 20);
            p.setScoreboard(Info);
        }
    }
}
