package club.aurorapvp.fastcrystals.listeners.packet;

import club.aurorapvp.fastcrystals.FastCrystals;
import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.entity.type.EntityTypes;
import com.github.retrooper.packetevents.protocol.packettype.PacketType.Play.Server;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerSpawnEntity;

public class EntityPacketListener implements PacketListener {

  @Override
  public void onPacketSend(PacketSendEvent event) {
    if (event.getPacketType() != Server.SPAWN_ENTITY) {
      return;
    }

    WrapperPlayServerSpawnEntity spawnPacket = new WrapperPlayServerSpawnEntity(event);

    if (spawnPacket.getEntityType() != EntityTypes.END_CRYSTAL) {
      return;
    }

    if (FastCrystals.isAlive(spawnPacket.getEntityId())) {
      event.setCancelled(true);
    }
  }
}
