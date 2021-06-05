package ru.DmN.AE2AO.networking;

import codechicken.lib.packet.PacketCustom;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import ru.DmN.AE2AO.Main;

public class ClientProxy extends CommonProxy {
    public void preinit(FMLPreInitializationEvent e) {
        super.preinit(e);
        PacketCustom.assignHandler(Main.MOD_ID, new Networking.CPH());
    }
}