package com.shops.SellAndBuy;

import com.shops.ItemStacks.Category;
import com.shops.ItemStacks.SellAndBuyGuiItems;
import Menus.Main.MainGui;
import com.shops.Shops;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class SellManage implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getView().getTitle() == "SellMenu") {
            e.setCancelled(true);
            int slot = e.getSlot();
            Player p = (Player) e.getView().getPlayer();
            PlayerInventory inv = p.getInventory();
            ItemStack item = new ItemStack(e.getInventory().getItem(13).getType());
            item.setAmount(SellAndBuyGuiItems.amount.get(p));
            Configuration config = Shops.getInstance().getConfig();
            ConfigurationSection section = config.getConfigurationSection("Items").getConfigurationSection(Category.category.get(p)).getConfigurationSection(String.valueOf(e.getInventory().getItem(13).getType()));
            int sellPrice = item.getAmount() * section.getInt("sell");

            if (slot != 12) {
                if (slot == 4) {
                    if (section.getInt("sell") >= 0) {
                        if (inv.contains(item.getType(), item.getAmount())) {
                            Shops.getEconomy().depositPlayer(p, sellPrice);
                            inv.removeItem(new ItemStack(item.getType(), item.getAmount()));
                            p.sendMessage(Shops.prefix("&eYou Sell &6" + item.getAmount() + " " + item.getType() + " &eFor &6" + sellPrice));
                            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 500, 500);
                            p.closeInventory();
                        } else {
                            p.sendMessage(Shops.prefix("&eYou Don`t Have ٍٍEnough Item In Inventory"));
                            p.closeInventory();
                        }
                    } else {
                        p.sendMessage(Shops.prefix("&eSell This Item Is Disable!"));
                    }
                } else if (slot == 26) {
                    p.openInventory(MainGui.inv(p));
                } else if (e.getCurrentItem().getType() == Material.REDSTONE) {
                    if (SellAndBuyGuiItems.amount.get(p) - e.getCurrentItem().getAmount() <= 0) {
                        item.setAmount(1);
                        SellAndBuyGuiItems.amount.put(p, 1);
                    } else {
                        item.setAmount(item.getAmount() - e.getCurrentItem().getAmount());
                        SellAndBuyGuiItems.amount.put(p, SellAndBuyGuiItems.amount.get(p) - e.getCurrentItem().getAmount());
                    }
                        p.closeInventory();
                        p.openInventory(SellGui.inv(item.getType(), p));

                } else if (e.getCurrentItem().getType() == Material.EMERALD) {
                        item.setAmount(item.getAmount() + e.getCurrentItem().getAmount());
                        SellAndBuyGuiItems.amount.put(p, SellAndBuyGuiItems.amount.get(p) + e.getCurrentItem().getAmount());
                        p.closeInventory();
                        p.openInventory(SellGui.inv(item.getType(), p));

                } else if (e.getCurrentItem().getType() == Material.DIAMOND) {
                    if (inv.contains(item.getType())) {
                        int num = 0;

                        while (inv.contains(item.getType(), num)) {
                            num += 1;
                        }
                        num =- 1;
                        inv.remove(item.getType());
                        Shops.getEconomy().depositPlayer(p, num * sellPrice);
                        p.sendMessage(Shops.prefix("&eYou Sell &6" + num + " " + item.getType() + " &eFor &6" + num * sellPrice + "&e!"));
                    } else {
                        p.sendMessage(Shops.prefix("&eYou Don`t Have ٍٍEnough Item In Inventory"));
                        p.closeInventory();
                    }
                }
            }
        }
    }
}
