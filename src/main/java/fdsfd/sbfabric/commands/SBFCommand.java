package fdsfd.sbfabric.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import fdsfd.sbfabric.SBFabric;
import fdsfd.sbfabric.screens.SBFabricScreen;
import fdsfd.sbfabric.utils.PingUtils;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;

public class SBFCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("sbf")
            .executes(SBFCommand::execute1)
                .then(ClientCommandManager.literal("ping")
                        .executes(SBFCommand::execute2)));
    }

    private static int execute1 (CommandContext<FabricClientCommandSource> context) {
        MinecraftClient client = MinecraftClient.getInstance();
        SBFabricScreen screen = new SBFabricScreen();

        // client.setScreen(new SBFabricScreen());
        // Not functional.
        return 1;
    }

    private static int execute2 (CommandContext<FabricClientCommandSource> context) {
        PingUtils.displayPing();
        return 1;
    }
}