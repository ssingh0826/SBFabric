package fdsfd.sbfabric.features.puzzler;

import net.minecraft.client.MinecraftClient;

public class DungeonBlessingDisplay {
    public static int blessingOfPower = 0, blessingOfWisdom = 0, blessingOfTime = 0;
    public static void displayBlessings(String chatMessage) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (!client.isOnThread()) return;

        if (chatMessage.startsWith("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬")) {
            blessingOfPower = 0; blessingOfWisdom = 0; blessingOfTime = 0;
        }

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

    public static String string() {
        return String.format("Blessings: " + " Power " + blessingOfPower + " Wisdom " + blessingOfWisdom + " Time " + blessingOfTime);
    }
}
