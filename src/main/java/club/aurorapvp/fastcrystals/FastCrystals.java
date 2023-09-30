package club.aurorapvp.fastcrystals;

import club.aurorapvp.fastcrystals.listeners.bukkit.BukkitEventManager;
import club.aurorapvp.fastcrystals.listeners.packet.PacketEventManager;
import com.destroystokyo.paper.event.entity.EntityRemoveFromWorldEvent;
import com.github.retrooper.packetevents.event.PacketEvent;
import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType.Play.Client;
import com.github.retrooper.packetevents.protocol.player.InteractionHand;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity.InteractAction;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerDestroyEntities;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import org.bukkit.Bukkit;
import org.bukkit.FluidCollisionMode;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

public final class FastCrystals extends JavaPlugin implements PacketListener, Listener {

  private static FastCrystals INSTANCE;
  private static final Map<Integer, EnderCrystal> CRYSTAL_IDS = new ConcurrentHashMap<>();

  public static FastCrystals getInstance() {
    return INSTANCE;
  }

  @Override
  public void onEnable() {
    INSTANCE = this;

    BukkitEventManager.init();
    PacketEventManager.init();
  }

  public static EnderCrystal getCrystal(int entityId) {
    return CRYSTAL_IDS.get(entityId);
  }

  public static void addCrystal(int entityId, EnderCrystal crystal) {
    CRYSTAL_IDS.put(entityId, crystal);
  }

  public static void removeCrystal(int entityId) {
    CRYSTAL_IDS.remove(entityId);
  }
}