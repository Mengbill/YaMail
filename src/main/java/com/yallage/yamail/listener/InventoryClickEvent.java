package com.yallage.yamail.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class InventoryClickEvent implements Listener {
    @EventHandler
    public void onInventoryClick(org.bukkit.event.inventory.InventoryClickEvent e){
        if(e.getWhoClicked().getOpenInventory().getTitle().equalsIgnoreCase("发送邮件")){
            int[] bk = new int[] {0,1,2,3,5,6,7,8,9,17,18,26,27,35,36,44,46,48,50,52};
            for (int i : bk) {
                if(e.getRawSlot() == i){
                    e.setCancelled(true);
                }
            }

            if(e.getRawSlot()<0 || e.getRawSlot()>e.getInventory().getSize() || e.getInventory()==null)
            return;
        }
    }
}
