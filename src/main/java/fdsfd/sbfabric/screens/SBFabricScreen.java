package fdsfd.sbfabric.screens;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class SBFabricScreen extends Screen {

    public SBFabricScreen() {
        super(Text.literal("SBFabric"));
    }

    @Override
    public void close() {
        super.close();
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
        int y = (this.height - 110) / 2;

        ButtonWidget editGuiButton = ButtonWidget.builder(Text.of("Move GUI Elements"), (button) -> {
            // do nothing right now
        }).dimensions(x, y, buttonWidth, buttonHeight).build();

        this.addDrawableChild(editGuiButton);

        y += buttonHeight + 10;

        ButtonWidget farmingButton = ButtonWidget.builder(Text.of("Farming"), (button) -> {
            this.client.setScreen(new FarmingScreen());
        }).dimensions(x, y, buttonWidth, buttonHeight).build();

        this.addDrawableChild(farmingButton);

        y += buttonHeight + 10;

        ButtonWidget miningButton = ButtonWidget.builder(Text.of("Mining"), (button) -> {
            this.client.setScreen(new MiningScreen());
        }).dimensions(x, y, buttonWidth, buttonHeight).build();

        this.addDrawableChild(miningButton);

        y += buttonHeight + 10;

        ButtonWidget dungeonsButton = ButtonWidget.builder(Text.of("Dungeons"), (button) -> {
            this.client.setScreen(new DungeonsScreen());
        }).dimensions(x, y, buttonWidth, buttonHeight).build();

        this.addDrawableChild(dungeonsButton);

        y += buttonHeight + 10;

        ButtonWidget miscButton = ButtonWidget.builder(Text.of("Misc"), (button) -> {
            this.client.setScreen(new MiscScreen());
        }).dimensions(x, y, buttonWidth, buttonHeight).build();

        this.addDrawableChild(miscButton);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {

        context.fill(0, 0, this.width, this.height, 0x88000000);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return true;
    }
}
