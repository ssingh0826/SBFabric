package fdsfd.sbfabric.features.puzzler;

import net.minecraft.util.math.BlockPos;

public class PuzzlerSolver {

    public static BlockPos block = null;
    public static boolean puzzlerSolved = false;

    public static void solvePuzzler(String message) {
        System.out.println("solvePuzzler() called with message: " + message);
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

        block = new BlockPos(x, y, z);
    }
}
