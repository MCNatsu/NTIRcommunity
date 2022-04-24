package me.aq.plugin.ntirEco.SQL;

import me.aq.plugin.ntirEco.NTIReco;
import net.dv8tion.jda.api.entities.Member;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SQLediter {

    private NTIReco plugin;

    public void SQLGetter(NTIReco plugin){
        this.plugin = plugin;

    }
    public void createTable(){

        PreparedStatement ps;
        PreparedStatement ps2;
        PreparedStatement ps3;
        PreparedStatement ps4;
        PreparedStatement ps5;
        PreparedStatement ps6;
        PreparedStatement ps7;
        PreparedStatement ps8;
        PreparedStatement ps9;
        PreparedStatement ps10;
        try {
            ps = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS money "
                    + "(NAME VARCHAR(100), UUID VARCHAR(100), money INT(100), PRIMARY KEY(NAME))");

            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            ps2 = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Daily "
                    + "(Player VARCHAR(100), UUID VARCHAR(100), SignedDays INT(100), Signed BOOLEAN, PRIMARY KEY(UUID))");

            ps2.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            ps3 = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS playerCommunity"
                    + "(NAME VARCHAR(100), UUID VARCHAR(100), Community VARCHAR(100), PRIMARY KEY(NAME))");
            ps3.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            ps4 = plugin.SQL.getCommunityDATA().prepareStatement("CREATE TABLE IF NOT EXISTS Communitylist"
                    + "(Community VARCHAR(100) ,Owner VARCHAR(100), OwnerUUID VARCHAR(100),Pos1 VARCHAR(100),Pos2 VARCHAR(100),Levels INT(100),PRIMARY KEY(Community))");
            ps4.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            ps5 = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS LinkList"
                    + "(PlayerName VARCHAR(100), PlayerUUID VARCHAR(100), dcName VARCHAR(100) ,DiscordID VARCHAR(100) , Linked VARCHAR(100), LinkedCode VARCHAR(100), PRIMARY KEY(PlayerName))");
            ps5.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            ps6 = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS ChatLog"
                    + "(Player VARCHAR(100), UUID VARCHAR(100), Message VARCHAR(100) ,Server VARCHAR(100) ,date VARCHAR(100) ,PRIMARY KEY(date))");
            ps6.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            ps7 = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS TempBanList"
                    + "(Player VARCHAR(100), UUID VARCHAR(100), Reason VARCHAR(100) ,Time VARCHAR(100) ,date VARCHAR(100),unBanDate VARCHAR(100),PRIMARY KEY(Player))");
            ps7.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ps8 = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS GetEasterEGG"
                    + "(Player VARCHAR(100), UUID VARCHAR(100), GETTED BOOLEAN ,PRIMARY KEY(UUID))");
            ps8.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            ps9 = plugin.SQL.getCommunityDATA().prepareStatement("CREATE TABLE IF NOT EXISTS CommunityJoinRequest"
                    + "(Player VARCHAR(100), UUID VARCHAR(100), Community VARCHAR(100) ,PRIMARY KEY(Player))");
            ps9.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ps10 = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS BanList"
                    + "(Player VARCHAR(100), UUID VARCHAR(100), Reason VARCHAR(100) ,date VARCHAR(100),PRIMARY KEY(Player))");
            ps10.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public void  createPlayer(Player player){

        try {
            UUID uuid = player.getUniqueId();
            if(!exists(uuid)){

                PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("INSERT INTO money (NAME,UUID,money) VALUES (?,?,?)");
                ps.setString(1, player.getName());
                ps.setString(2, uuid.toString());
                ps.setInt(3, 0);
                ps.executeUpdate();

            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        try {
            UUID uuid = player.getUniqueId();

            if(!existsPlayerCommunity(uuid)){

                PreparedStatement ps3 = plugin.SQL.getConnection().prepareStatement("INSERT INTO playerCommunity (NAME,UUID,Community) VALUES (?,?,?)");
                ps3.setString(1, player.getName());
                ps3.setString(2, uuid.toString());
                ps3.setString(3, null);
                ps3.executeUpdate();

                return;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        try {
            UUID uuid = player.getUniqueId();
            if(!existDaily(uuid)){

                PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("INSERT INTO Daily (Player,UUID,SignedDays,Signed) VALUES (?,?,?,?)");
                ps.setString(1, player.getName());
                ps.setString(2, uuid.toString());
                ps.setInt(3, 0);
                ps.setBoolean(4,false);
                ps.executeUpdate();

            }

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    /*public void deleteCommunity(Player p){

        try {
            UUID uuid = p.getUniqueId();

            if(!existsPlayerCommunity(p.getUniqueId())){

                PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("DROP TABLE " + );
                ps.setString(1, community);
                ps.setString(2, p.getName());
                ps.setString(3, uuid.toString());
                ps.setInt(4, 1);

                ps.executeUpdate();


                return;
            }


        }catch (SQLException e){
            e.printStackTrace();
        }

    }*/
    //社區功能
    public void createCommunity(Player p, String community) {

        try {
            UUID uuid = p.getUniqueId();

            if(!existsCommunity(community)){

                PreparedStatement ps = plugin.SQL.getCommunityDATA().prepareStatement("INSERT INTO Communitylist (Community,Owner,OwnerUUID,Pos1,Pos2,Levels) VALUES (?,?,?,?,?,?)");
                PreparedStatement ps1 = plugin.SQL.getCommunityDATA().prepareStatement("CREATE TABLE If NOT EXISTS " + community +
                        "(Members VARCHAR(100), MembersUUID VARCHAR(100), Position VARCHAR(100),InRange BOOLEAN, PRIMARY KEY(Members))");
                PreparedStatement ps2 = plugin.SQL.getCommunityDATA().prepareStatement("INSERT INTO " + community + "(Members,MembersUUID,Position,InRange) VALUES(?,?,?,?)");
                ps.setString(1, community);
                ps.setString(2, p.getName());
                ps.setString(3, uuid.toString());
                ps.setString(4,null);
                ps.setString(5,null);
                ps.setInt(6, 1);

                ps2.setString(1,p.getDisplayName());
                ps2.setString(2,p.getUniqueId().toString());
                ps2.setString(3,"Owner");
                ps2.setBoolean(4,false);

                ps.executeUpdate();
                ps1.executeUpdate();
                ps2.executeUpdate();
                setCommunity(p.getUniqueId(),community);

                return;
            }


        }catch (SQLException e){
            e.printStackTrace();
        }


    }
    public void LeaveCommunity(Player p,String community){
        try {
            PreparedStatement ps = plugin.SQL.getCommunityDATA().prepareStatement("UPDATE " + community + " SET InRange=? WHERE MembersUUID=?");
            ps.setBoolean(1,false);
            ps.setString(2,p.getUniqueId().toString());
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void visitCommunity(Player p,String community,String Post){
        try {
            PreparedStatement ps = plugin.SQL.getCommunityDATA().prepareStatement("INSERT INTO " + community + "(Members,MembersUUID,Position,InRange) VALUES(?,?,?,?)");
            ps.setString(1,p.getDisplayName());
            ps.setString(2,p.getUniqueId().toString());
            ps.setString(3,Post);
            ps.setBoolean(4,true);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void EnterCommunity(Player p,String community){
        try {
            PreparedStatement ps = plugin.SQL.getCommunityDATA().prepareStatement("UPDATE " + community + " SET InRange=? WHERE MembersUUID=?");
            ps.setBoolean(1,true);
            ps.setString(2,p.getUniqueId().toString());
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void setCommunity(UUID uuid, String Community){

        try {
            if(existsCommunity(Community)) {
                PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE playerCommunity SET Community=? WHERE UUID=?");
                ps.setString(1, (Community));
                ps.setString(2, uuid.toString());
                ps.executeUpdate();
                if(plugin.data.existDATAinCommunity(Bukkit.getOfflinePlayer(uuid),Community)){
                    PreparedStatement ps1 = plugin.SQL.getCommunityDATA().prepareStatement("UPDATE " + Community + " SET Position=? WHERE MembersUUID=?");
                    ps1.setString(1,"Member");
                    ps1.setString(2,uuid.toString());
                    ps1.executeUpdate();
                }

                if(!plugin.data.existDATAinCommunity(Bukkit.getOfflinePlayer(uuid),Community)){
                    PreparedStatement ps1 = plugin.SQL.getCommunityDATA().prepareStatement("INSERT INTO " + Community + "(Members,MembersUUID,Position,InRange) VALUES(?,?,?,?)");
                    ps.setString(1,Bukkit.getOfflinePlayer(uuid).getName());
                    ps1.setString(2,uuid.toString());
                    ps1.setString(3,"Member");
                    ps1.setBoolean(4,false);
                    ps1.executeUpdate();
                }
            }else {
                return;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }


    }
    public void setPos1(Location loc, String community){
        try {
            PreparedStatement ps = plugin.SQL.getCommunityDATA().prepareStatement("UPDATE Communitylist SET Pos1=? WHERE Community=?");
            String pos1 = "x=" + loc.getX() + "z=" + loc.getZ();
            ps.setString(1,pos1);
            ps.setString(2,community);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void setPos2(Location loc, String community){
        try {
            PreparedStatement ps = plugin.SQL.getCommunityDATA().prepareStatement("UPDATE Communitylist SET Pos2=? WHERE Community=?");
            String pos2 = "x=" + loc.getX() + "z=" + loc.getZ();
            ps.setString(1,pos2);
            ps.setString(2,community);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void JoinRequest(Player p,String community){
        try {
            if(haveRequest(p)){
                return;
            }
            PreparedStatement ps = plugin.SQL.getCommunityDATA().prepareStatement("INSERT INTO CommunityJoinRequest (Player,UUID,Community) VALUES(?,?,?)");
            ps.setString(1, p.getDisplayName());
            ps.setString(2, p.getUniqueId().toString());
            ps.setString(3, community);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void DelJoinRequest(OfflinePlayer p){
        try {
            if(!haveRequest(p)){
                return;
            }
            PreparedStatement ps = plugin.SQL.getCommunityDATA().prepareStatement("DELETE FROM CommunityJoinRequest WHERE UUID=?");
            ps.setString(1, p.getUniqueId().toString());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean existsPlayerCommunity(UUID uuid){

        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT UUID FROM playerCommunity WHERE UUID=?");
            ps.setString(1, uuid.toString());

            ResultSet results = ps.executeQuery();
            if(results.next()){
                return true;
            }
            return false;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;

    }
    public boolean Communityexists(String community){
        try {
            PreparedStatement ps = plugin.SQL.getCommunityDATA().prepareStatement("SELECT Community FROM Communitylist WHERE Community=?");
            ps.setString(1, community);

            ResultSet results = ps.executeQuery();
            if(results.next()){
                return true;
            }
            return false;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean existsCommunity(String community){

        try {
            PreparedStatement ps = plugin.SQL.getCommunityDATA().prepareStatement("SELECT Community FROM Communitylist WHERE Community=?");
            ps.setString(1, community);

            ResultSet results = ps.executeQuery();
            if(results.next()){
                return true;
            }
            return false;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;

    }
    public boolean inRange(String community,Player p){
        try {
            PreparedStatement ps = plugin.SQL.getCommunityDATA().prepareStatement("SELECT InRange FROM " + community + " WHERE MembersUUID=?");
            ps.setString(1,p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getBoolean("InRange");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean existDATAinCommunity(OfflinePlayer p ,String community){
        try {
            PreparedStatement ps = plugin.SQL.getCommunityDATA().prepareStatement("SELECT MembersUUID FROM " + community + " WHERE MembersUUID=?");
            ps.setString(1,p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean haveRequest(OfflinePlayer p){
        try {
            PreparedStatement ps = plugin.SQL.getCommunityDATA().prepareStatement("SELECT UUID FROM CommunityJoinRequest WHERE UUID=?");
            ps.setString(1,p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public List inCommunity(String community){
        try {
            PreparedStatement ps = plugin.SQL.getCommunityDATA().prepareStatement("SELECT Members FROM " + community + " WHERE InRange=?");
            ps.setBoolean(1,true);
            ResultSet rs = ps.executeQuery();
            List list = new ArrayList<>();
            list.clear();
            if(rs.next()) {
                for (int cur = 0; cur < InCount(community); cur++, rs.next()) {
                    list.add(cur, rs.getString("Members"));

                }
            }
            return list;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public List getPos1(){
        try {
            List<String> Pos1List = new ArrayList<>();
            Pos1List.clear();
            PreparedStatement ps = plugin.SQL.getCommunityDATA().prepareStatement("SELECT Pos1 FROM Communitylist");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                for (int cur=0; cur < CommunityCount(); cur++,rs.next()){
                    String s = rs.getString("Pos1");
                    Pos1List.add(cur, s);
                }
                return Pos1List;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public List getPos2(){
        try {
            List<String> Pos2List = new ArrayList<>();
            Pos2List.clear();
            PreparedStatement ps = plugin.SQL.getCommunityDATA().prepareStatement("SELECT Pos2 FROM Communitylist");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                for (int cur=0; cur < CommunityCount(); cur++,rs.next()){
                    String s = rs.getString("Pos2");
                    Pos2List.add(cur, s);
                }
                return Pos2List;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public List getCommunitys(){
        try {
            List<String> PosList = new ArrayList<>();
            PosList.clear();
            PreparedStatement ps = plugin.SQL.getCommunityDATA().prepareStatement("SELECT Community FROM CommunityList");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                for (int cur=0; cur < CommunityCount(); cur++,rs.next()){
                    PosList.add(rs.getString("Community"));
                }
                return PosList;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public List<OfflinePlayer> getRequest(String community){
        try {
            PreparedStatement ps = plugin.SQL.getCommunityDATA().prepareStatement("SELECT UUID FROM CommunityJoinRequest WHERE Community=?");
            ps.setString(1,community);
            ResultSet rs = ps.executeQuery();
            List<OfflinePlayer> list = new ArrayList<>();
            if(rs.next()){
                for (int cur =0; cur < RequestCount(community);cur++,rs.next()){
                    list.add(cur,Bukkit.getOfflinePlayer(UUID.fromString(rs.getString("UUID"))));
                }
                return list;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public int InCount(String community){
        try {
            PreparedStatement ps = plugin.SQL.getCommunityDATA().prepareStatement("SELECT COUNT(MembersUUID) AS COUNT FROM " + community + " WHERE InRange=?");
            ps.setBoolean(1,true);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("COUNT");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public int CommunityCount(){
        try {
            PreparedStatement ps = plugin.SQL.getCommunityDATA().prepareStatement("SELECT COUNT(Community) AS COUNT FROM Communitylist");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int count = rs.getInt("COUNT");
                return count;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public int getLevels(String community){
        try {
            PreparedStatement ps = plugin.SQL.getCommunityDATA().prepareStatement("SELECT Levels FROM Communitylist WHERE Community=?");
            ps.setString(1,community);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("Levels");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public int RequestCount(String community){
        try {
            PreparedStatement ps = plugin.SQL.getCommunityDATA().prepareStatement("SELECT COUNT(UUID) AS COUNT FROM CommunityJoinRequest WHERE Community=?");
            ps.setString(1,community);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("COUNT");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public String getCommunity(OfflinePlayer p){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT Community FROM playerCommunity WHERE UUID=?");
            ps.setString(1,p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getString("Community");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public String getPost(Player p,String community){
        try {
            PreparedStatement ps = plugin.SQL.getCommunityDATA().prepareStatement("SELECT Position FROM " + community + " WHERE MembersUUID=?");
            ps.setString(1,p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getString("Position");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public String getRequestedCommunity(OfflinePlayer p){
        try {
            PreparedStatement ps = plugin.SQL.getCommunityDATA().prepareStatement("SELECT Community FROM CommunityJoinRequest WHERE UUID=?");
            ps.setString(1,p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getString("Community");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public OfflinePlayer getOwner(String community){
        try {
            PreparedStatement ps = plugin.SQL.getCommunityDATA().prepareStatement("SELECT OwnerUUID FROM Communitylist WHERE Community=?");
            ps.setString(1, community);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return Bukkit.getOfflinePlayer(UUID.fromString( rs.getString("OwnerUUID")));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    //玩家自訂義設定
    public void setpvp(Player p,Boolean b){
        try {

            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE PlayerChatControl SET pvp=? WHERE UUID=?");
            ps.setBoolean(1,b);
            ps.setString(2,p.getUniqueId().toString());
            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public boolean existsPlayerControl(Player p){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT UUID FROM PlayerChatControl WHERE UUID=?");
            ps.setString(1,p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
            return false;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean enablePVP(Player p){
        try {

            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT pvp FROM PlayerChatControl WHERE UUID=?");
            ps.setString(1,p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getBoolean("pvp");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    //NTIR點數
    public void addpoint(UUID uuid, int points){

        try{
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE money SET money=? WHERE UUID=?");
            ps.setInt(1, (getPoints(uuid) + points));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public void setPoint(UUID uuid, int points){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE money SET money=? WHERE UUID=?");
            ps.setInt(1, (points));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void addMoney(double money,String uuid){
        try {

            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE MainBalance SET balance=? WHERE UUID=?");
            ps.setDouble(1,GETMoney(uuid) + money);
            ps.setString(2,uuid);
            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean exists(UUID uuid){

        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT UUID FROM money WHERE UUID=?");
            ps.setString(1, uuid.toString());

            ResultSet results = ps.executeQuery();
            if(results.next()){
                return true;
            }
            return false;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;

    }

    public int getPoints(UUID uuid){


        try {
            PreparedStatement ps2 = plugin.SQL.getConnection().prepareStatement("SELECT money FROM money WHERE UUID=?");
            ps2.setString(1, uuid.toString());
            ResultSet rs = ps2.executeQuery();
            int point = 0;
            if(rs.next()){
                point = rs.getInt("money");
                return point;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double GETMoney(String uuid){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT balance FROM MainBalance WHERE UUID=?");
            ps.setString(1,uuid);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                double money = rs.getDouble("balance");
                return money;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    //封鎖
    public void TempBan(OfflinePlayer p , String reason, Long time){

        SimpleDateFormat date1 = new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss.SSS");
        long time1 = System.currentTimeMillis() + time;
        Date current = new Date();
        Date unban = new Date(time1);

        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("INSERT INTO TempBanList (Player,UUID,Reason,Time,date,unBanDate) VALUES(?,?,?,?,?,?)");
            ps.setString(1,p.getName());
            ps.setString(2,p.getUniqueId().toString());
            ps.setString(3,reason);
            ps.setString(4, String.valueOf(time));
            ps.setString(5,date1.format(current));
            ps.setString(6,date1.format(unban));
            ps.executeUpdate();
            if(p.isOnline()){
                Player target = (Player) p;
                target.kickPlayer(plugin.tempBanMessage.banMessage(reason,date1.format(unban)));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void unBan(OfflinePlayer p){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("DELETE FROM TempBanList WHERE UUID=?");
            ps.setString(1,p.getUniqueId().toString());
            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void Ban(OfflinePlayer p, String reason){
        SimpleDateFormat date1 = new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss.SSS");
        Date current = new Date();
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("INSERT INTO BanList (Player,UUID,Reason,date) VALUES(?,?,?,?)");
            ps.setString(1,p.getName());
            ps.setString(2,p.getUniqueId().toString());
            ps.setString(3,reason);
            ps.setString(4, date1.format(current));
            ps.executeUpdate();
            if(p.isOnline()){
                Player target = (Player) p;
                target.kickPlayer(plugin.BanMessage.banMessage(reason));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public String getUnBanDate(OfflinePlayer p){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT unBanDate FROM TempBanList WHERE UUID=?");
            ps.setString(1,p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            String date = null;
            if(rs.next()){
                date = rs.getString("unBanDate");
                return date;
            }
            return null;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;

    }
    public boolean tempBanned(Player p){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT UUID FROM TempBanList WHERE UUID=?");
            ps.setString(1,p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
            return false;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean Banned(Player p){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT UUID FROM BanList WHERE UUID=?");
            ps.setString(1,p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
            return false;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    //稱號
    public void setPrefix(UUID uuid, String prefix){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE PlayerChatSettings SET prefix=? WHERE UUID=?");
            ps.setString(1, (prefix));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public boolean existsprefix(UUID uuid){

        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT UUID FROM PlayerChatSettings WHERE UUID=?");
            ps.setString(1, uuid.toString());

            ResultSet results = ps.executeQuery();
            if(results.next()){
                return true;
            }
            return false;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;

    }
    public String getPrefix(UUID uuid){


        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT prefix FROM PlayerChatSettings WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            String prefix = null;
            if(rs.next()){
                prefix = rs.getString("prefix");
                return prefix;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getuuid(String  name) {
        try {
            PreparedStatement ps2 = plugin.SQL.getConnection().prepareStatement("SELECT UUID FROM PlayerChatSettings WHERE Player=?");
            ps2.setString(1,name);
            ResultSet rs = ps2.executeQuery();
            if(rs.next()){
                String uuid = rs.getString("UUID");
                return uuid;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    //chat
    public void Log(Player p, String motd, String message){

        SimpleDateFormat date1 = new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss.SSS");
        Date current = new Date();

        try {

            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("INSERT INTO ChatLog (Player,UUID,Message,Server,date) VALUES(?,?,?,?,?)");
            ps.setString(1,p.getDisplayName());
            ps.setString(2,p.getUniqueId().toString());
            ps.setString(3,message);
            ps.setString(4,motd);
            ps.setString(5,date1.format(current).toString());
            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }


    }

    //簽到
    public boolean existDaily(UUID uuid){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT UUID FROM Daily WHERE UUID=?");
            ps.setString(1, uuid.toString());

            ResultSet results = ps.executeQuery();
            if(results.next()){
                return true;
            }
            return false;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public void sign(Player p) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE  daily SET Signed=? WHERE UUID=?");
            PreparedStatement ps1 = plugin.SQL.getConnection().prepareStatement("UPDATE daily SET SignedDays=? WHERE UUID=?");
            ps.setBoolean(1,true);
            ps.setString(2,p.getUniqueId().toString());
            ps.executeUpdate();
            if(getSignedDays(p) >= 7){
                ps1.setInt(1,0);
                ps1.setString(2,p.getUniqueId().toString());
                ps1.executeUpdate();
                return;
            }
            ps1.setInt(1,getSignedDays(p) + 1);
            ps1.setString(2,p.getUniqueId().toString());
            ps.executeUpdate();
            ps1.executeUpdate();


        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public boolean signed(Player p) {

        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT Signed FROM daily WHERE UUID=?");
            ps.setString(1,p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getBoolean("Signed");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return true;
    }
    public boolean UPtoDATE(){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT UUID FROM daily WHERE Player=?");
            ps.setString(1,"LastUPDATE");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String LastUPDATE = rs.getString("UUID");
                SimpleDateFormat SF = new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss.SSS");
                Date Last = SF.parse(LastUPDATE);
                long LastMillis = Last.getTime();
                if(new Date().getTime() - LastMillis > 24*60*60*1000){
                    return true;
                }
            }
        }catch (SQLException | ParseException e){
            e.printStackTrace();
        }
        return false;
    }
    public void UPDATE(){
        try {
            long Last = GetLast();
            SimpleDateFormat date1 = new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss.SSS");
            Date date = new Date(Last+24*60*60*1000);
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE daily SET SignedDays=? WHERE Signed=?");
            PreparedStatement ps1 = plugin.SQL.getConnection().prepareStatement("UPDATE daily SET Signed=?");
            PreparedStatement ps2 = plugin.SQL.getConnection().prepareStatement("UPDATE daily SET UUID=? WHERE Player=?");
            ps.setInt(1,0);
            ps.setBoolean(2,false);
            ps.executeUpdate();
            ps1.setBoolean(1,false);
            ps1.executeUpdate();
            ps2.setString(1,date1.format(date));
            ps2.setString(2,"LastUPDATE");
            ps2.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public long GetLast(){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT UUID FROM daily WHERE Player=?");
            ps.setString(1,"LastUPDATE");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String LastUPDATE = rs.getString("UUID");
                SimpleDateFormat SF = new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss.SSS");
                Date Last = SF.parse(LastUPDATE);
                long LastMillis = Last.getTime();
                return LastMillis;
            }
        }catch (SQLException | ParseException e){
            e.printStackTrace();
        }
        return new Date().getTime();
    }

    public int getSignedDays(Player p){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT SignedDays FROM daily WHERE UUID=?");
            ps.setString(1,p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("SignedDays");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    //節慶領取
    public void getReward(Player p){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("INSERT INTO GetEasterEGG (Player, UUID,GETTED) VALUES(?,?,?)");
            ps.setString(1,p.getName());
            ps.setString(2,p.getUniqueId().toString());
            ps.setBoolean(3,true);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean getted(Player p){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT UUID FROM GetEasterEGG WHERE UUID=?");
            ps.setString(1,p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
