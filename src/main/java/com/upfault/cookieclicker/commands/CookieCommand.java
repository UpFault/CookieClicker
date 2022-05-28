package com.upfault.cookieclicker.commands;

import com.upfault.cookieclicker.gui.CookieClickerGui;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;

public class CookieCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) {
            Bukkit.getLogger().log(Level.SEVERE, "CookieClicker: This command can only be run by a player.");
            return true;
        }

        new CookieClickerGui().openInventory((HumanEntity) sender);
        return true;
    }
}
