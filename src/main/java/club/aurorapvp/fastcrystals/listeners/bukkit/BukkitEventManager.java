package club.aurorapvp.fastcrystals.listeners.bukkit;

import club.aurorapvp.fastcrystals.FastCrystals;
import org.bukkit.Bukkit;

public class BukkitEventManager {

  public static void init() {
    Bukkit.getPluginManager().registerEvents(new EntityEventListener(), FastCrystals.getInstance());
    Bukkit.getPluginManager().registerEvents(new PlayerEventListener(), FastCrystals.getInstance());
  }
}
