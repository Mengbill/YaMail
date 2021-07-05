package com.yallage.yamail;

import com.yallage.yamail.command.YaCommandExcutor;
import com.yallage.yamail.listener.InventoryClickEvent;
import com.yallage.yamail.listener.PlayerJoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class YaMail extends JavaPlugin {

    public static YaMail instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        reloadConfig();
        saveResource("playerdata.yml",false);
        Bukkit.getLogger().info(getConfig().getString("prefix")+"> 正在加载YaMail插件"+"版本"+getConfig().get("version"));
        Bukkit.getPluginCommand("yamail").setExecutor(new YaCommandExcutor());
        Bukkit.getPluginManager().registerEvents(new InventoryClickEvent(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinEvent(),this);
        instance = this;
        Bukkit.getLogger().info(getConfig().getString("prefix")+"> 加载YaMail插件成功!");
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info(getConfig().getString("prefix")+"> YaMail插件已卸载 感谢您的使用");
    }
}
