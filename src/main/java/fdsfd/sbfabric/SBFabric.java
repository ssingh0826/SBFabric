package fdsfd.sbfabric;

import com.mojang.brigadier.tree.LiteralCommandNode;
import fdsfd.sbfabric.utils.PingUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static net.minecraft.server.command.CommandManager.*;

public class SBFabric implements ModInitializer {
	public static final String MOD_ID = "sbfabric";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		LOGGER.info("SBFabric is initializing!");

		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			final LiteralCommandNode<ServerCommandSource> sbfNode = dispatcher.register(literal("sbfabric")
					.executes(context -> {
						context.getSource().sendFeedback(() -> Text.literal("Mod Menu coming soontm"), true);

						return 1;
						})
					.then(literal("ping")
							.executes(context -> {
								PingUtils.displayPing();
								return 1;
							})
						)
			);
			dispatcher.register(literal("sbf").redirect(sbfNode));
		});

		LOGGER.info("SBFabric is done initializing!");
	}
}