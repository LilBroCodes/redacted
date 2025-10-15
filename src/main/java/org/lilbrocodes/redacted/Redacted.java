package org.lilbrocodes.redacted;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.lilbrocodes.redacted.config.Configs;
import org.lilbrocodes.redacted.registry.ModSoundEvents;

public class Redacted implements ModInitializer {
    private static final String MOD_ID = "redacted";

    @Override
    public void onInitialize() {
        Configs.initialize();
        ModSoundEvents.initialize();
    }

    public static Identifier identify(String path) {
        return Identifier.of(MOD_ID, path);
    }
}
