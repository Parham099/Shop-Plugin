package Menus.Main;

import com.shops.ItemStacks.Global;
import com.shops.Shops;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class MainGui {
    public static Inventory inv(Player p) {
        Inventory inv = Bukkit.createInventory(null, 54, "ShopMenu");
        ItemStack filter = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta filterMeta = filter.getItemMeta();
        filterMeta.setDisplayName(" ");
        filter.setItemMeta(filterMeta);

        for (int i = 0; i < 54; i++) {
            inv.setItem(i, filter);
        }

        for (Material material : Material.values()) {
            ConfigurationSection section = Shops.getInstance().getConfig().getConfigurationSection("Sections");
            if (section.contains(String.valueOf(material))) {

                ItemStack Item = new ItemStack(material);
                ItemMeta meta = Item.getItemMeta();
                meta.setDisplayName(c(String.valueOf(section.getConfigurationSection(String.valueOf(material)).getString("display-Name"))));
                meta.setLore(List.of("", c("&eLeft Click For Open")));
                if (section.getConfigurationSection(String.valueOf(material)).getBoolean("enchanted")) {
                    meta.addEnchant(Enchantment.DAMAGE_UNDEAD, 1 , true);
                    meta.getItemFlags().add(ItemFlag.HIDE_ENCHANTS);
                }
                Item.setItemMeta(meta);
                inv.setItem(section.getConfigurationSection(String.valueOf(material)).getInt("slot"), Item);
            }
        }
        inv.setItem(45, Global.status(p));
        inv.setItem(53, Global.backBTN());

        return inv;
    }
    public static String c(String arg) {
        return ChatColor.translateAlternateColorCodes('&', arg);
    }
}
