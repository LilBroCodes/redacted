package org.lilbrocodes.redacted.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import org.lilbrocodes.redacted.client.screen.PhotosensitivityWarningScreen;
import org.lilbrocodes.redacted.config.Configs;
import org.lilbrocodes.redacted.registry.ModShaders;

public class RedactedClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModShaders.initialize();

        ClientTickEvents.START_WORLD_TICK.register(world -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client != null && !Configs.CLIENT.seenPhotosensitivityPopup) client.setScreen(new PhotosensitivityWarningScreen());
        });
    }
}
