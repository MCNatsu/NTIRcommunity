package me.aq.plugin.ntirEco.utils.PluginMessage;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.aq.plugin.ntirEco.NTIReco;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

public class PluginMsg implements PluginMessageListener {

    private NTIReco plugin;

    @Override
    public void onPluginMessageReceived(@NotNull String channel, @NotNull Player player, @NotNull byte[] message) {
        if(!channel.equals("BungeeCord")){
            return;
        }
        ByteArrayDataInput input = ByteStreams.newDataInput(message);
        String subChannel = input.readUTF();
    }

    public void connect(Player p, String server){
        plugin = NTIReco.getPlugin();

        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("Connect");
        output.writeUTF(server);
        p.sendPluginMessage(plugin,"BungeeCord",output.toByteArray());
    }
}
