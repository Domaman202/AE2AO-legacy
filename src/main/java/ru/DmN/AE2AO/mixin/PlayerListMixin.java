package ru.DmN.AE2AO.mixin;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.management.PlayerChunkMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.DmN.AE2AO.Main;
import ru.DmN.AE2AO.networking.Networking;

@Mixin(PlayerChunkMap.class)
public class PlayerListMixin {
    @Inject(method = "addPlayer", at = @At("HEAD"), remap = false)
    public void addPlayer(EntityPlayerMP player, CallbackInfo ci) {
        Networking.INSTANCE.sendTo(Main.lcc, player);
    }
}
