package com.shops.Commands;

import com.shops.ItemStacks.Global;
import com.shops.ItemStacks.SellAndBuyGuiItems;
import Menus.Main.MainGui;
import com.shops.SellAndBuy.SellGui;
import com.shops.Shops;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Commands implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender s, Command command, String arg, String[] args) {
        Configuration config = Shops.getInstance().getConfig();
        if (args.length == 0) {
            if (s instanceof Player) {
                Player p = (Player) s;
                if (s.hasPermission("shop.open")) {
                    SellAndBuyGuiItems.amount.put(p, 1);
                    p.openInventory(MainGui.inv(p));
                } else {
                    s.sendMessage(Shops.prefix(config.getString("PermissionMessage")));
                }
            }
        } else if (args[0].equalsIgnoreCase("open")) { //open for others
            if (s.hasPermission("shop.open.other")) {
                if (Bukkit.getPlayer(args[1]) != null && Bukkit.getPlayer(args[1]).isOnline()) {
                    Player p = Bukkit.getPlayer(args[1]);
                    p.openInventory(MainGui.inv(p));
                } else {
                    s.sendMessage(Shops.prefix(config.getString("playerIsOffline")));
                }
            } else {
                s.sendMessage(Shops.prefix(config.getString("PermissionMessage")));
            }
        } else if (args[0].equalsIgnoreCase("reload")) { // reload
            if (s.hasPermission("shop.reload")) {
                Shops.getInstance().reloadConfig();
                s.sendMessage(Shops.prefix(config.getString("reloadMessage")));
            } else {
                s.sendMessage(Shops.prefix(config.getString("PermissionMessage")));
            }
        } else if (args[0].equalsIgnoreCase("additem")) { // additem
            if (s.hasPermission("shop.additem")) {
                addItem.onAddItem(args, s);
            } else {
                s.sendMessage(Shops.prefix(config.getString("PermissionMessage")));
            }
        } else {
            s.sendMessage(Shops.prefix(config.getString("commandNotFound")));
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {

        if (args.length == 1) {
            List<String> list = new ArrayList<>();
            list.add("open");
            list.add("reload");
            list.add("logs");
            list.add("additem");

            return list;
        }
        if (args[0].equalsIgnoreCase("open")) {
            return null;
        }
        //add item tab compelate
        if (args[0].equalsIgnoreCase("additem")) {
            List<String> list = new ArrayList<>();
            if (args.length == 2) {
                list.add("section");
                return list;
            } else if (args.length == 3) {
                for (Material material : Material.values()) {
                    list.add(String.valueOf(material));
                }
                return list;
            } else if (args.length == 4) {
                list.add("buy-price");
                return list;
            } else if (args.length == 5) {
                list.add("sell-price");
                return list;
            } else if (args.length == 6) {
                list.add("slot-number");
                return list;
            } else if (args.length == 7) {
                list.add("page");
                return list;
            }
            return list;
        }
        return null;
    }
}
