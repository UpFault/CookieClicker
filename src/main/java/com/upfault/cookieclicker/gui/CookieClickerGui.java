package com.upfault.cookieclicker.gui;

import com.upfault.cookieclicker.utilities.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;

public class CookieClickerGui {
    private final Inventory inv;

    @SuppressWarnings("all")
    public CookieClickerGui() {
        inv = Bukkit.createInventory(null, 36, "§8Cookie Clicker v0.01");
        initializeItems();
    }

    public void initializeItems() {
        for(int slot = 0; slot < inv.getSize(); slot++) { inv.setItem(slot, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" ").toItemStack());}
        inv.setItem(31, new ItemBuilder(Material.BARRIER).setName("§cClose").toItemStack());
        inv.setItem(13, new ItemBuilder(Material.COOKIE).setName("§e" + 0 + " §6Cookies").setLore(
                "§6Cookies §7of course, are not a",
                "§7valid source of §anutrition§7.",
                "§7This however, does not stop them",
                "§7being §dawesome§7.",
                " ",
                "§8Status:",
                "§7You feel like making cookies.", "§7But nobody wants to eat your", "§7cookies.",
                " ",
                "§eClick to earn cookies!").toItemStack());
    }

    public void openInventory(final HumanEntity ent) {
        ent.openInventory(inv);
    }

    public Inventory getInventory() {
        return inv;
    }

}
