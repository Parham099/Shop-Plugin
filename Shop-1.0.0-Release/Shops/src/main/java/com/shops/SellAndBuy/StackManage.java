package com.shops.SellAndBuy;

import com.shops.Shops;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class StackManage implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getView().getTitle() == "Stack") {
            event.setCancelled(true);
            Player p = (Player) event.getView().getPlayer();
            int price = event.getCurrentItem().getAmount() * 64 * Shops.getInstance().getConfig().getConfigurationSection(String.valueOf(event.getCurrentItem().getType())).getInt("Buy");
            if (Shops.getEconomy().getBalance(p) >= price) {
                PlayerInventory inv = p.getInventory();

                Shops.getEconomy().withdrawPlayer(p, price);
                inv.addItem(new ItemStack(event.getCurrentItem().getType(), event.getCurrentItem().getAmount() * 64));
                p.sendMessage(Shops.prefix("&eYou Buy &6" + event.getCurrentItem().getAmount() + " &eStack &6" + event.getCurrentItem().getType() + " &eFor &6" + price));
            }
        }
    }
}
