package com.yallage.yamail.command;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static com.yallage.yamail.YaMail.instance;

public class YaCommandExcutor implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("yamail")) {
            if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
                sender.sendMessage("§f===========================§9<§4§l YaMail 系 统 §9>§f===========================");
                sender.sendMessage("§a§l/yamail help §f§l- §7§l查看指令帮助");
                sender.sendMessage("§a§l/yamail gui §f§l- §7§l打开YaMail收件箱");
                sender.sendMessage("§a§l/yamail send <玩家名> §f§l- §7§l发送YaMail邮件");
                sender.sendMessage("§a§l/yamail sendall §f§l- §7§l发送全体YaMail邮件");
                sender.sendMessage("§a§l/yamail reload §f§l- §7§l重载插件配置");
                return true;
            }
            if (args[0].equalsIgnoreCase("reload")) {
                if (!(sender.hasPermission("yamail.reload") || sender.isOp())) {
                    sender.sendMessage(instance.getConfig().getString("prefix") + "> §4§l你没有执行此命令的权限");
                } else {
                    instance.reloadConfig();
                    sender.sendMessage(instance.getConfig().getString("prefix") + "> §a§l插件配置文件已重载");
                }
                return true;
            }
            if(args[0].equalsIgnoreCase("gui")){
                if(!(sender instanceof Player)){
                    sender.sendMessage(instance.getConfig().getString("prefix")+"> §4§l该命令只能由玩家执行");
                }else
                if(!(sender.hasPermission("yamail.gui") || sender.isOp())){
                    sender.sendMessage(instance.getConfig().getString("prefix")+"> §4§l你没有执行此命令的权限");
                }else{
                    // 收件箱(容器)
                }
                return true;
            }
            if(args[0].equalsIgnoreCase("send")){
                if(!(sender instanceof Player)){
                    sender.sendMessage(instance.getConfig().getString("prefix")+"> §4§l该命令只能由玩家执行");
                }else{
                    if(!(sender.hasPermission("yamail.send") || sender.isOp())){
                        sender.sendMessage(instance.getConfig().getString("prefix")+"> §4§l你没有执行此命令的权限");
                    }
                    if(args.length == 2){
                        Player player = (Player)sender;
                        Player target = Bukkit.getPlayer(args[1]);
                        player.sendMessage(instance.getConfig().getString("prefix")+"> §a§l请稍后正在为您发送邮件");
                        Inventory inventory = Bukkit.createInventory(null,54,"发送邮件");
                        ItemStack item_bk = new ItemStack(Material.PINK_STAINED_GLASS_PANE);
                        ItemMeta item_bk_meta = item_bk.getItemMeta();
                        item_bk_meta.setDisplayName("");
                        item_bk.setItemMeta(item_bk_meta);
                        int[] bk = new int[] {0,1,2,3,5,6,7,8,9,17,18,26,27,35,36,44,46,48,50,52};
                        for (int i : bk) {
                            inventory.setItem(i,item_bk);
                        }
                        ItemStack item_info1 = new ItemStack(Material.CLOCK);
                        ItemMeta item_info1_meta = item_info1.getItemMeta();
                        item_info1_meta.setDisplayName("§8§l收件人: §a§l"+target.getName());
                        List<String> info1_lores = new ArrayList<>();
                        info1_lores.add(target.getName());
                        item_info1_meta.setLore(info1_lores);
                        item_info1.setItemMeta(item_info1_meta);
                        ItemStack item_info2 = new ItemStack(Material.COMPASS);
                        ItemMeta item_info2_meta = item_info2.getItemMeta();
                        item_info2_meta.setDisplayName("§8§l发件人: §a§l"+player.getName());
                        List<String> info2_lores = new ArrayList<String>();
                        info2_lores.add("● §8§l请将发送的物品放入上方空格处");
                        info2_lores.add("● §8§l如需发送犽币请点击左侧向日葵");
                        info2_lores.add("● §8§l如需发送点券请点击右侧下界之星");
                        info2_lores.add("● §8§l确认发送请点击绿色混凝土");
                        info2_lores.add("● §8§l取消发送请点击红色混凝土");
                        item_info2_meta.setLore(info2_lores);
                        item_info2.setItemMeta(item_info2_meta);
                        ItemStack item_button1 = new ItemStack(Material.GREEN_CONCRETE);
                        ItemStack item_button2 = new ItemStack(Material.RED_CONCRETE);
                        ItemMeta item_button1_meta = item_button1.getItemMeta();
                        ItemMeta item_button2_meta = item_button2.getItemMeta();
                        item_button1_meta.setDisplayName("§a§l确认");
                        item_button2_meta.setDisplayName("§4§l取消");
                        item_button1.setItemMeta(item_button1_meta);
                        item_button2.setItemMeta(item_button2_meta);
                        ItemStack item_button3 = new ItemStack(Material.SUNFLOWER);
                        ItemStack item_button4 = new ItemStack(Material.NETHER_STAR);
                        ItemMeta item_buitton3_meta = item_button3.getItemMeta();
                        item_buitton3_meta.setDisplayName("§e§l犽币");
                        List<String> button3_lores = new ArrayList();
                        button3_lores.add("● §8§l左键增加100犽币");
                        button3_lores.add("● §8§l右键减少100犽币");
                        button3_lores.add("● §e§l当前邮件所含犽币: 0");
                        item_buitton3_meta.setLore(button3_lores);
                        item_button3.setItemMeta(item_buitton3_meta);
                        ItemMeta item_buitton4_meta = item_button4.getItemMeta();
                        item_buitton4_meta.setDisplayName("§b§l点券");
                        List<String> button4_lores = new ArrayList();
                        button4_lores.add("● §8§l左键增加100点券");
                        button4_lores.add("● §8§l右键减少100点券");
                        button4_lores.add("● §e§l当前邮件所含点券: 0");
                        item_buitton4_meta.setLore(button4_lores);
                        item_button4.setItemMeta(item_buitton4_meta);
                        inventory.setItem(4,item_info1);
                        inventory.setItem(49,item_info2);
                        inventory.setItem(47,item_button1);
                        inventory.setItem(51,item_button2);
                        inventory.setItem(45,item_button3);
                        inventory.setItem(53,item_button4);
                        player.openInventory(inventory);
                    }else{
                        sender.sendMessage(instance.getConfig().getString("prefix")+"> §4§l你输入的指令有误 /yamail help查看指令帮助");
                    }
                }
                return true;
            }
            sender.sendMessage(instance.getConfig().getString("prefix")+"> §4§l你输入的指令有误 /yamail help查看指令帮助");
            return true;
        }
        return false;
    }
}