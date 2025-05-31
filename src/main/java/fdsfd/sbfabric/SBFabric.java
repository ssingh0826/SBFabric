package fdsfd.sbfabric;

import com.mojang.brigadier.tree.LiteralCommandNode;
import fdsfd.sbfabric.config.ConfigManager;
import fdsfd.sbfabric.screens.SBFabricScreen;
import fdsfd.sbfabric.utils.PingUtils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class SBFabric implements ClientModInitializer {
	public static final String MOD_ID = "sbfabric";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitializeClient() {

		LOGGER.info("SBFabric is initializing!");

		LOGGER.info("Loading config!");
		ConfigManager.load();
		LOGGER.info("Config finished loading!");

		LOGGER.info("Registering commands!");
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			final LiteralCommandNode<FabricClientCommandSource> sbfNode = dispatcher.register(literal("sbf")
					.executes(context -> {
						MinecraftClient.getInstance().execute(() -> {
							MinecraftClient client = MinecraftClient.getInstance();
							client.player.sendMessage(Text.literal("For whatever reason, /sbf currently does not open the screen. Until it is fixed, open the config screen from ModMenu.").formatted(Formatting.RED), false);
							client.setScreen(new SBFabricScreen());
						});
						return 1;
					})
					.then(literal("ping")
						.executes(context -> {
							PingUtils.displayPing();
							return 1;
						})
					)
			);
		});
		LOGGER.info("Commands finished registering!");

		LOGGER.info("SBFabric is done initializing!");
	}
}
