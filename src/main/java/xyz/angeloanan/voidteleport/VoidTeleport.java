package xyz.angeloanan.voidteleport;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
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
    public void voidTP(EntityDamageEvent e) {
        if (e.getEntityType() == EntityType.PLAYER) {
            if (e.getEntity().getLocation().getY() < 0) {
                Player p = (Player) e.getEntity();
                Location tp_coord = getTPCoord(p);

                p.setFallDistance(8888);
                p.teleportAsync(tp_coord);

                String player_name = p.getPlayerListName();
                getServer().broadcastMessage(player_name.concat(" has been teleported because he fell to the void."));
            }
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

