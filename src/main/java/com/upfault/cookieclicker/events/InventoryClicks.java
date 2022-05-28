package com.upfault.cookieclicker.events;

import com.upfault.cookieclicker.gui.CookieClickerGui;
import com.upfault.cookieclicker.utilities.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Arrays;
import java.util.Objects;

public class InventoryClicks implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void cancelAllEvents(InventoryClickEvent e) {
        if(e.getClickedInventory() == null) return;
        if(Arrays.asList(e.getClickedInventory().getContents()).contains(new CookieClickerGui().getInventory().getContents()[0])) {
            e.setCancelled(true);
        }
        e.setCancelled(true);
    }

    private static int count = 0;
    public void addCookies(int amount) {
        count += amount;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void itemClicks(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if(e.getCurrentItem() != null) {
            if (e.getSlot() == 13) {
                addCookies(1);
                Objects.requireNonNull(e.getClickedInventory()).setItem(13, new ItemBuilder(Material.COOKIE).setName("§e" + count + " §6Cookies").setLore(setStatus(count)).toItemStack());
            }
            if (e.getSlot() == 31) {
                player.closeInventory();
            }
        }
    }

    public String[] setStatus(int amount) {
        if(amount >= 0 && amount < 5) {
            return new String[]{"§6Cookies §7of course, are not a", "§7valid source of §anutrition§7.", "§7This however, does not stop them", "§7being §dawesome§7.", " ", "§8Status:", "§7You feel like making cookies.", "§7But nobody wants to eat your", "§7cookies.", " ", "§eClick to earn cookies!" };
        }
        if(amount >= 5 && amount < 50) {
            return new String[]{"§6Cookies §7of course, are not a", "§7valid source of §anutrition§7.", "§7This however, does not stop them", "§7being §dawesome§7.", " ", "§8Status:", "§7Your first batch goes to the", "§7trash. The local rats barely", "§7want to touch them.", " ", "§eClick to earn cookies!" };
        }
        if(amount >= 50 && amount < 100) {
            return new String[]{"§6Cookies §7of course, are not a", "§7valid source of §anutrition§7.", "§7This however, does not stop them", "§7being §dawesome§7.", " ", "§8Status:", "§7Your family reluctantly tries", "§7some of your cookies.", " ", "§eClick to earn cookies!"};
        }
        if(amount >= 100 && amount < 500) {
            return new String[]{"§6Cookies §7of course, are not a", "§7valid source of §anutrition§7.", "§7This however, does not stop them", "§7being §dawesome§7.", " ", "§8Status:", "§7Your cookies are popular in the", "§7neighborhood.", " ", "§eClick to earn cookies!"};
        }
        if(amount >= 500 && amount < 1000) {
            return new String[]{"§6Cookies §7of course, are not a", "§7valid source of §anutrition§7.", "§7This however, does not stop them", "§7being §dawesome§7.", " ", "§8Status:", "§7People are starting to talk", "§7about your cookies.", " ", "§eClick to earn cookies!"};
        }
        if(amount >= 1000 && amount < 5000) {
            return new String[]{"§6Cookies §7of course, are not a", "§7valid source of §anutrition§7.", "§7This however, does not stop them", "§7being §dawesome§7.", " ", "§8Status:", "§7Your cookies are talked about", "§7for miles around.", " ", "§eClick to earn cookies!"};
        }
        if(amount >= 5000 && amount < 10000) {
            return new String[]{"§6Cookies §7of course, are not a", "§7valid source of §anutrition§7.", "§7This however, does not stop them", "§7being §dawesome§7.", " ", "§8Status:", "§7Your cookies are renowned", "§7in the whole town!", " ", "§eClick to earn cookies!"};
        }
        if(amount >= 10000 && amount < 50000) {
            return new String[]{"§6Cookies §7of course, are not a", "§7valid source of §anutrition§7.", "§7This however, does not stop them", "§7being §dawesome§7.", " ", "§8Status:", "§7Your cookies bring all the", "§7boys to the yard.", " ", "§eClick to earn cookies!"};
        }
        if(amount >= 50000 && amount < 100000) {
            return new String[]{"§6Cookies §7of course, are not a", "§7valid source of §anutrition§7.", "§7This however, does not stop them", "§7being §dawesome§7.", " ", "§8Status:", "§7Your cookies now have", "§7their own website!", " ", "§eClick to earn cookies!"};
        }
        if(amount >= 100000) {
            return new String[]{"§6Cookies §7of course, are not a", "§7valid source of §anutrition§7.", "§7This however, does not stop them", "§7being §dawesome§7.", " ", "§8Status:", "§7Your cookies are worth", "§7a lot of money.", " ", "§eClick to earn cookies!"};
        }
        return new String[]{"§cIf you see this", "§csomething went wrong."};
    }
}
