package org.lilbrocodes.redacted.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.border.WorldBorder;
import org.lilbrocodes.redacted.config.Configs;
import org.lilbrocodes.redacted.registry.ModDamageTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {
    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    @Shadow public abstract ServerWorld getServerWorld();

    @Inject(method = "tick", at = @At("TAIL"))
    public void redacted$kill(CallbackInfo ci) {
        if (!Configs.SERVER.kill || getServerWorld() == null) return;

        WorldBorder border = getServerWorld().getWorldBorder();
        double d = border.getDistanceInsideBorder(this);
        double K = Configs.SERVER.killDistance;

        if (d <= K) {
            damage(ModDamageTypes.border(getServerWorld()), 1);
        }
    }
}
