package balzamingmobs.balzamingmobs;

import commands.BalzamCommands;
import crafts.BalzamCrafts;
import events.BalzamEvents;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class BalzamingMobs extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("BalzamingMobs was enabled");

        new BalzamEvents(this);

        getCommand("balzam").setExecutor(new BalzamCommands());
        getCommand("balzam").setTabCompleter(new BalzamCommands());

        new BalzamCrafts();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("BalzamingMobs was disabled");
    }
}
