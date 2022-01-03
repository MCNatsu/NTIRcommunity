package me.aq.plugin.ntirEco.SQL;

import me.aq.plugin.ntirEco.NTIReco;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                    + "(dcName VARCHAR(100) ,DiscordID VARCHAR(100) ,PlayerName VARCHAR(100), PlayerUUID VARCHAR(100), Linked VARCHAR(100), LinkedCode VARCHAR(100), PRIMARY KEY(dcName))");
            ps5.executeUpdate();
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

    public void verify1(String member,String memberID,Player p , String LinkCode){

        try {
            UUID uuid = p.getUniqueId();

            if(!existsverified(uuid)){

                PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("INSERT INTO LinkList (dcName,DiscordID,PlayerName,PlayerUUID, Linked, LinkedCode) VALUES (?,?,?,?,?,?)");
                ps.setString(1, member);
                ps.setString(2, memberID);
                ps.setString(3, p.getDisplayName());
                ps.setString(4, uuid.toString());
                ps.setString(5, null);
                ps.setString(6, LinkCode);
                ps.executeUpdate();

                return;
            }


        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void verify2(Player p,Boolean Linked){

        UUID uuid = p.getUniqueId();

        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE LinkList SET Linked=? WHERE PlayerUUID=?");
            ps.setString(1, "true");
            ps.setString(2, uuid.toString());
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
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT UUID FROM LinkList WHERE PlayerUUID=?");
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
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT LinkedCode FROM LinkList WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            String linkCode = null;
            if(rs.next()){
                linkCode = rs.getString("prefix");
                return linkCode;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
