package fdsfd.sbfabric.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class DropRateCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("droprate")
                .then(ClientCommandManager.argument("dropChancePercent", DoubleArgumentType.doubleArg())
                        .then(ClientCommandManager.argument("magicfind", DoubleArgumentType.doubleArg())
                                .then(ClientCommandManager.argument("lootingLevel", IntegerArgumentType.integer())
                                        .executes(DropRateCommand::itemDropChance)
                                        .then(ClientCommandManager.argument("petluck", DoubleArgumentType.doubleArg())
                                                .executes(DropRateCommand::petDropChance)
                                        )
                                )
                        )
                )
        );
    }

    private static int itemDropChance(CommandContext<FabricClientCommandSource> context) {
        double dropChancePercent = DoubleArgumentType.getDouble(context, "dropChancePercent");
        double magicFind = DoubleArgumentType.getDouble(context, "magicfind");
        int lootingLevel = IntegerArgumentType.getInteger(context, "lootingLevel");

        double dropChance = dropChancePercent / 100;
        double nonLootingDropRate = (dropChance * (1 + (magicFind / 100)));
        double lootingRate = 1 + (0.15 * lootingLevel);

        double finalDropRate = (nonLootingDropRate * lootingRate);
        double finalDropRatePercent = (finalDropRate * 100);
        MinecraftClient client = MinecraftClient.getInstance();

        client.player.sendMessage(Text.literal("§9§lSBFabric §8➤ §r§7Final Drop Chance: " + Math.round(finalDropRatePercent * 100)/100.0 + "% (1 in " + ((Math.round(1/finalDropRate * 100) / 100.0)) + ")") , false);
        return 1;
    }

    private static int petDropChance(CommandContext<FabricClientCommandSource> context) {
        double dropChancePercent = DoubleArgumentType.getDouble(context, "dropChancePercent");
        double magicFind = DoubleArgumentType.getDouble(context, "magicfind");
        int lootingLevel = IntegerArgumentType.getInteger(context, "lootingLevel");
        double petLuck = DoubleArgumentType.getDouble(context, "petluck");

        double dropChance = dropChancePercent / 100;
        double nonLootingDropRate = (dropChance * (1 + (magicFind / 100)));
        double petLuckDropRate = (dropChance * (1 + ((magicFind + petLuck) / 100)));
        double lootingRate = 1 + (0.15 * lootingLevel);

        double finalDropRate = (nonLootingDropRate * lootingRate);
        double finalPetDropRate = (petLuckDropRate * lootingRate);
        double finalDropRatePercent = (finalDropRate * 100);
        double finalPetDropRatePercent = (finalPetDropRate * 100);
        MinecraftClient client = MinecraftClient.getInstance();

        client.player.sendMessage(Text.literal("§9§lSBFabric §8➤ §r§7Final Drop Chance: " + Math.round(finalDropRatePercent * 100)/100.0 + "% (1 in " + ((Math.round(1/finalDropRate * 100) / 100.0)) + ")\n" +
                "§9§lSBFabric §8➤ §r§7Final Pet Chance: " + Math.round(finalPetDropRatePercent * 100)/100.0 + "% (1 in " + ((Math.round(1/finalPetDropRate * 100) / 100.0)) + ")"), false);

        return 1;
    }
}
