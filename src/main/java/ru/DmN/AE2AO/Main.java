package ru.DmN.AE2AO;

import com.moandjiezana.toml.Toml;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.DmN.AE2AO.networking.CommonProxy;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

@Mod(modid = Main.MOD_ID)
public class Main {
    public static final String MOD_ID = "ae2ao";
    // Logger
    static final Logger LOGGER = LogManager.getLogger();
    // Config
    public static Config lcc = null;
    public static Config lc = null;
    //
    @SidedProxy(clientSide = "ru.DmN.AE2AO.networking.ClientProxy", serverSide = "ru.DmN.AE2AO.networking.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        //
        MinecraftForge.EVENT_BUS.register(this);
        //
        proxy.preinit(event);
        // Config init
        try {
            File conf = new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + "ae2ao.toml");

            if (conf.createNewFile()) {
                try (FileOutputStream stream = new FileOutputStream(conf)) {
                    stream.write("DisableChannels = false\nControllerLimits = false\nMax_X = 7\nMax_Y = 7\nMax_Z = 7\nSCFD = false\nChatInfo = true".getBytes(StandardCharsets.UTF_8));
                }

                lcc = new Config();
                lc = new Config();
            } else {
                lcc = new Toml().read(conf).to(Config.class);
                lc = lcc.clone();
            }
        } catch (Exception e) {
            LOGGER.throwing(e);
        }
    }
}
