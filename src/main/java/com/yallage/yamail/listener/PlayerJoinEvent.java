package com.yallage.yamail.listener;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.File;
import java.io.IOException;

import static com.yallage.yamail.YaMail.instance;

public class PlayerJoinEvent implements Listener {
    @EventHandler
    public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent e){
        Player player = e.getPlayer();
        File PlayerDataFile = new File(instance.getDataFolder(),"playerdata.yml");
        FileConfiguration PlayerData = YamlConfiguration.loadConfiguration(PlayerDataFile);
        if(!PlayerData.contains(String.valueOf(player.getUniqueId()))){
            PlayerData.set(player.getUniqueId() +".amount",0);
            try {
                PlayerData.save(PlayerDataFile);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        if(PlayerData.getInt(player.getUniqueId() +".amount") > 0){
            player.sendMessage(instance.getConfig().getString("prefix")+"> §4§l您有新邮件请使用/yamail gui查看");
            player.sendTitle("§4§l您有新邮件请注意查收",instance.getConfig().getString("prefix"),10,70,20);
        }
    }
}
