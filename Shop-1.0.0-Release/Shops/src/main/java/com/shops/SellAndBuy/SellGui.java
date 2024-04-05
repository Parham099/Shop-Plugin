package com.shops.SellAndBuy;

import com.shops.ItemStacks.Category;
import com.shops.ItemStacks.Global;
import com.shops.ItemStacks.SellAndBuyGuiItems;
import com.shops.Shops;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SellGui {
    public static Inventory inv(Material material, Player p) {
        Configuration config = Shops.getInstance().getConfig();
        Inventory inv = Bukkit.createInventory(null, 27, "SellMenu");
        ConfigurationSection section = config.getConfigurationSection("Items").getConfigurationSection(Category.category.get(p)).getConfigurationSection(String.valueOf(material));
        // filtering air slots! {
        ItemStack filter = Global.Filter();
        // }
        for (int i = 0; i < 27; i++) {
            inv.setItem(i, filter);
        }
        // settup Inventor View :D {
        inv.setItem(4, SellAndBuyGuiItems.accept());
        inv.setItem(10, SellAndBuyGuiItems.menuse(64));
        inv.setItem(11, SellAndBuyGuiItems.menuse(10));
        inv.setItem(12, SellAndBuyGuiItems.menuse(1));

        inv.setItem(13, SellAndBuyGuiItems.item(material, section.getInt("sell"), p));

        inv.setItem(14, SellAndBuyGuiItems.plus(1));
        inv.setItem(15, SellAndBuyGuiItems.plus(10));
        inv.setItem(16, SellAndBuyGuiItems.plus(64));

        inv.setItem(22, SellAndBuyGuiItems.sellall());

        inv.setItem(18, Global.status(p));
        inv.setItem(26, Global.backBTN());
        // }
        return inv;
    }
}
