package ru.DmN.AE2AO.networking;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
    public void preinit(FMLPreInitializationEvent e) {
        Networking.RegisterMessages();
    }
}