package org.lilbrocodes.redacted.registry;

import ladysnake.satin.api.managed.ManagedShaderEffect;
import ladysnake.satin.api.managed.ShaderEffectManager;
import ladysnake.satin.api.managed.uniform.Uniform1f;
import ladysnake.satin.api.managed.uniform.Uniform2f;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.world.border.WorldBorder;
import org.joml.Vector2f;
import org.lilbrocodes.redacted.Redacted;
import org.lilbrocodes.redacted.config.Configs;
import org.lilbrocodes.redacted.sounds.StaticSoundInstance;

public class ModShaders {
    public static final ManagedShaderEffect STATIC = ShaderEffectManager.getInstance()
            .manage(Redacted.identify("shaders/post/static.json"));

    public static final Uniform2f textureSizeUniform = STATIC.findUniform2f("uTextureSize");
    public static final Uniform1f time = STATIC.findUniform1f("uTime");
    public static final Uniform1f value = STATIC.findUniform1f("uValue");
    public static final Uniform1f cloudy = STATIC.findUniform1f("uCloudy");

    private static StaticSoundInstance staticSound;

    public static void initialize() {

    }

    public static void renderStatic(MinecraftClient client, float tickDelta) {
        if (!Configs.SERVER.enabled || !Configs.SERVER.render
                || client.world == null || client.player == null
                || client.player.isDead() || client.isPaused()) {
            return;
        }

        WorldBorder border = client.world.getWorldBorder();
        double d = border.getDistanceInsideBorder(client.player);

        double C = Configs.SERVER.renderDistance;
        double K = Configs.SERVER.killDistance;

        float v = (float)((d - C) / (K - C));
        v = Math.max(0f, Math.min(1f, v)) * 2.5f;

        if (v > 0f) {
            textureSizeUniform.set(new Vector2f(
                    client.getWindow().getFramebufferWidth(),
                    client.getWindow().getFramebufferHeight()
            ));
            time.set(client.world.getTimeOfDay() * 61.1251f + tickDelta);
            value.set(v);
            cloudy.set(Configs.CLIENT.photosensitivityMode ? 1f : 0f);

            STATIC.render(tickDelta);

            if (staticSound == null || staticSound.isDone()) {
                staticSound = new StaticSoundInstance(client, ModSoundEvents.STATIC);
                client.getSoundManager().play(staticSound);
            }
        }
    }
}
