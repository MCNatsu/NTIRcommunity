package me.aq.plugin.ntirEco.DiscordBot;

import me.aq.plugin.ntirEco.NTIReco;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public final class Verify extends ListenerAdapter {

    private NTIReco plugin;

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent e) {

        plugin = NTIReco.getPlugin();

        User user = e.getAuthor();

        if(user.isBot() || e.isWebhookMessage()) return;

        Member member = e.getMember();

        String[] args = e.getMessage().getContentRaw().split(" ");


        if(args[0].equalsIgnoreCase("!link")){

            if ((args.length != 2)){
                e.getChannel().sendMessage("請輸入玩家名稱!").queue();
                return;
            }


            Player p = Bukkit.getPlayerExact(args[1]);

            if(p == null){
                e.getChannel().sendMessage("該玩家不存在!").queue();
                return;
            }

            if(plugin.data.verified(p.getUniqueId())){
                e.getChannel().sendMessage("該玩家已驗證").queue();
                return;
            }

            String code = new Random().nextInt(800000) + 20000 + "AA";

            plugin.data.verify1(member.getEffectiveName() ,member.getId(), p , code  );

            e.getMessage().reply("成功生成驗證碼!\n\n請至遊戲中輸入/verify取得驗證碼並輸入/verify <驗證碼>來連結帳號").queue();


        }
    }

}
