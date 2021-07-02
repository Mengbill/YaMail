package com.yallage.yamail;

import com.yallage.yamail.command.YaCommandExcutor;
import com.yallage.yamail.listener.YaEventListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class YaMail extends JavaPlugin {

    public static YaMail instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        reloadConfig();
        Bukkit.getLogger().info(getConfig().getString("prefix")+"> 正在加载YaMail插件......"+"版本"+getConfig().get("version"));
        Bukkit.getPluginCommand("yamail").setExecutor(new YaCommandExcutor());
        Bukkit.getPluginManager().registerEvents(new YaEventListener(),this);
        Bukkit.getLogger().info(getConfig().getString("prefix")+"> 加载YaMail插件成功!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info(getConfig().getString("prefix")+"> YaMail插件已卸载 感谢您的使用");
    }
}
