package me.aq.plugin.ntirEco.Events.community;

import me.aq.plugin.ntirEco.NTIReco;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scoreboard.*;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

public class EnterCommunity implements Listener {

    private NTIReco plugin;

    @EventHandler
    public void onMove(PlayerMoveEvent e) throws ParseException {

        plugin = NTIReco.getPlugin();
        if (e.getFrom().getX() == e.getTo().getX() | e.getFrom().getZ() == e.getTo().getZ() | e.getFrom().getY() == e.getTo().getY()) {
            return;
        }
        List Pos1List = plugin.data.getPos1();
        List Pos2List = plugin.data.getPos2();
        List Communits = plugin.data.getCommunitys();
        Player p = e.getPlayer();


        double Pos2x = 0;
        double px1 = 0;
        double Pos1x = 0;
        double Pos2z = 0;
        double pz1 = 0;
        double Pos1z = 0;
        double px2 = 0;
        double pz2 = 0;
        String community = new String();
        int cur;
        for (cur = 0; cur < plugin.data.CommunityCount(); cur++) {
            String Pos1 = (String) Pos1List.get(cur);
            String Pos2 = (String) Pos2List.get(cur);
            if (Pos1 == null | Pos2 == null) {
                continue;
            }
            double x1 = NumberFormat.getInstance().parse(Pos1.substring(Pos1.indexOf("=") + 1, Pos1.indexOf("z"))).intValue();
            double z1 = NumberFormat.getInstance().parse(Pos1.substring((Pos1.indexOf("z") + 2))).intValue();

            double x2 = NumberFormat.getInstance().parse(Pos2.substring(Pos1.indexOf("=") + 1, Pos1.indexOf("z"))).intValue();
            double z2 = NumberFormat.getInstance().parse(Pos2.substring((Pos1.indexOf("z") + 2))).intValue();
            Pos1x = Math.max(x1, x2);
            Pos2x = Math.min(x1, x2);
            Pos1z = Math.max(z1, z2);
            Pos2z = Math.min(z1, z2);

            px1 =  e.getTo().getX();
            pz1 =  e.getTo().getZ();

            px2 =  e.getFrom().getX();
            pz2 =  e.getFrom().getZ();
            community = String.valueOf(Communits.get(cur));
            if (Pos2x <= px1 && px1 <= Pos1x && Pos2z <= pz1 && pz1 <= Pos1z) {
                community = String.valueOf(Communits.get(cur));
                break;
            }
        }

        if (px1 < Pos2x | px1 > Pos1x | pz1 > Pos1z | pz1 < Pos2z) {
            if(Pos2x <= px2 && px2 <= Pos1x && Pos2z <= pz2 && pz2 <= Pos1z) {
                if (plugin.data.existDATAinCommunity(p, community) && plugin.data.inRange(community,p)) {
                    plugin.data.LeaveCommunity(p, community);
                    p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
                }
                return;
            }
            return;
        }

        if (Pos2x <= px1 && px1 <= Pos1x && Pos2z <= pz1 && pz1 <= Pos1z) {
            if (plugin.data.getCommunity(p) != community && !plugin.data.existDATAinCommunity(p,community)) {
                plugin.data.visitCommunity(p, community, "Visitor");
            }




            ScoreboardManager Manager = Bukkit.getScoreboardManager();
            Scoreboard Info = Manager.getNewScoreboard();
            Objective InfoO = Info.registerNewObjective("info", "01", ChatColor.YELLOW + ":社區資訊:");
            InfoO.setDisplaySlot(DisplaySlot.SIDEBAR);

            Score NAME = InfoO.getScore(ChatColor.GOLD + "社區:" + ChatColor.AQUA + community);
            Score Owner = InfoO.getScore(ChatColor.GREEN + "擁有者:" + ChatColor.LIGHT_PURPLE + plugin.data.getOwner(community).getName());
            Score Lelves = InfoO.getScore(ChatColor.GRAY + "社區等級:" + ChatColor.YELLOW + plugin.data.getLevels(community));

            if (Pos2x <= px2 && px2 <= Pos1x && Pos2z <= pz2 && pz2 <= Pos1z) {
                return;
            }


            if (Objects.equals(plugin.data.getCommunity(p), community)) {
                Score isMember = InfoO.getScore(ChatColor.GREEN + "你是該社區的成員!");
                isMember.setScore(1);
            }

            if (!Objects.equals(plugin.data.getCommunity(p), community)) {
                Score isMember = InfoO.getScore(ChatColor.RED + "你不是該社區的成員!");
                if (plugin.data.getOwner(community).isOnline()){
                    plugin.data.getOwner(community).getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR,new TextComponent(ChatColor.GRAY + "[" + ChatColor.RED + "社區警告" + ChatColor.GRAY + "]" + " 玩家" + p.getDisplayName() + "進入了你的社區範圍" + ChatColor.RED + "(不是社區成員!)") );
                }
                isMember.setScore(1);
            }
            NAME.setScore(4);
            Owner.setScore(3);
            Lelves.setScore(2);
            plugin.data.EnterCommunity(p,community);


            p.sendTitle(ChatColor.RED + "你已經進入" + ChatColor.YELLOW + community + ChatColor.AQUA + "所宣告的地區內", ChatColor.GREEN + "擁有者:" + ChatColor.LIGHT_PURPLE + plugin.data.getOwner(community).getPlayer().getDisplayName(), 20, 60, 20);
            p.setScoreboard(Info);
        }
    }
}
