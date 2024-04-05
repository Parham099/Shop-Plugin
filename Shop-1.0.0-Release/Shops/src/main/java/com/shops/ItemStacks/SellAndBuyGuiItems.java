package com.shops.ItemStacks;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;

public class SellAndBuyGuiItems {

    public static HashMap<Player, Integer> amount = new HashMap<>();
    public static ItemStack menuse(int number) {
        ItemStack i = new ItemStack(Material.REDSTONE, number);
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName(c("&4-" + number));
        i.setItemMeta(meta);
        return i;
    }
    public static ItemStack plus(int number) {
        ItemStack i = new ItemStack(Material.EMERALD, number);
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName(c("&2+" + number));
        i.setItemMeta(meta);
        return i;
    }
    public static ItemStack accept() {
        ItemStack i = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName(c("&aAccept"));
        meta.setLore(List.of("", c("&7Click For Accept Your Shopping!"), ""));
        i.setItemMeta(meta);
        return i;
    }
    public static ItemStack item(Material material, int price, Player p) {
        ItemStack i = new ItemStack(material, SellAndBuyGuiItems.amount.get(p));
        ItemMeta meta = i.getItemMeta();
        meta.setLore(List.of(" ", c("&6Amount: &e" + i.getAmount()), "", c("&6Price: &e" + i.getAmount() * price), ""));
        i.setItemMeta(meta);
        return i;
    }
    public static ItemStack sellall() {
        ItemStack i = new ItemStack(Material.DIAMOND);
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName(c("&aSellAll"));
        meta.setLore(List.of("", c("&7Click For SellAll This Items In Your Inventory!"), ""));
        i.setItemMeta(meta);
        return i;
    }
    public static ItemStack stack() {
        ItemStack i = new ItemStack(Material.SHULKER_BOX);
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName(c("&bStack"));
        meta.setLore(List.of("", c("&7Click Here If You Need More"), ""));
        i.setItemMeta(meta);
        return i;
    }
    private static String c(String arg) {
        return ChatColor.translateAlternateColorCodes('&', arg);
    }
}
