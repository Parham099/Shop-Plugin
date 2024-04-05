package com.shops;

import Menus.Manage;
import Menus.Main.MainManage;
import com.shops.Commands.Commands;
import com.shops.SellAndBuy.BuyManage;
import com.shops.SellAndBuy.SellManage;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class Shops extends JavaPlugin {

    public static Plugin instance;
    private static Economy econ = null;
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        saveDefaultConfig();
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new BuyManage(), this);
        pm.registerEvents(new SellManage(), this);
        pm.registerEvents(new MainManage(), this);
        pm.registerEvents(new Manage(), this);

        Bukkit.getServer().getPluginCommand("shop").setExecutor(new Commands());

        if (!setupEconomy() ) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static Plugin getInstance() {
        return instance;
    }
    public static Economy getEconomy() {
        return econ;
    }
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
    public static String prefix(String arg) {
        return ChatColor.translateAlternateColorCodes('&',getInstance().getConfig().getString("Prefix") + " " + arg);
    }
}
