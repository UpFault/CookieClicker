package com.upfault.cookieclicker.events;

import com.upfault.cookieclicker.CookieClicker;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(!player.hasPlayedBefore() || !CookieClicker.getInstance().getConfig().contains(player.getUniqueId().toString())) {
            CookieClicker.getInstance().getConfig().set(player.getName() + ".uuid", player.getUniqueId().toString());
            CookieClicker.getInstance().getConfig().set(player.getName() + ".highest_cookies", 0);
            CookieClicker.getInstance().getConfig().set(player.getName() + ".upgrades.cursor", 0);
            CookieClicker.getInstance().getConfig().set(player.getName() + ".upgrades.farm", 0);
            CookieClicker.getInstance().getConfig().set(player.getName() + ".upgrades.granny", 0);
            CookieClicker.getInstance().saveConfig();
        } else {
            return;
        }
    }
}
