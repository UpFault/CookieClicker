package com.upfault.cookieclicker;

import com.upfault.cookieclicker.gui.CookieClickerGui;
import com.upfault.cookieclicker.gui.UpgradesGui;
import com.upfault.cookieclicker.utilities.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;
import java.util.logging.Level;

@SuppressWarnings("all")
public final class CookieClicker extends JavaPlugin implements CommandExecutor, Listener {

    private static CookieClicker instance;
    private static int count = 0;
    public void addCookies(int amount) {
        count += amount;
    }
    public void removeCookies(int amount) {count -= amount;}

    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(this, this);
        Objects.requireNonNull(getCommand("cookie")).setExecutor(this);
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) {
            Bukkit.getLogger().log(Level.SEVERE, "CookieClicker: This command can only be run by a player.");
            return true;
        }
        new CookieClickerGui().openInventory((HumanEntity) sender);
        return true;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(!player.hasPlayedBefore() || !CookieClicker.getInstance().getConfig().contains(player.getName())) {
            CookieClicker.getInstance().getConfig().set(player.getName() + ".uuid", player.getUniqueId().toString());
            CookieClicker.getInstance().getConfig().set(player.getName() + ".highest_cookies", 0);
            CookieClicker.getInstance().getConfig().set(player.getName() + ".upgrades.cursor", 0);
            CookieClicker.getInstance().getConfig().set(player.getName() + ".upgrades.farm", 0);
            CookieClicker.getInstance().getConfig().set(player.getName() + ".upgrades.granny", 0);
            CookieClicker.getInstance().saveConfig();
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        //check if count is hifghter than highest_cookies
        Player player = event.getPlayer();
        if(count > CookieClicker.getInstance().getConfig().getInt(player.getName() + ".highest_cookies")) {
            CookieClicker.getInstance().getConfig().set(player.getName() + ".highest_cookies", count);
            CookieClicker.getInstance().saveConfig();
            count = 0;
        }
        count = 0;
    }

    @EventHandler
    private void ClickEvents(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        /* Cancel Clicks */
        if(e.getView().getTitle().equals("§8Cookie Clicker v0.01 - Upgrades") || e.getView().getTitle().equals("§8Cookie Clicker v0.01")) {
            e.setCancelled(true);
            e.setResult(Event.Result.DENY);

            if (e.getCurrentItem() != null) {
                /*  Cookie GUI */
                if (e.getView().getTitle().equals("§8Cookie Clicker v0.01")) {
                    if (e.getSlot() == 13) {
                        addCookies(1);
                        Objects.requireNonNull(e.getClickedInventory()).setItem(13, new ItemBuilder(Material.COOKIE).setName("§e" + count + " §6Cookies").setLore(setStatus(count)).toItemStack());
                        if(Math.random() < 0.00001) {
                            if(player.getInventory().firstEmpty() != -1) {
                                player.getInventory().addItem(new ItemBuilder(Material.COOKIE).setName("§6Booster Cookie").setLore("§7Consume to gain the §dCookie Buff", "§7for §b4 §7days:", "", "§8‣ §7Ability to gain §bBits§7!", "§8‣ §b+20% §7Skill XP", "§8‣ §b+15✯ §7Magic Find", "§8‣ §7Keep §6coins §7and §deffects §7on death", "§8‣ §ePermafly §7on private islands", "§8‣ §7Access §6/ah §7 and §6/bazaar §7anywhere", "§8‣ §7Sell items directly to the trades menu", "§8‣ §7AFK §aimmunity §7on your island", "§8‣ §7Toggle specific §dpotion effects", "§8‣ §7Access to §6/anvil §7and §6/etable", "", "§6§lLEGENDARY", "", "§8Unique ID: " + UUID.randomUUID()).toItemStack());
                            } else {
                                player.getWorld().dropItem(player.getLocation(), new ItemBuilder(Material.COOKIE).setName("§6Booster Cookie").setLore("§7Consume to gain the §dCookie Buff", "§7for §b4 §7days:", "", "§8‣ §7Ability to gain §bBits§7!", "§8‣ §b+20% §7Skill XP", "§8‣ §b+15✯ §7Magic Find", "§8‣ §7Keep §6coins §7and §deffects §7on death", "§8‣ §ePermafly §7on private islands", "§8‣ §7Access §6/ah §7 and §6/bazaar §7anywhere", "§8‣ §7Sell items directly to the trades menu", "§8‣ §7AFK §aimmunity §7on your island", "§8‣ §7Toggle specific §dpotion effects", "§8‣ §7Access to §6/anvil §7and §6/etable", "", "§6§lLEGENDARY", "", "§8Unique ID: " + UUID.randomUUID()).toItemStack());
                            }
                            player.sendMessage("§d§lCRAZY RARE DROP! §7(§6Booster Cookie§7)");
                        }
                    }
                    if (e.getSlot() == 31) {
                        player.closeInventory();
                    }
                    if (e.getSlot() == 32) {
                        new UpgradesGui().openInventory(player);
                    }
                    /* Upgrades GUI */
                } else if (e.getView().getTitle().equals("§8Cookie Clicker v0.01 - Upgrades")) {
                    if (e.getSlot() == 30) {
                        new CookieClickerGui().openInventory(player);
                        e.getInventory().setItem(13, new ItemBuilder(Material.COOKIE).setName("§e" + count + " §6Cookies").setLore(setStatus(count)).toItemStack());
                    }
                    if (e.getSlot() == 31) {
                        player.closeInventory();
                    }
                }
            }
        }
    }

    public static CookieClicker getInstance() {
        return instance;
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

    //TODO: add duplicate cookie checker

    @Override
    public void onDisable() {
        count = 0;
        instance = null;
    }
}
