package nadiendev.ntp.datagen;

import nadiendev.ntp.datagen.providers.ModRecipeProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;


public class DataGenerators {
    private DataGenerators() {}                         

                                  
    public static void gatherData(GatherDataEvent event) {
        event.getGenerator().addProvider(              
            event.includeServer(),                     
            new ModRecipeProvider.Runner(              
                event.getGenerator().getPackOutput(),  
                event.getLookupProvider()              
            )
        );
    }
}