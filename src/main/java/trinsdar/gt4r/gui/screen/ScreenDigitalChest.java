package trinsdar.gt4r.gui.screen;

import muramasa.antimatter.gui.screen.ScreenMachine;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;
import trinsdar.gt4r.gui.ContainerDigitalChest;
import trinsdar.gt4r.tile.single.TileEntityDigitalChest;

public class ScreenDigitalChest extends ScreenMachine<TileEntityDigitalChest, ContainerDigitalChest> {
    public ScreenDigitalChest(ContainerDigitalChest container, Inventory inv, Component name) {
        super(container, inv, name);
        this.imageHeight = 221;
    }
}
