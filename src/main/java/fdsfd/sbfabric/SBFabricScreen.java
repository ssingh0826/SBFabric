package fdsfd.sbfabric;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SBFabricScreen extends Screen {

    public static final String MOD_ID = "sbfabric";

    public static final Logger LOGGER = LoggerFactory.getLogger(SBFabric.MOD_ID);

    public SBFabricScreen() {
        super(Text.literal("SBFabric"));
        LOGGER.info("SBFabricScreen constructor finished");
    }

    @Override
    protected void init() {
        LOGGER.info("SBFabricScreen init method called");
        ButtonWidget buttonWidget = ButtonWidget.builder(Text.of("Hello World"), (btn) -> {
            LOGGER.info("Button clicked");
            this.client.getToastManager().add(
                    SystemToast.create(this.client, SystemToast.Type.NARRATOR_TOGGLE, Text.of("Hello World!"), Text.of("This is a toast."))
            );
        }).dimensions(40, 40, 120, 20).build();

        this.addDrawableChild(buttonWidget);
        LOGGER.info("ButtonWidget added to screen");
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);


        context.drawText(this.textRenderer, "Special Button", 40, 40 - this.textRenderer.fontHeight - 10, 0xFFFFFFFF, true);
    }
}
