package fdsfd.sbfabric.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class PingUtils {

    public static int displayPing() {
        MinecraftClient client = MinecraftClient.getInstance();
        int ping = getPing();
        client.player.sendMessage(
            Text.literal("SBFabric").formatted(Formatting.BOLD).formatted(Formatting.BLUE)
                .append(Text.literal(" ➤ ").formatted(Formatting.BOLD).formatted(Formatting.GRAY))
                .append(Text.literal("Ping: ").formatted(Formatting.BOLD).formatted(Formatting.YELLOW))
                .append(Text.literal(String.valueOf(ping)).formatted(Formatting.BOLD).formatted(Formatting.GREEN))
                .append(Text.literal(" ms").formatted(Formatting.BOLD).formatted(Formatting.YELLOW))
            , false);

        return 1;
    }

    public static int getPing() {
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayNetworkHandler networkHandler = client.getNetworkHandler();
        if (networkHandler != null) {
            PlayerListEntry playerListEntry = networkHandler.getPlayerListEntry(client.player.getUuid());
            if (playerListEntry != null) {
                return playerListEntry.getLatency();
            }
        }
        return -1; // Return -1 if ping cannot be determined
    }
}
