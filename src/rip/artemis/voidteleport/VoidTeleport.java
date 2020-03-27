package rip.artemis.voidteleport;

import org.bukkit.plugin.java.JavaPlugin;

public class VoidTeleport extends JavaPlugin {
	public VoidTeleport plugin;

	@Override
	public void onEnable() {
		plugin = this;
		saveDefaultConfig();
		reloadConfig();
		events();
		plugin.getServer().getConsoleSender().sendMessage(Color.chat("&9VOID TELEPORT HAS BEEN ENABLED"));
	}

	@Override
	public void onDisable() {
		plugin = this;
		plugin.getServer().getConsoleSender().sendMessage(Color.chat("&9VOID TELEPORT HAS BEEN DISABLED"));
	}

	public void events() {
		getServer().getPluginManager().registerEvents(new VoidEvent(this), this);
	}

}
