package fdsfd.sbfabric.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import fdsfd.sbfabric.screens.SBFabricScreen;
import fdsfd.sbfabric.utils.PingUtils;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;

public class SBFCommand {

    private static boolean shouldOpenScreen = false;

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("sbf")
            .executes(SBFCommand::execute1)
                .then(ClientCommandManager.literal("ping")
                        .executes(SBFCommand::execute2)));
    }

    private static int execute1 (CommandContext<FabricClientCommandSource> context) {
        shouldOpenScreen = true;
        MinecraftClient client = MinecraftClient.getInstance();

        client.execute(() -> client.setScreen(new SBFabricScreen()));

        return 1;
    }

    private static int execute2 (CommandContext<FabricClientCommandSource> context) {
        PingUtils.displayPing();
        return 1;
    }

    public static void tick(MinecraftClient client) {
        if (shouldOpenScreen) {
            client.setScreen(new SBFabricScreen());
            shouldOpenScreen = false;
            System.out.println(shouldOpenScreen);
        }
    }
}