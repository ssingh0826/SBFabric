package fdsfd.sbfabric.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import fdsfd.sbfabric.screens.SBFabricScreen;
import fdsfd.sbfabric.utils.PingUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class SBFCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("sbf")
            .executes(SBFCommand::execute1)
                .then(CommandManager.argument("ping", StringArgumentType.greedyString())
                        .executes(SBFCommand::execute2)));
    }

    private static int execute1 (CommandContext<ServerCommandSource> context) {
        MinecraftClient client = MinecraftClient.getInstance();
        client.setScreen(new SBFabricScreen());
        return 1;
    }

    private static int execute2 (CommandContext<ServerCommandSource> context) {
        PingUtils.displayPing();
        return 1;
    }
}