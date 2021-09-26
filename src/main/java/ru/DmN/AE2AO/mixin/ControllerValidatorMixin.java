//package ru.DmN.AE2AO.mixin;
//
////import appeng.api.networking.IGridHost;
////import appeng.api.networking.IGridNode;
////import appeng.me.pathfinding.ControllerValidator;
////import appeng.tile.networking.TileController;
////import net.minecraft.util.math.BlockPos;
////import ru.DmN.AE2AO.Main;
//
//public class ControllerValidatorMixin extends appeng.me.pathfinding.ControllerValidator {
//    private boolean isValid;
//    private int found;
//    private int minX;
//    private int minY;
//    private int minZ;
//    private int maxX;
//    private int maxY;
//    private int maxZ;
//
//    public ControllerValidatorMixin(int x, int y, int z) { super(x, y, z); }
//
//    /**
//     * @author DomamaN202
//     * @reason Adding a size customization
//     */
//    public boolean visitNode(appeng.api.networking.IGridNode n) {
//        appeng.api.networking.IGridHost h=n.getMachine();if(isValid&&h instanceof appeng.tile.networking.TileController){net.minecraft.util.math.BlockPos pos=((appeng.tile.networking.TileController)h).getPos();minX=Math.min(pos.getX(),minX);maxX=Math.max(pos.getX(),maxX);minY=Math.min(pos.getY(),minY);maxY=Math.max(pos.getY(),maxY);minZ = Math.min(pos.getZ(), minZ);maxZ = Math.max(pos.getZ(),maxZ);if(maxX-minX<ru.DmN.AE2AO.Main.lc.Max_X&&maxY-minY<ru.DmN.AE2AO.Main.lc.Max_Y&&maxZ-minZ<ru.DmN.AE2AO.Main.lc.Max_Z){this.found++;return true;}isValid = false;}return false;
//    }
//}
