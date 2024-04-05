package com.shops.Commands;

import com.shops.Shops;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class addItem {
    public static void onAddItem(String[] arg, CommandSender sender) {
        ConfigurationSection config = Shops.getInstance().
                getConfig().
                getConfigurationSection("Items");

        if (config.contains(arg[1])) {
            ConfigurationSection se = config.getConfigurationSection(arg[1]);
            Material m = Material.valueOf(arg[2]);
            if (m != null) {
                if (!se.contains(String.valueOf(m))) {
                    int buy = Integer.parseInt(arg[3]);
                    int sell = Integer.parseInt(arg[4]);

                    if (!se.contains(String.valueOf(m))) {

                        sender.sendMessage(Shops.prefix("&6" + m + " &eAdded To &6" + se.getName() + " &7(Slot: " + arg[5] + " Page: " + arg[6]) + ")");
                        se.createSection(String.valueOf(m));
                        ConfigurationSection section = se.getConfigurationSection(String.valueOf(m));
                        section.createSection("buy");
                        section.createSection("sell");
                        section.createSection("slot");
                        section.createSection("page");
                        section.set("buy", buy);
                        section.set("sell", sell);
                        section.set("slot", Integer.parseInt(arg[5]));
                        section.set("page", Integer.parseInt(arg[6]));
                        Shops.getInstance().saveConfig();
                        Shops.getInstance().reloadConfig();
                    }
                }
            }
        }
    }
}
