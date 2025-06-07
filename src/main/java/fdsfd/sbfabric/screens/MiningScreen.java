package fdsfd.sbfabric.screens;

import fdsfd.sbfabric.config.ConfigManager;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MiningScreen extends Screen {

    public static final String MOD_ID = "sbfabric";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public Screen parent;

    public MiningScreen() {
        super(Text.literal("Mining"));
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

        ButtonWidget puzzlerSolverButton = ButtonWidget.builder(Text.of("Puzzler Solver: " + (ConfigManager.config.puzzlerSolverEnabled ? "ON" : "OFF")), (button) -> {
            ConfigManager.config.puzzlerSolverEnabled = !ConfigManager.config.puzzlerSolverEnabled;
            button.setMessage(Text.of("Puzzler Solver: " + (ConfigManager.config.puzzlerSolverEnabled ? "ON" : "OFF")));
            ConfigManager.save();

        }).dimensions(x, y, buttonWidth, buttonHeight).build();

        this.addDrawableChild(puzzlerSolverButton);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {

        context.fill(0, 0, this.width, this.height, 0x88000000);

        int textWidth = this.textRenderer.getWidth("Dwarven Mines");
        int x = (this.width - textWidth) / 2;
        int y = 40;

        context.drawText(this.textRenderer, "Dwarven Mines", x, y, 0xFFFFFF, false);

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
