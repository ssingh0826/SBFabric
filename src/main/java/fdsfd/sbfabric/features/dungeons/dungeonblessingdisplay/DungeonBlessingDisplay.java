package fdsfd.sbfabric.features.dungeons.dungeonblessingdisplay;

import net.minecraft.client.MinecraftClient;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;

public class DungeonBlessingDisplay {
    public static int blessingOfPower = 0, blessingOfWisdom = 0, blessingOfTime = 0;
    public static boolean inDungeons = false;

    public static void displayBlessings(String chatMessage) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (!client.isOnThread()) return;

        if (chatMessage.toLowerCase().startsWith("dungeon buff!")) {
            String[] messageSplit = chatMessage.split(" ");
            String blessingStrength = messageSplit[8].replace("!", "");
            String blessingType = messageSplit[7];
            switch(blessingStrength) {
                case "I":
                    updateBlessings(blessingType, 1);
                    break;
                case "II":
                    updateBlessings(blessingType, 2);
                    break;
                case "III":
                    updateBlessings(blessingType, 3);
                    break;
                case "IV":
                    updateBlessings(blessingType, 4);
                    break;
                case "V":
                    updateBlessings(blessingType, 5);
                    break;
            }
            System.out.println("Blessings: \nPower " + blessingOfPower + "\nWisdom " + blessingOfWisdom + "\nTime " + blessingOfTime);
        }
    }

    private static void updateBlessings(String type, int value) {
        switch (type) {
            case "Power":
                blessingOfPower += value;
                break;
            case "Wisdom":
                blessingOfWisdom += value;
                break;
            case "Time":
                blessingOfTime += value;
                break;
        }
    }

    public static void resetBlessings() {
        blessingOfPower = 0; blessingOfWisdom = 0; blessingOfTime = 0;
    }

    public static void checkDungeonStatus() {
        Scoreboard scoreboard = MinecraftClient.getInstance().player.getScoreboard();
        List<Team> teams = new ArrayList<>(scoreboard.getTeams());
        if (teams.size() > 12) {
            for (Team team : teams) {
                if ((team.getPrefix().getString() + "" + team.getSuffix().getString()).contains("Catacombs")) {
                    inDungeons = true;
                    break;
                } else {
                    inDungeons = false;
                }
            }
        }
    }

    public static String string() {
        return String.format("Blessings: " + " Power " + blessingOfPower + " Wisdom " + blessingOfWisdom + " Time " + blessingOfTime);
    }
}
