package fdsfd.sbfabric.mixins;

import fdsfd.sbfabric.config.ConfigManager;
import fdsfd.sbfabric.features.mining.abilitycooldown.AbilityCooldownMessage;
import fdsfd.sbfabric.features.dungeons.dungeonblessingdisplay.DungeonBlessingDisplay;
import fdsfd.sbfabric.features.mining.fueldisplay.FuelDisplay;
import fdsfd.sbfabric.features.mining.puzzler.PuzzlerSolver;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {

    @Unique

    @Inject(method = "onGameMessage", at = @At("HEAD"))
    private void onGameMessage(GameMessageS2CPacket packet, CallbackInfo ci) {
        Text message = packet.content();
        String messageString = message.getString();

        // Puzzler Solver Logger
        if (messageString.startsWith("§e[NPC] §dPuzzler§f: ") && ConfigManager.config.puzzlerSolverEnabled) {
            PuzzlerSolver.solvePuzzler(messageString);
        }

        // Dungeon Blessing Chat Logger
        if (messageString.startsWith("DUNGEON BUFF!") || messageString.startsWith("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬")) {
            DungeonBlessingDisplay.displayBlessings(messageString);
        }

        // Ability Cooldown Message Logger
        if (messageString.contains(" is now available!")) {
            String ability = messageString.substring(0, messageString.indexOf(" is now available!"));
            AbilityCooldownMessage.message(ability);
        }
    }

    // Scoreboard Updating Logger
    @Inject(method = "onScoreboardObjectiveUpdate", at = @At("TAIL"))
    private void onScoreboardChange(CallbackInfo ci) throws InterruptedException {
        DungeonBlessingDisplay.checkDungeonStatus();
        FuelDisplay.displayFuel();
    }

    // World Loader
    @Inject(method = "onGameJoin", at = @At("HEAD"))
    private void onWorldLoad(CallbackInfo ci) {
        DungeonBlessingDisplay.resetBlessings();
    }
}