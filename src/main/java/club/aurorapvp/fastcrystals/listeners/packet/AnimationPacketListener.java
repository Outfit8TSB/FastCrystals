package club.aurorapvp.fastcrystals.listeners.packet;

import club.aurorapvp.fastcrystals.player.CrystalPlayer;
import club.aurorapvp.fastcrystals.enums.AnimationType;
import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType.Play.Client;
import com.github.retrooper.packetevents.protocol.player.DiggingAction;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerDigging;

public class AnimationPacketListener implements PacketListener {
  @Override
  public void onPacketReceive(PacketReceiveEvent event) {
    if (event.getUser().getUUID() == null) {
      return;
    }

    CrystalPlayer player = CrystalPlayer.getPlayer(event.getUser().getUUID());

    if (player == null) {
      return;
    }

    if (event.getPacketType() == Client.INTERACT_ENTITY) {
      WrapperPlayClientInteractEntity wrapper = new WrapperPlayClientInteractEntity(event);

      if (wrapper.getAction() == WrapperPlayClientInteractEntity.InteractAction.ATTACK) {
        player.setLastAnimation(AnimationType.ATTACK);
      }

      return;
    }

    if (event.getPacketType() != Client.PLAYER_DIGGING) {
      player.setLastAnimation(AnimationType.IGNORE);
      return;
    }

    WrapperPlayClientPlayerDigging wrapper = new WrapperPlayClientPlayerDigging(event);

    if (wrapper.getAction() == DiggingAction.DROP_ITEM
        || wrapper.getAction() == DiggingAction.DROP_ITEM_STACK) {
      player.setLastAnimation(AnimationType.IGNORE);
    } else if (wrapper.getAction() == DiggingAction.START_DIGGING) {
      player.setLastAnimation(AnimationType.START_DIGGING);
    }
  }
}