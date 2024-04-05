package com.shops.ItemStacks;

import com.shops.Shops;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

public class Global {
    public static ItemStack status(Player p) {
        ItemStack i = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) i.getItemMeta();
        meta.setDisplayName(c("&6&l" + p.getName()));
        meta.setOwningPlayer(p);
        meta.setLore(List.of("", c("&6&lBalance &e" + Shops.getEconomy().getBalance(p))));
        i.setItemMeta(meta);
        return i;
    }
    public static ItemStack backBTN() {
        ItemStack i = new ItemStack(Material.REDSTONE);
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName(c("&4&l<- &cBack"));
        meta.setLore(List.of("", c("&eGo To Old Page"), ""));
        i.setItemMeta(meta);
        return i;
    }
    public static ItemStack nextBTN() {
        ItemStack i = new ItemStack(Material.EMERALD);
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName(c("&aNext &2&l->"));
        meta.setLore(List.of("", c("&eGo To Next Page"), ""));
        i.setItemMeta(meta);
        return i;
    }
    public static ItemStack Filter() {
        ItemStack filterYellow = new ItemStack(Material.valueOf(Shops.getInstance().getConfig().getConfigurationSection("FillerItem").getString("type")));
        ItemMeta meta1 = filterYellow.getItemMeta();
        meta1.setDisplayName(Shops.getInstance().getConfig().getConfigurationSection("FillerItem").getString("display-Name"));
        if (Shops.getInstance().getConfig().getConfigurationSection("FillerItem").getBoolean("enchanted")) {
            meta1.addEnchant(Enchantment.DURABILITY, 1, true);
            meta1.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        filterYellow.setItemMeta(meta1);
        return filterYellow;
    }
    private static String c(String arg) {
        return ChatColor.translateAlternateColorCodes('&', arg);
    }
}
