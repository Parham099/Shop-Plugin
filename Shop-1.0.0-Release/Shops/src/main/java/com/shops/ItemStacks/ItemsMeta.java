package com.shops.ItemStacks;

import com.shops.Shops;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemsMeta {
    public static ItemMeta Lore(ItemStack i, int Buy, int Sell) {

        String buy = null;
        String sell = null;

        if (Buy < 0) {
            buy = "&r&6&l-";
        } else {
            buy = String.valueOf(Buy);
        }
        if (Sell < 0) {
            sell = "&r&6&l-";
        } else {
            sell = String.valueOf(Sell);
        }

        ItemMeta meta = i.getItemMeta();
        meta.setLore(List.of(" ", c("&e&lBuy"), c("&a&n" + buy), "", c("&e&lSell"), c("&a&n" + sell), "", c("&6LeftClick For Buy"), c("&6RightClick For Sell"), ""));
        return meta;
    }
    public static String c(String arg) {
        return ChatColor.translateAlternateColorCodes('&', arg);
    }
}
