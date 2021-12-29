package trinsdar.gt4r.tile.multi;

import muramasa.antimatter.capability.machine.MachineEnergyHandler;
import muramasa.antimatter.capability.machine.MultiMachineEnergyHandler;
import muramasa.antimatter.gui.event.GuiEvents;
import muramasa.antimatter.gui.event.IGuiEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.multi.TileEntityMultiMachine;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import tesseract.api.gt.IEnergyHandler;
import tesseract.api.gt.IGTNode;

import java.util.Arrays;

public class TileEntityFusionReactor extends TileEntityMultiMachine<TileEntityFusionReactor> {

    Display display = Display.REGULAR;

    public TileEntityFusionReactor(Machine<?> type) {
        super(type);
        this.energyHandler.set(() -> new MultiMachineEnergyHandler<TileEntityFusionReactor>( this){
            public void onStructureBuild() {
                super.onStructureBuild();

            }
        });
    }

    /*@Override
    public void onRecipeFound() {
        consumeEnergy(activeRecipe.getSpecialValue());
        System.out.println("Consumed Starting Energy");
    }*/


    public Display getDisplay() {
        return display;
    }

    @Override
    public CompoundNBT save(CompoundNBT tag) {
        super.save(tag);
        tag.putInt("display", display.ordinal());
        return tag;
    }

    @Override
    public void load(BlockState state, CompoundNBT tag) {
        super.load(state, tag);
        this.display = Display.values()[tag.getInt("display")];
    }

    @Override
    public void onGuiEvent(IGuiEvent event, PlayerEntity playerEntity) {
        super.onGuiEvent(event, playerEntity);
        if (event.getFactory() == GuiEvents.EXTRA_BUTTON){
            GuiEvents.GuiEvent ev =(GuiEvents.GuiEvent) event;
            int[] data = ev.data;
            if (data[1] == 0){
                this.display = Display.REGULAR;
            } else if (data[1] == 1){
                this.display = Display.MIDDLE;
            } else if (data[1] == 2){
                this.display = Display.TOP_BOTTOM;
            }
        }
    }

    public enum Display{
        REGULAR,
        MIDDLE,
        TOP_BOTTOM
    }
}
