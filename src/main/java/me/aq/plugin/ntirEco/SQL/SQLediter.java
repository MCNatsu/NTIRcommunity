package me.aq.plugin.ntirEco.SQL;

import me.aq.plugin.ntirEco.NTIReco;
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
        try {
            ps = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS money "
                    + "(NAME VARCHAR(100), UUID VARCHAR(100), money INT(100), PRIMARY KEY(NAME))");

            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void  createPlayer(Player player){

        try {
            UUID uuid = player.getUniqueId();
            if(!exists(uuid)){

                PreparedStatement ps2 = plugin.SQL.getConnection().prepareStatement("INSERT IGNORE INTO money" +
                        "(NAME,UUID,money) VALUES (?,?,?)");
                ps2.setString(1, player.getName());
                ps2.setString(2, uuid.toString());
                ps2.setInt(3, 0);
                ps2.executeUpdate();

                return;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public boolean exists(UUID uuid){

        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT FROM money WHERE UUID=?");
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
}
