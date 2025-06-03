package fdsfd.sbfabric.features.puzzler;

import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class PuzzlerSolver {
    public static void solvePuzzler(String message) {
        int left = 0, right = 0, up = 0, down = 0, x = 181, y = 195, z = 135;

        for (char c : message.toCharArray()) {
            switch (c) {
                case '◀':
                    left++;
                    break;
                case '▶':
                    right++;
                    break;
                case '▲':
                    up++;
                    break;
                case '▼':
                    down++;
                    break;
            }
        }

        x = x + left - right;
        z = z + up - down;

        BlockPos block = new BlockPos(x, y, z);

        WorldRenderEvents.AFTER_ENTITIES.register(context -> {

        });

    }
}
