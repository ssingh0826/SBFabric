package fdsfd.sbfabric.mixins;

import fdsfd.sbfabric.config.Config;
import fdsfd.sbfabric.features.puzzler.PuzzlerSolver;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    @Inject(method = "onGameMessage", at = @At("HEAD"))
    private void onGameMessage(GameMessageS2CPacket packet, CallbackInfo ci) {
        Text message = packet.content();
        String messageString = message.getString();

        // Puzzler Solver
        if (messageString.startsWith("§e[NPC] §dPuzzler§f: ") && Config.puzzlerSolverEnabled) {
            PuzzlerSolver.solvePuzzler(messageString);
        }
    }
}
