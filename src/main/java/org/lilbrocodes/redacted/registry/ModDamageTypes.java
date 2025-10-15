package org.lilbrocodes.redacted.registry;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import org.lilbrocodes.redacted.Redacted;

public class ModDamageTypes {
    public static final RegistryKey<DamageType> BORDER =
            RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Redacted.identify("border"));

    public static DamageSource border(ServerWorld world) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(ModDamageTypes.BORDER));
    }
}
