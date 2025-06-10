package fdsfd.sbfabric.screens;

import fdsfd.sbfabric.config.ConfigManager;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class DungeonsScreen extends Screen {

    public Screen parent;

    public DungeonsScreen() {
        super(Text.literal("Dungeons"));
        this.parent = new SBFabricScreen();
    }

    @Override
    public void close() {
        this.client.setScreen(this.parent);
    }

    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
        // Intentionally left empty
    }

    @Override
    public void init() {
        int buttonWidth = 300;
        int buttonHeight = 20;
        int x = (this.width - buttonWidth) / 2;
        int y = 70;

        ButtonWidget dungeonBlessingButton = ButtonWidget.builder(Text.of("Display Dungeon Blessings: " + (ConfigManager.config.displayDungeonBlessings ? "ON" : "OFF")), (button) -> {
            ConfigManager.config.displayDungeonBlessings = !ConfigManager.config.displayDungeonBlessings;
            button.setMessage(Text.of("Display Dungeon Blessings: " + (ConfigManager.config.displayDungeonBlessings ? "ON" : "OFF")));
            ConfigManager.save();

        }).dimensions(x, y, buttonWidth, buttonHeight).build();

        this.addDrawableChild(dungeonBlessingButton);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {

        context.fill(0, 0, this.width, this.height, 0x88000000);

        int textWidth = this.textRenderer.getWidth("Blessings");
        int x = (this.width - textWidth) / 2;
        int y = 40;

        context.drawText(this.textRenderer, "Blessings", x, y, 0xFFFFFF, false);

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
