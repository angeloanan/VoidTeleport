package xyz.angeloanan.voidteleport;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class VoidTeleport extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        System.out.println("[VoidTeleport] Plugin is enabled");
    }

    @Override
    public void onDisable() {
        System.out.println("[VoidTeleport] Plugin is disabled");
    }

    @EventHandler
    public void voidCoord(PlayerMoveEvent e) {
        if (e.getTo().getY() < -10){
            Player p = e.getPlayer();
            Location tp_coord = getTPCoord(p);

            p.setFallDistance(8888);
            p.teleportAsync(tp_coord);
        }
    }

    public Location getTPCoord(Player p) {
        Location player_bed = p.getBedSpawnLocation();
        if (player_bed != null) {
            return player_bed;
        } else {
            Location spawn = p.getWorld().getSpawnLocation();
            return p.getWorld().getHighestBlockAt(spawn).getLocation();
        }
    };
};

