package com.yallage.yamail.listener;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.yallage.yamail.YaMail.instance;

public class InventoryClickEvent implements Listener {
    int coin = 0;
    int point = 0;
    int count = 0;
    @EventHandler
    public void onInventoryClick(org.bukkit.event.inventory.InventoryClickEvent e){
        if(e.getWhoClicked().getOpenInventory().getTitle().equalsIgnoreCase("发送邮件") || e.getWhoClicked().getOpenInventory().getTitle().equalsIgnoreCase("发送全体邮件")){
           int[] bk = new int[] {0,1,2,3,4,5,6,7,8,9,17,18,26,27,35,36,44,45,46,47,48,49,50,51,52,53};
            for (int i : bk) {
                if(e.getRawSlot() ==i){
                    e.setCancelled(true);
                }
            }
            Player player = (Player)e.getWhoClicked();
            if(e.getRawSlot() == 51){
                player.closeInventory();
                player.sendMessage(instance.getConfig().getString("prefix")+"> §4§l您取消了邮件发送");
            }
            ItemStack item_button1 = e.getInventory().getItem(45);
            ItemStack item_button2 = e.getInventory().getItem(53);
            ItemMeta item_button1_meta = item_button1.getItemMeta();
            ItemMeta item_button2_meta = item_button2.getItemMeta();
            List<String> button1_lores = item_button1_meta.getLore();
            List<String> button2_lores = item_button2_meta.getLore();
            if(e.getRawSlot() == 45){
                if(e.getClick().isLeftClick()){
                    coin = coin + 100;
                    button1_lores.remove(2);
                    button1_lores.add(2,"● §e§l当前邮件所含犽币: "+coin);
                    item_button1_meta.setLore(button1_lores);
                    item_button1.setItemMeta(item_button1_meta);
                }
                if(e.getClick().isRightClick() && coin > 0){
                    coin = coin - 100;
                    button1_lores.remove(2);
                    button1_lores.add(2,"● §e§l当前邮件所含犽币: "+coin);
                    item_button1_meta.setLore(button1_lores);
                    item_button1.setItemMeta(item_button1_meta);
                }
            }
            if(e.getRawSlot() == 53){
                if(e.getClick().isLeftClick()){
                    point = point + 100;
                    button2_lores.remove(2);
                    button2_lores.add(2,"● §e§l当前邮件所含点券: "+point);
                    item_button2_meta.setLore(button2_lores);
                    item_button2.setItemMeta(item_button2_meta);
                }
                if(e.getClick().isRightClick() && point > 0){
                    point = point - 100;
                    button2_lores.remove(2);
                    button2_lores.add(2,"● §e§l当前邮件所含点券: "+point);
                    item_button2_meta.setLore(button2_lores);
                    item_button2.setItemMeta(item_button2_meta);
                }
            }
            if(e.getRawSlot() == 47){
                int[] fs = new int[] {10,11,12,13,14,15,16,19,20,21,22,23,24,25,29,29,30,31,32,33,34,37,38,39,40,41,42,43};
                File PlayerDataFile = new File(instance.getDataFolder(),"playerdata.yml");
                FileConfiguration PlayerData = YamlConfiguration.loadConfiguration(PlayerDataFile);
                Player target = Bukkit.getPlayer(e.getInventory().getItem(4).getItemMeta().getLore().get(0));
                int amount = PlayerData.getInt(target.getUniqueId() +".amount");
                amount++;
                PlayerData.set(target.getUniqueId() +".amount",amount);
                Date dNow = new Date( );
                SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                PlayerData.set(target.getUniqueId() +"."+amount+".sender",e.getWhoClicked().getName());
                PlayerData.set(target.getUniqueId() +"."+amount+".time",ft.format(dNow));
                PlayerData.set(target.getUniqueId() +"."+amount+".coin",coin);
                PlayerData.set(target.getUniqueId() +"."+amount+".point",point);
                PlayerData.set(target.getUniqueId() +"."+amount+".count",count);
                PlayerData.set(target.getUniqueId() +".amount",amount);
                if(target != null){
                    target.sendMessage(instance.getConfig().getString("prefix")+"> §4§l您有新邮件请使用/yamail gui查看");
                    target.sendTitle("§4§l您有新邮件请注意查收",instance.getConfig().getString("prefix"),10,70,20);
                }
                for (int f : fs) {
                    if(e.getInventory().getItem(f) != null){
                        count++;
                        PlayerData.set(target.getUniqueId() +"."+amount+".content"+"."+count,e.getInventory().getItem(f));
                    }
                }
                try {
                    PlayerData.save(PlayerDataFile);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                player.closeInventory();
                player.sendMessage(instance.getConfig().getString("prefix")+"> §a§l您的邮件已发送成功");
                coin = 0;
                point = 0;
                count = 0;
            }
            if(e.getRawSlot()<0 || e.getRawSlot()>e.getInventory().getSize() || e.getInventory()==null)
            return;
        }
    }
}
