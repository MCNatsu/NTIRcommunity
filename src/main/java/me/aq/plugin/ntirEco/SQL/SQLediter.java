package me.aq.plugin.ntirEco.SQL;

import me.aq.plugin.ntirEco.NTIReco;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.utils.WidgetUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

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
        try {
            ps = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS money "
                    + "(NAME VARCHAR(100), UUID VARCHAR(100), money INT(100), PRIMARY KEY(NAME))");

            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            ps2 = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS playerPrefix "
                    + "(NAME VARCHAR(100), UUID VARCHAR(100), prefix VARCHAR(100), PRIMARY KEY(NAME))");

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
            ps4 = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Communitylist"
                    + "(Community VARCHAR(100) ,Owner VARCHAR(100), OwnerUUID VARCHAR(100), Levels INT(100), PRIMARY KEY(Community))");
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

            if(!existsprefix(uuid)){

                PreparedStatement ps2 = plugin.SQL.getConnection().prepareStatement("INSERT INTO playerprefix (NAME,UUID,prefix) VALUES (?,?,?)");
                ps2.setString(1, player.getName());
                ps2.setString(2, uuid.toString());
                ps2.setString(3, ChatColor.AQUA + "[玩家]");
                ps2.executeUpdate();

                return;
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

    }

    public void createCommunity(Player p, String community) {

        try {
            UUID uuid = p.getUniqueId();

            if(!existsCommunity(community)){

                PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("INSERT INTO Communitylist (Community,Owner,OwnerUUID,Levels) VALUES (?,?,?,?)");
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


    }

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

    public void verify(Player p , String LinkCode){

        try {
            UUID uuid = p.getUniqueId();

            if(!existsverified(uuid)){

                PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("INSERT INTO LinkList (PlayerName,PlayerUUID, dcName,DiscordID, LinkedCode) VALUES (?,?,?,?,?)");
                ps.setString(1, p.getDisplayName());
                ps.setString(2, uuid.toString());
                ps.setString(3, null);
                ps.setString(4, null);
                ps.setString(5, LinkCode);
                ps.executeUpdate();

            }


        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void verifydc(Member member, String LinkCode){

        try {
            String DCId = member.getId();


                PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE LinkList SET dcName=? ,DiscordID=?, Linked=? WHERE LinkedCode=?");
                ps.setString(1, member.getEffectiveName());
                ps.setString(2, DCId);
                ps.setString(3, "true");
                ps.setString(4, LinkCode);
                ps.executeUpdate();
                return;


        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public Player getPlayer(String linkCode){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT PlayerName FROM LinkList WHERE LinkedCode=?");
            ps.setString(1, linkCode);
            ResultSet rs = ps.executeQuery();
            String name = null;
            if(rs.next()){
                name = rs.getString("PlayerNAME");
                Player p = Bukkit.getPlayerExact(name);
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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

    public boolean existsprefix(UUID uuid){

        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT UUID FROM playerprefix WHERE UUID=?");
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

    public boolean existsverified(UUID uuid){

        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT PlayerUUID FROM LinkList WHERE PlayerUUID=?");
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

    public boolean verified(UUID uuid){

        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT Linked FROM LinkList WHERE PlayerUUID=?");
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
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT Community FROM Communitylist WHERE Community=?");
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
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT Community FROM Communitylist WHERE Community=?");
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

    public void setPrefix(UUID uuid, String prefix){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE playerprefix SET prefix=? WHERE UUID=?");
            ps.setString(1, (prefix));
            ps.setString(2, uuid.toString());
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
            }else {
                return;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }


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
    public String getPrefix(UUID uuid){


        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT prefix FROM playerprefix WHERE UUID=?");
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

    public String getVerifyCode(UUID uuid){


        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT LinkedCode FROM LinkList WHERE PlayerUUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            String linkCode = null;
            if(rs.next()){
                linkCode = rs.getString("LinkedCode");
                return linkCode;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
