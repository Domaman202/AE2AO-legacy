package ru.DmN.AE2AO.mixin;

import appeng.api.networking.IGrid;
import appeng.api.networking.IGridNode;
import appeng.api.networking.events.MENetworkControllerChange;
import appeng.api.networking.pathing.ControllerState;
import appeng.api.util.AEPartLocation;
import appeng.api.util.DimensionalCoord;
import appeng.me.cache.PathGridCache;
import appeng.me.pathfinding.ControllerValidator;
import appeng.tile.networking.TileController;
import ru.DmN.AE2AO.Main;

import java.util.Set;

public class PathGridCacheMixin extends PathGridCache {
    private Set<TileController>         controllers;
    private IGrid                       myGrid;
    private boolean                     recalculateControllerNextTick;
    private ControllerState             controllerState;

    public PathGridCacheMixin(IGrid g) { super(g); }

    /**
     * @author DomamaN202
     * @reason Adding controller error system control
     */
    private void recalcController() {
        recalculateControllerNextTick = false;
        final ControllerState o = controllerState;

        if (controllers.isEmpty()) {
            controllerState = ControllerState.NO_CONTROLLER;
        } else if (Main.lc.ControllerLimits) {
            IGridNode startingNode = this.controllers.iterator().next().getGridNode(AEPartLocation.INTERNAL);
            if (startingNode == null) {
                this.controllerState = ControllerState.CONTROLLER_CONFLICT;
                return;
            }

            DimensionalCoord dc = startingNode.getGridBlock().getLocation();
            ControllerValidator cv = new ControllerValidator(dc.x, dc.y, dc.z);
            startingNode.beginVisit(cv);
            if (cv.isValid() && cv.getFound() == this.controllers.size()) {
                this.controllerState = ControllerState.CONTROLLER_ONLINE;
            } else {
                this.controllerState = ControllerState.CONTROLLER_CONFLICT;
            }
        } else {
            boolean valid = true;

            for (TileController controller : controllers) {
                final IGridNode node = controller.getGridNode(AEPartLocation.INTERNAL);
                if (node == null) {
                    this.controllerState = ControllerState.CONTROLLER_CONFLICT;
                    return;
                }

                final DimensionalCoord dc = node.getGridBlock().getLocation();
                final ControllerValidator cv = new ControllerValidator(dc.x, dc.y, dc.z);

                node.beginVisit(cv);

                if (!cv.isValid())
                    valid = false;
            }

            if (valid)
                this.controllerState = ControllerState.CONTROLLER_ONLINE;
            else
                this.controllerState = ControllerState.CONTROLLER_CONFLICT;
        }

        if (o != this.controllerState) {
            myGrid.postEvent(new MENetworkControllerChange());
        }
    }
}
