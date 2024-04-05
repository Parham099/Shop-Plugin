package Menus;

import Menus.Main.MainGui;
import com.shops.ItemStacks.Category;
import com.shops.ItemStacks.Global;
import com.shops.SellAndBuy.BuyGui;
import com.shops.SellAndBuy.SellGui;
import com.shops.Shops;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Manage implements Listener {
    @EventHandler
    public void Other(InventoryClickEvent event) {
        String t = event.getView().getTitle();
        if (Shops.getInstance().getConfig().getConfigurationSection("Items").contains(t)) {
            event.setCancelled(true);
            Material m = event.getCurrentItem().getType();
            Player p = (Player) event.getView().getPlayer();
            ConfigurationSection s = Shops.getInstance().getConfig().getConfigurationSection("Items").getConfigurationSection(t).getConfigurationSection(String.valueOf(event.getCurrentItem().getType()));

            if (event.getSlot() == 53) {
                p.openInventory(MainGui.inv(p));
            } else if (m != Global.Filter().getType() && m != Global.status(p).getType()) {
                // registering prices
                if (event.getClick() == ClickType.LEFT) {
                    if (s.getInt("buy") >= 0) {
                        Category.category.put(p, event.getView().getTitle());
                        p.openInventory(BuyGui.inv(event.getCurrentItem().getType(), p));
                    } else {
                        p.sendMessage(Shops.prefix("&6" + event.getCurrentItem().getType() + "&e Buy Is Disable!"));
                    }
                } else if (event.getClick() == ClickType.RIGHT) {
                    if (s.getInt("sell") >= 0) {
                        Category.category.put(p, event.getView().getTitle());
                        p.openInventory(SellGui.inv(event.getCurrentItem().getType(), p));
                    } else {
                        p.sendMessage(Shops.prefix("&6" + event.getCurrentItem().getType() + "&e Sell Is Disable!"));
                    }
                }
            }
        }
    }
}
