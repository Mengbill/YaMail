package com.yallage.yamail.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.io.IOException;

import static com.yallage.yamail.YaMail.instance;

public class PlayerJoinEvent implements Listener {
    public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent e){
        Player player = e.getPlayer();
        if(!instance.PlayerData.contains(String.valueOf(player.getUniqueId()))){
            instance.PlayerData.set(player.getUniqueId() +".received",false);
            try {
                instance.PlayerData.save(instance.PlayerDataFile);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        if(instance.PlayerData.getBoolean(player.getUniqueId() +".received")){
            player.sendMessage(instance.getConfig().getString("prefix")+"> §4§l您有新邮件请使用/yamail gui查看");
            player.sendTitle("§4§l您有新邮件请注意查收",instance.getConfig().getString("prefix"),10,70,20);
        }
    }
}
