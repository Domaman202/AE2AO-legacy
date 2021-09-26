package ru.DmN.AE2AO.core;

import javassist.*;
import net.minecraftforge.fml.relauncher.CoreModManager;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nullable;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.CodeSource;
import java.util.Map;

public class CoreMod implements IFMLLoadingPlugin {
    static ClassPool pool = ClassPool.getDefault();

    public CoreMod() throws NotFoundException, CannotCompileException {
        // Mixin "PlayerList"
        CtClass clazz = pool.get("net.minecraft.server.management.PlayerChunkMap");
        clazz.getMethod("addPlayer", "(Lnet/minecraft/entity/player/EntityPlayerMP;)V").insertBefore("{ru.DmN.AE2AO.networking.Networking.INSTANCE.sendTo(ru.DmN.AE2AO.Main.lcc, player);}");
        clazz.toClass();
        //
        clazz = pool.get("appeng.me.pathfinding.ControllerValidator");
        clazz.removeMethod(clazz.getMethod("visitNode", "(Lappeng/api/networking/IGridNode;)Z"));
        clazz.addMethod(CtMethod.make("boolean visitNode(appeng.api.networking.IGridNode n){appeng.api.networking.IGridHost h=n.getMachine();if(isValid&&h instanceof appeng.tile.networking.TileController){net.minecraft.util.math.BlockPos pos=((appeng.tile.networking.TileController)h).getPos();minX=Math.min(pos.getX(),minX);maxX=Math.max(pos.getX(),maxX);minY=Math.min(pos.getY(),minY);maxY=Math.max(pos.getY(),maxY);minZ = Math.min(pos.getZ(), minZ);maxZ = Math.max(pos.getZ(),maxZ);if(maxX-minX<ru.DmN.AE2AO.Main.lc.Max_X&&maxY-minY<ru.DmN.AE2AO.Main.lc.Max_Y&&maxZ-minZ<ru.DmN.AE2AO.Main.lc.Max_Z){this.found++;return true;}isValid = false;}return false;}", clazz));
        clazz.toClass();
        // Я хз что это такое, но пусть будет
        CodeSource codeSource = this.getClass().getProtectionDomain().getCodeSource();
        if (codeSource != null) {
            URL location = codeSource.getLocation();
            try {
                File file = new File(location.toURI());
                if (file.isFile()) {
                    CoreModManager.getReparseableCoremods().remove(file.getName());
                }
            } catch (URISyntaxException ignored) {}
        } else {
            LogManager.getLogger().warn("No CodeSource, if this is not a development environment we might run into problems!");
            LogManager.getLogger().warn(this.getClass().getProtectionDomain());
        }
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
