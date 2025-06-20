package fdsfd.sbfabric.features.mining.fueldisplay;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class FuelDisplay {
    public static String displayFuel() {
        MinecraftClient client = MinecraftClient.getInstance();
        assert client.player != null;
        ItemStack item = client.player.getMainHandStack();
        List<Text> itemLore = item.getTooltip(Item.TooltipContext.DEFAULT, client.player, TooltipType.ADVANCED);
        for (Text line : itemLore) {
            if (line.getString().contains("Fuel: ")) {
                return line.getString();
            }
        }
        return "";
    }
}
