package fdsfd.sbfabric.features.mining.abilitycooldown;

import fdsfd.sbfabric.config.ConfigManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class AbilityCooldownMessage {
    public static void message(String ability) {
        MinecraftClient client = MinecraftClient.getInstance();
        InGameHud hud = client.inGameHud;

        if (ConfigManager.config.abilityCooldownMessageEnabled) {
            hud.setTitle(Text.literal(ability + " available!").formatted(Formatting.AQUA).formatted(Formatting.BOLD));
        }
    }
}