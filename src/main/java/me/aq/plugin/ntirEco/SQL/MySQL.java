package me.aq.plugin.ntirEco.SQL;

import org.checkerframework.checker.units.qual.C;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    //Connect Address
    private String host = "localhost";
    private String port = "3306";
    //Database
    private String database = "ntirplayerdata";
    private String communityDATA = "communitydata";
    private String chatDATA = "ntirchatdata";
    public String managementDATA = "NTIRManagementData";
    //SQL Account
    private String user = "NatsuServer";
    private String password = "TaiWanIsVeryGood";


    private Connection connection;
    private Connection CommunityDATA;
    private Connection ChatDATA;
    private Connection SVManagement;

    public boolean isConnected(){

        return (connection == null ? false : true);

    }

    public boolean isChatDATAConnected(){

        return (ChatDATA == null ? false : true);

    }

    public boolean isSVManagementDATAConnected(){

        return (SVManagement == null ? false : true);

    }

    public boolean isCommunityDATAConnected(){

        return (CommunityDATA == null ? false : true);

    }

    public  void connect() throws ClassNotFoundException, SQLException{
        if(!isConnected()) {
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":"+ port + "/"
                    + database + "?useSSL=false&autoReconnect=true&failOverReadOnly=false", user, password);
        }

        if(!isCommunityDATAConnected()) {
            CommunityDATA = DriverManager.getConnection("jdbc:mysql://" + host + ":"+ port + "/"
                    + communityDATA + "?useSSL=false&autoReconnect=true&failOverReadOnly=false", user, password);
        }

        if(!isChatDATAConnected()) {
            ChatDATA = DriverManager.getConnection("jdbc:mysql://" + host + ":"+ port + "/"
                    + chatDATA + "?useSSL=false&autoReconnect=true&failOverReadOnly=false", user, password);
        }
        if(!isSVManagementDATAConnected()) {
            SVManagement = DriverManager.getConnection("jdbc:mysql://" + host + ":"+ port + "/"
                    + managementDATA + "?useSSL=false&autoReconnect=true&failOverReadOnly=false", user, password);
        }
    }

    public  void disconnect(){
        if(isConnected())
        try {
            connection.close();
            CommunityDATA.close();
            ChatDATA.close();
            SVManagement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  Connection getConnection(){
        return connection;
    }

    public Connection getCommunityDATA(){return CommunityDATA;}

    public Connection getChatDATA(){return ChatDATA;}
}
