package ru.DmN.AE2AO.networking;

import codechicken.lib.packet.PacketCustom;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import ru.DmN.AE2AO.Main;

public class CommonProxy {
    public void preinit(FMLPreInitializationEvent e) {
        //Даже, если у вас есть отдельный класс для серверного прокси, все равно регистрацию серверного обработчика делайте здесь
        PacketCustom.assignHandler(Main.MOD_ID, new Networking.SPH());
    }
}