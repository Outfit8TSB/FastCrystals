package club.aurorapvp.fastcrystals;

import club.aurorapvp.fastcrystals.listeners.bukkit.BukkitEventManager;
import club.aurorapvp.fastcrystals.listeners.packet.PacketEventManager;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.bukkit.Location;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.plugin.java.JavaPlugin;

public final class FastCrystals extends JavaPlugin {

  private static FastCrystals INSTANCE;
  private static final Set<Integer> CRYSTAL_IDS = ConcurrentHashMap.newKeySet();
  private static final Map<Integer, EnderCrystal> CRYSTALS = new ConcurrentHashMap<>();
  private static final Set<Location> CRYSTAL_LOCATIONS = ConcurrentHashMap.newKeySet();
  private static int lastEntityId;

  public static FastCrystals getInstance() {
    return INSTANCE;
  }

  @Override
  public void onEnable() {
    INSTANCE = this;

    BukkitEventManager.init();
    PacketEventManager.init();
  }

  public static int getLastEntityId() {
    return lastEntityId;
  }

  public static void setLastEntityId(int entityId) {
    lastEntityId = entityId;
  }

  public static boolean containsCrystal(Location loc) {
    return CRYSTAL_LOCATIONS.contains(loc);
  }

  public static EnderCrystal getCrystal(int entityId) {
    return CRYSTALS.get(entityId);
  }

  public static void registerId(int entityId) {
    CRYSTAL_IDS.add(entityId);
  }

  public static void unegisterId(int entityId) {
    CRYSTAL_IDS.remove(entityId);
  }

  public static void addCrystal(int entityId, EnderCrystal crystal) {
    CRYSTAL_IDS.add(entityId);
    CRYSTALS.put(entityId, crystal);
    CRYSTAL_LOCATIONS.add(crystal.getLocation());
  }

  public static void removeCrystal(int entityId) {
    CRYSTAL_IDS.remove(entityId);
    CRYSTAL_LOCATIONS.remove(CRYSTALS.remove(entityId).getLocation());
  }

  public static boolean isAlive(int entityId) {
    return CRYSTAL_IDS.contains(entityId);
  }
}