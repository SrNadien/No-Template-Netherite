package nadiendev.ntp.datagen;

import nadiendev.ntp.datagen.providers.ModRecipeProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class DataGenerators {
    private DataGenerators() {}

    public static void gatherData(GatherDataEvent.Client event) {
        event.createProvider(ModRecipeProvider.Runner::new);
    }
}