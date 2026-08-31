package nadiendev.ntp;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.neoforged.neoforge.network.handling.IPayloadHandler;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.fml.util.thread.SidedThreadGroups;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.IEventBus;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.FriendlyByteBuf;

import nadiendev.ntp.datagen.DataGenerators;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Collection;
import java.util.ArrayList;

@Mod("ntp")
public class NoNetheriteTemplate {
    public static final Logger LOGGER = LogManager.getLogger(NoNetheriteTemplate.class);
    public static final String MODID = "ntp";

    public NoNetheriteTemplate(IEventBus modEventBus) {
        LOGGER.info("Initializing No Netherite Template");

        NeoForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::registerNetworking);
        modEventBus.addListener(DataGenerators::gatherData);

        LOGGER.info("No Netherite Template initialized successfully");
    }

    // ============================================
    // NETWORKING
    // ============================================

    private static boolean networkingRegistered = false;
    private static final Map<CustomPacketPayload.Type<?>, NetworkMessage<?>> MESSAGES = new HashMap<>();

    private record NetworkMessage<T extends CustomPacketPayload>(
        StreamCodec<? extends FriendlyByteBuf, T> reader,
        IPayloadHandler<T> handler
    ) {}

    public static <T extends CustomPacketPayload> void addNetworkMessage(
        CustomPacketPayload.Type<T> id,
        StreamCodec<? extends FriendlyByteBuf, T> reader,
        IPayloadHandler<T> handler
    ) {
        if (networkingRegistered)
            throw new IllegalStateException("Cannot register new network messages after networking has been registered");
        MESSAGES.put(id, new NetworkMessage<>(reader, handler));
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private void registerNetworking(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(MODID);
        MESSAGES.forEach((id, networkMessage) ->
            registrar.playBidirectional(
                id,
                ((NetworkMessage) networkMessage).reader(),
                ((NetworkMessage) networkMessage).handler()
            )
        );
        networkingRegistered = true;
    }

    // ============================================
    // SERVER TICK
    // ============================================

    private static final Collection<Tuple<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();

	private static final class ScheduledWork {
		private final Runnable action;
		private int ticks;

		private ScheduledWork(Runnable action, int ticks) {
			this.action = action;
			this.ticks = ticks;
		}
	}

	private static final Collection<ScheduledWork> workQueue = new ConcurrentLinkedQueue<>();

	public static void queueServerWork(int tick, Runnable action) {
		if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER)
			workQueue.add(new ScheduledWork(action, tick));
	}

	@SubscribeEvent
	public void tick(ServerTickEvent.Post event) {
		List<ScheduledWork> actions = new ArrayList<>();
		workQueue.forEach(work -> {
			work.ticks--;
			if (work.ticks == 0)
				actions.add(work);
		});
		actions.forEach(e -> e.action.run());
		workQueue.removeAll(actions);
	}
}