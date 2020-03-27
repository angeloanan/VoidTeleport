package rip.artemis.voidteleport;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class VoidEvent implements Listener {
	private VoidTeleport plugin;

	public VoidEvent(VoidTeleport instance) {
		plugin = instance;
	}

	@EventHandler
	public void voidCoord(PlayerMoveEvent e) {
		if (e.getTo().getY() < -10) {
			Player p = e.getPlayer();
			p.setHealth(20);
			Location l = p.getLocation();
			int x = l.getBlockX();
			int y = l.getBlockY();
			int z = l.getBlockZ();
			String world = plugin.getConfig().getString("world-name");
			boolean heightenabled = plugin.getConfig().getBoolean("height-enabled");
			int height = plugin.getConfig().getInt("height");
			if (plugin.getConfig().getBoolean("kill-instantly") == true) {
				p.setFallDistance(8888);
			} else {
				p.setFallDistance(0);
			}
			if (plugin.getConfig().getBoolean("teleport-to-bedspawn") == true) {
				p.teleportAsync(p.getBedSpawnLocation());
			} else if (plugin.getConfig().getBoolean("teleport-to-world-spawn") == true) {
				p.teleportAsync(plugin.getServer().getWorld(world).getSpawnLocation());
			} else {
				Location lh = new Location(plugin.getServer().getWorld(world), x, heightenabled ? height : y + 0.1, z);
				Block b = plugin.getServer().getWorld(world).getHighestBlockAt(lh);
				Location bl = b.getLocation();
				int xh = bl.getBlockX();
				int yh = bl.getBlockY();
				int zh = bl.getBlockZ();
				p.teleportAsync(new Location(plugin.getServer().getWorld(world), xh, heightenabled ? height : yh, zh));
			}
		}
	}
}
