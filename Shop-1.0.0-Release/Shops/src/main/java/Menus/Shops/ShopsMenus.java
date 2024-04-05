package Menus.Shops;

import com.shops.ItemStacks.Global;
import com.shops.ItemStacks.ItemsMeta;
import com.shops.Shops;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ShopsMenus {
    public static Inventory inv(Player p, String category) {
        Inventory inv = Bukkit.createInventory(null, 54,category);
        for (int i = 0; i < 54 ; i++) {
            inv.setItem(i, Global.Filter());
        }
        inv.setItem(45, Global.backBTN());
        inv.setItem(53, Global.status(p));

        for (Material material : Material.values()) {
            ConfigurationSection s = Shops.getInstance().getConfig().getConfigurationSection("Items").getConfigurationSection(category);
            if (s.contains(String.valueOf(material))) {
                ItemStack i = new ItemStack(material);

                i.setItemMeta(ItemsMeta.Lore(i,
                        s.getConfigurationSection(String.valueOf(material)).getInt("buy"),
                        s.getConfigurationSection(String.valueOf(material)).getInt("sell")));

                inv.setItem(s.getConfigurationSection(String.valueOf(material)).getInt("slot"), i);
            }
        }

        return inv;
    }
}
