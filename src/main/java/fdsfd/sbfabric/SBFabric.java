package fdsfd.sbfabric;

import fdsfd.sbfabric.commands.DropRateCommand;
import fdsfd.sbfabric.commands.SBFCommand;
import fdsfd.sbfabric.config.ConfigManager;
import fdsfd.sbfabric.features.puzzler.DungeonBlessingDisplay;
import fdsfd.sbfabric.features.puzzler.PuzzlerSolver;
import fdsfd.sbfabric.utils.RenderUtils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SBFabric implements ClientModInitializer {
	public static final String MOD_ID = "sbfabric";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static BlockPos highlightPos = null;

	@Override
	public void onInitializeClient() {
		LOGGER.info("SBFabric is initializing!");

		LOGGER.info("Loading config!");
		ConfigManager.load();
		LOGGER.info("Config finished loading!");

		LOGGER.info("Registering commands!");

		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			SBFCommand.register(dispatcher);
		});

		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			DropRateCommand.register(dispatcher);
		});

		LOGGER.info("Commands finished registering!");

		LOGGER.info("Registering event listeners!");

		WorldRenderEvents.AFTER_ENTITIES.register(context -> {
			if (PuzzlerSolver.block == null || PuzzlerSolver.puzzlerSolved) return;
			if (ConfigManager.config == null || !ConfigManager.config.puzzlerSolverEnabled) return;

			Vec3d cameraPos = context.camera().getPos();
			MatrixStack matrices = context.matrixStack();
			VertexConsumerProvider.Immediate buffer = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();

			Box box = new Box(PuzzlerSolver.block);

			matrices.push();
			RenderUtils.drawBoxOutline(matrices, buffer, box, cameraPos, 1.0f, 0f, 0f, 1.0f);
			matrices.pop();

			buffer.draw();
		});

		HudRenderCallback.EVENT.register((context, tickCounter)  -> {
			if (ConfigManager.config == null || !ConfigManager.config.displayDungeonBlessings) return;
			if (!DungeonBlessingDisplay.inDungeons) return;
			TextRenderer renderer = MinecraftClient.getInstance().textRenderer;
			Matrix4f matrices = context.getMatrices().peek().translate(0.0F, 0.0F, 0.0F);
			VertexConsumerProvider.Immediate buffer = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();

			renderer.draw(Text.literal(DungeonBlessingDisplay.string()), 0, 0, 0x57B9FF, true, matrices, buffer, TextRenderer.TextLayerType.NORMAL, 1, 1, false);
		});

		ClientTickEvents.END_CLIENT_TICK.register(SBFCommand::tick);

		LOGGER.info("SBFabric is done initializing!");
	}
}