package ru.DmN.AE2AO.mixin;

import appeng.api.networking.IGridBlock;
import appeng.me.GridNode;
import ru.DmN.AE2AO.Main;

public class GridNodeMixin extends GridNode {
    private int usedChannels;

    public GridNodeMixin(IGridBlock what) { super(what); }

    /**
     * @author DomamaN202
     * @reason Add system turn off channels
     */
    private int getUsedChannels()
    {
        return Main.lc.DisableChannels ? 1 : usedChannels;
    }
}
