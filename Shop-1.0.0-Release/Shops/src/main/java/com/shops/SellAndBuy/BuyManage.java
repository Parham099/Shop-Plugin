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

public class BuyManage implements Listener {
    @EventHandler
    public void Clicking(InventoryClickEvent e) {
        if (e.getView().getTitle() == "BuyMenu") {
            e.setCancelled(true);
            int slot = e.getSlot();
            Player p = (Player) e.getView().getPlayer();
            PlayerInventory inv = p.getInventory();
            ItemStack item = e.getInventory().getItem(13);
            item.setAmount(SellAndBuyGuiItems.amount.get(p));
            Configuration config = Shops.getInstance().getConfig();
            ConfigurationSection section = config.getConfigurationSection("Items").getConfigurationSection(Category.category.get(p)).getConfigurationSection(String.valueOf(e.getInventory().getItem(13).getType()));
            int buyPrice = item.getAmount() * section.getInt("buy");

            if (slot == 4) {
                if (section.getInt("buy") >= 0) {
                    if (Shops.getEconomy().getBalance(p) >= buyPrice) {
                        Shops.getEconomy().withdrawPlayer(p, buyPrice);
                        inv.addItem(new ItemStack(item.getType(), item.getAmount()));
                        p.sendMessage(Shops.prefix("&eYou Buy &6" + item.getAmount() + " " + item.getType() + " &eFor &6" + buyPrice));
                        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 500, 500);
                    } else {
                        p.sendMessage(Shops.prefix("&eYou Need &6" + (buyPrice - Shops.getEconomy().getBalance(p)) + " &eMore Money!"));
                        p.closeInventory();
                    }
                } else {
                    p.sendMessage(Shops.prefix("&eBuy This Item Is Disable!"));
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
                    p.openInventory(BuyGui.inv(item.getType(), p));

            } else if (e.getCurrentItem().getType() == Material.EMERALD) {
                    item.setAmount(item.getAmount() + e.getCurrentItem().getAmount());
                    SellAndBuyGuiItems.amount.put(p, SellAndBuyGuiItems.amount.get(p) + e.getCurrentItem().getAmount());
                    p.closeInventory();
                    p.openInventory(BuyGui.inv(item.getType(), p));
            }
        }
    }
}
