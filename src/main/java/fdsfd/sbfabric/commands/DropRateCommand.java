package fdsfd.sbfabric.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class DropRateCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("droprate")
                .then(CommandManager.argument("dropChancePercent", DoubleArgumentType.doubleArg())
                        .then(CommandManager.argument("magicfind", DoubleArgumentType.doubleArg())
                                .then(CommandManager.argument("lootingLevel", IntegerArgumentType.integer())
                                        .executes(DropRateCommand::execute3)
                                        .then(CommandManager.argument("petluck", DoubleArgumentType.doubleArg())
                                                .executes(DropRateCommand::execute4)
                                        )
                                )
                        )
                )
        );
    }

    private static int execute3(CommandContext<ServerCommandSource> context) {
        double dropChancePercent = DoubleArgumentType.getDouble(context, "dropChancePercent");
        double magicFind = DoubleArgumentType.getDouble(context, "magicfind");
        int lootingLevel = IntegerArgumentType.getInteger(context, "lootingLevel");

        double dropChance = dropChancePercent / 100;
        double nonLootingDropRate = (dropChance * (1 + (magicFind / 100)));
        double lootingRate = 1 + (0.15 * lootingLevel);

        double finalDropRate = (nonLootingDropRate * lootingRate);
        double finalDropRatePercent = (finalDropRate * 100);
        context.getSource().sendFeedback(() -> Text.literal("§9§lSBFabric §8➤ §r§7Final Drop Chance: " + Math.round(finalDropRatePercent * 100)/100.0 + "% (1 in " + ((Math.round(1/finalDropRate * 100) / 100.0)) + ")") , false);
        return 1;
    }

    private static int execute4(CommandContext<ServerCommandSource> context) {
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
        context.getSource().sendFeedback(() -> Text.literal("§9§lSBFabric §8➤ §r§7Final Drop Chance: " + Math.round(finalDropRatePercent * 100)/100.0 + "% (1 in " + ((Math.round(1/finalDropRate * 100) / 100.0)) + ")\n" +
                "§9§lSBFabric §8➤ §r§7Final Pet Chance: " + Math.round(finalPetDropRatePercent * 100)/100.0 + "% (1 in " + ((Math.round(1/finalPetDropRate * 100) / 100.0)) + ")") , false);

        return 1;
    }
}
