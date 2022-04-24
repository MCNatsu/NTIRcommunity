package me.aq.plugin.ntirEco.SQL;

import org.checkerframework.checker.units.qual.C;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    private String host = "localhost";
    private String port = "3306";
    private String database = "ntirplayerdata";
    private String communityDATA = "communitydata";
    private String chatDATA = "ntirchatdata";
    private String user = "NatsuServer";
    private String password = "TaiWanIsVeryGood";


    private Connection connection;
    private Connection CommunityDATA;
    private Connection ChatDATA;

    public boolean isConnected(){

        return (connection == null ? false : true);

    }

    public boolean iscommunityDATAConnected(){

        return (CommunityDATA == null ? false : true);

    }

    public boolean isDATAConnected(){

        return (CommunityDATA == null ? false : true);

    }

    public  void connect() throws ClassNotFoundException, SQLException{
        if(!isConnected()) {
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":"+ port + "/"
                    + database + "?useSSL=false&autoReconnect=true&failOverReadOnly=false", user, password);
        }
    }

    public  void connectCommunity() throws ClassNotFoundException, SQLException{
        if(!isDATAConnected()) {
            CommunityDATA = DriverManager.getConnection("jdbc:mysql://" + host + ":"+ port + "/"
                    + communityDATA + "?useSSL=false&autoReconnect=true&failOverReadOnly=false", user, password);
        }
    }

    public  void disconnect(){
        if(isConnected())
        try {
            connection.close();
            CommunityDATA.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  Connection getConnection(){
        return connection;
    }

    public Connection getCommunityDATA(){return CommunityDATA;}
}
