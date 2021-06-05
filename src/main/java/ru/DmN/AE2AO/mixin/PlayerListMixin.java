package ru.DmN.AE2AO.mixin;

import codechicken.lib.packet.PacketCustom;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.management.PlayerChunkMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.DmN.AE2AO.Main;

@Mixin(PlayerChunkMap.class)
public class PlayerListMixin {
    @Inject(method = "addPlayer", at = @At("HEAD"), remap = false)
    public void addPlayer(EntityPlayerMP player, CallbackInfo ci) {
        Main.lcc.toPacket(new PacketCustom(Main.MOD_ID, 1)).sendToPlayer(player);
    }
}
