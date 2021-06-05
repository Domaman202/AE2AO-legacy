package ru.DmN.AE2AO.networking;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import ru.DmN.AE2AO.Config;

public class Networking {
    public static SimpleNetworkWrapper INSTANCE;

    public static void RegisterMessages() {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("ae2ao_config");

        INSTANCE.registerMessage(Config.Handler.class, Config.class, 1, Side.SERVER);
        INSTANCE.registerMessage(Config.Handler.class, Config.class, 2, Side.CLIENT);
    }
}
