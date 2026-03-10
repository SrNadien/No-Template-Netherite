package nadiendev.ntp.datagen;

import nadiendev.ntp.datagen.providers.ModRecipeProvider;
import net.minecraftforge.data.event.GatherDataEvent;

public class DataGenerators {
    private DataGenerators() {}

    public static void gatherData(GatherDataEvent event) {
        var generator = event.getGenerator();

        generator.addProvider(
            event.includeServer(),
            new ModRecipeProvider(generator.getPackOutput())
        );
    }
}