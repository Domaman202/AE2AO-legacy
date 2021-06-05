package ru.DmN.AE2AO.networking;

import codechicken.lib.packet.ICustomPacketHandler;
import codechicken.lib.packet.PacketCustom;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.INetHandlerPlayServer;
import ru.DmN.AE2AO.Main;

public class Networking {
    public static class SPH implements ICustomPacketHandler.IServerPacketHandler {
        @Override
        public void handlePacket(PacketCustom packetCustom, EntityPlayerMP entityPlayerMP, INetHandlerPlayServer iNetHandlerPlayServer) {

        }
    }

    public static class CPH implements ICustomPacketHandler.IClientPacketHandler {
        @Override
        public void handlePacket(PacketCustom packetCustom, Minecraft minecraft, INetHandlerPlayClient iNetHandlerPlayClient) {
            Main.lc.ofPacket(packetCustom);
        }
    }
}
