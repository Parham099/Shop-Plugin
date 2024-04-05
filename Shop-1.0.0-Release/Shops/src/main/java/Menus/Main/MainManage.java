package Menus.Main;

import Menus.Shops.ShopsMenus;
import com.shops.ItemStacks.Global;
import com.shops.Shops;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MainManage implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player p = (Player) event.getView().getPlayer();
        if (event.getView().getTitle() == "ShopMenu") {
            event.setCancelled(true);
            if (event.getCurrentItem().getType() != Global.Filter().getType()) {
                if (!(event.getSlot() == 53)) {
                    ConfigurationSection section = Shops.getInstance().getConfig().getConfigurationSection("Sections").getConfigurationSection(String.valueOf(event.getCurrentItem().getType()));
                    if (event.getSlot() == section.getInt("slot")) {
                        p.openInventory(ShopsMenus.inv(p, section.getString("category")));
                    }
                } else {
                    p.closeInventory();
                }
            }
        }
    }
    public static String c(String arg) {
        return ChatColor.translateAlternateColorCodes('&', arg);
    }
}
