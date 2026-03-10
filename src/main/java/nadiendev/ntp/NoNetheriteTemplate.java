package nadiendev.ntp;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.util.thread.SidedThreadGroups;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import net.minecraft.util.Tuple;

import nadiendev.ntp.datagen.DataGenerators;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;

@Mod("ntp")
public class NoNetheriteTemplate {
    public static final Logger LOGGER = LogManager.getLogger(NoNetheriteTemplate.class);
    public static final String MODID = "ntp";

    public NoNetheriteTemplate() {
        LOGGER.info("Initializing No Netherite Template");

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(DataGenerators::gatherData);

        LOGGER.info("No Netherite Template initialized successfully");
    }

    // ============================================
    // SERVER TICK
    // ============================================

    private static final Collection<Tuple<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();

    public static void queueServerWork(int tick, Runnable action) {
        if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER)
            workQueue.add(new Tuple<>(action, tick));
    }

    @SubscribeEvent
    public void tick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        List<Tuple<Runnable, Integer>> actions = new ArrayList<>();
        workQueue.forEach(work -> {
            work.setB(work.getB() - 1);
            if (work.getB() == 0)
                actions.add(work);
        });
        actions.forEach(e -> e.getA().run());
        workQueue.removeAll(actions);
    }
}