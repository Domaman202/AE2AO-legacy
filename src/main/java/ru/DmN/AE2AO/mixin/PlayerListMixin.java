package ru.DmN.AE2AO.mixin;

import net.minecraft.entity.player.EntityPlayerMP;
import ru.DmN.AE2AO.Main;
import ru.DmN.AE2AO.networking.Networking;

public class PlayerListMixin {
    public void addPlayer(EntityPlayerMP player) {
        Networking.INSTANCE.sendTo(Main.lcc, player);
    }
}
