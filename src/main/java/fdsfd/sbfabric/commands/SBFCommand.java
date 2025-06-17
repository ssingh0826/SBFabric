package fdsfd.sbfabric.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fdsfd.sbfabric.screens.SBFabricScreen;
import fdsfd.sbfabric.utils.PingUtils;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class SBFCommand {

    private static boolean shouldOpenScreen = false;

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("sbf")
            .executes(SBFCommand::openScreen)
                .then(ClientCommandManager.literal("ping")
                    .executes(SBFCommand::ping))
                .then(ClientCommandManager.literal("help")
                    .executes(SBFCommand::commandList)));
    }

    public static int commandList(CommandContext<FabricClientCommandSource> context) {
        MinecraftClient client = MinecraftClient.getInstance();
        client.player.sendMessage(Text.literal("SBFabric").formatted(Formatting.BOLD).formatted(Formatting.BLUE)
            .append(Text.literal(" ➤ ").formatted(Formatting.BOLD).formatted(Formatting.GRAY))
            .append(Text.literal(" command list: /droprate, /sbf, /sbf help").formatted(Formatting.BOLD).formatted(Formatting.YELLOW))
        , false);
        
        return 1;
    }

    private static int openScreen (CommandContext<FabricClientCommandSource> context) {
        shouldOpenScreen = true;
        MinecraftClient client = MinecraftClient.getInstance();

        client.execute(() -> client.setScreen(new SBFabricScreen()));

        return 1;
    }

    private static int ping (CommandContext<FabricClientCommandSource> context) {
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