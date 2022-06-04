package com.upfault.cookieclicker;

import com.upfault.cookieclicker.commands.CookieCommand;
import com.upfault.cookieclicker.events.InventoryClicks;
import com.upfault.cookieclicker.events.PlayerJoin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class CookieClicker extends JavaPlugin {

    private static CookieClicker instance;

    @Override
    public void onEnable() {
        instance = this;
        registerCommands();
        registerEvents();
        generateFile();
    }

    private void generateFile() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new InventoryClicks(), this);
    }

    private void registerCommands() {
        Objects.requireNonNull(getServer().getPluginCommand("cookie")).setExecutor(new CookieCommand());
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static CookieClicker getInstance() {
        return instance;
    }
}
