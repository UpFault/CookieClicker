package com.upfault.cookieclicker.gui;

import com.upfault.cookieclicker.utilities.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;

public class UpgradesGui {

    //TODO: FIX THE UPGRADES GUI

    private final Inventory inv;

    public UpgradesGui() {
        inv = Bukkit.createInventory(null, 36, "§8Cookie Clicker v0.01 - Upgrades");
        initializeItems();
    }

    public void initializeItems() {
        for(int slot = 0; slot < inv.getSize(); slot++) { inv.setItem(slot, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" ").toItemStack());}
        inv.setItem(30, new ItemBuilder(Material.ARROW).setName("§aGo Back").addLoreLine("§7To Cookie Clicker v0.01").toItemStack());
        inv.setItem(31, new ItemBuilder(Material.BARRIER).setName("§cClose").toItemStack());
        inv.setItem(10, new ItemBuilder(Material.LEVER).setName("§fCursor").setLore("", "§7Description:", "", "§7AutoClicks once every", "§710 seconds.").toItemStack());
        inv.setItem(12, new ItemBuilder(Material.DIRT).setName("§6Farm").setLore("", "§7Description:", "", "§7Grows §6cookie §7plants ", "§7from §6cookie §7seeds.").toItemStack());
        inv.setItem(14, new ItemBuilder(Material.TOTEM_OF_UNDYING).setName("§dGranny").setLore("", "§7Description:", "", "§7A nice grandma to bake ", "§7more §6cookies§7.").toItemStack());
    }

    public void openInventory(final HumanEntity ent) {
        ent.openInventory(inv);
    }
}
