package org.lilbrocodes.redacted.registry;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.lilbrocodes.redacted.Redacted;

public class ModSoundEvents {
    public static final Identifier STATIC_ID = Redacted.identify("static");
    public static final SoundEvent STATIC = SoundEvent.of(STATIC_ID);

    public static void initialize() {
        Registry.register(Registries.SOUND_EVENT, STATIC_ID, STATIC);
    }
}
