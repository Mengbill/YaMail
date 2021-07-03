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

import static com.yallage.yamail.YaMail.instance;

public class YaCommandExcutor implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("yamail")) {
            if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
                sender.sendMessage("§f===========================§9<§4§l YaMail 系 统 §9>§f===========================");
                sender.sendMessage("§a§l/yamail help §f§l- §7§l查看指令帮助");
                sender.sendMessage("§a§l/yamail gui §f§l- §7§l打开YaMail收件箱");
                sender.sendMessage("§a§l/yamail send §f§l- §7§l发送YaMail邮件");
                sender.sendMessage("§a§l/yamail sendall §f§l- §7§发送全体YaMail邮件");
                sender.sendMessage("§a§l/yamai; reload §f§l- §7§l重载插件配置");
                return true;
            }
            if (args[0].equalsIgnoreCase("reload")) {
                if (!(sender.hasPermission("yamail.reload") || sender.isOp())) {
                    sender.sendMessage(instance.getConfig().getString("prefix") + "> §4§l你没有执行此命令的权限");
                } else {
                    instance.reloadConfig();
                    sender.sendMessage(instance.getConfig().getString("prefix") + "> §4§l插件配置文件已重载");
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
                        if(target == null){
                            player.sendMessage(instance.getConfig().getString("prefix")+"> §4§l该玩家离线或不存在 §2§l已转为离线邮件发送");


                        }else{
                            player.sendMessage(instance.getConfig().getString("prefix")+"> §2§l该玩家当前在线 正在为您发送邮件");
                            Inventory inventory = Bukkit.createInventory(null,54,"发送邮件");
                            ItemStack item_bk = new ItemStack(Material.PINK_STAINED_GLASS_PANE);
                            ItemMeta item_bk_meta = item_bk.getItemMeta();
                            item_bk_meta.setDisplayName(null);
                            item_bk.setItemMeta(item_bk_meta);
                            int[] bk = new int[] {0,1,2,3,5,6,7,8,9,17,18,26,27,35,36,44,46,48,50,52};
                            for (int i : bk) {
                                inventory.setItem(i,item_bk);
                            }
                        }
                    }else{
                        sender.sendMessage(instance.getConfig().getString("prefix")+"> §4§l你输入的指令有误 /yamail help查看指令帮助");
                    }
                }
            }
        }
        return false;
    }
}