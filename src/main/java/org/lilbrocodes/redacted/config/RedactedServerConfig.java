package org.lilbrocodes.redacted.config;

import me.fzzyhmstrs.fzzy_config.api.FileType;
import me.fzzyhmstrs.fzzy_config.api.SaveType;
import me.fzzyhmstrs.fzzy_config.config.Config;
import org.jetbrains.annotations.NotNull;
import org.lilbrocodes.redacted.Redacted;

public class RedactedServerConfig extends Config {
    public RedactedServerConfig() {
        super(Redacted.identify("server_config"));
    }

    @Override
    public int defaultPermLevel() {
        return 4;
    }

    @Override
    public @NotNull FileType fileType() {
        return FileType.JSONC;
    }

    @Override
    public @NotNull SaveType saveType() {
        return SaveType.SEPARATE;
    }

    @Prefix("Disables the whole mod, showing the vanilla border")
    public boolean enabled = true;
    @Prefix("Disables rendering of the effect, still not showing the vanilla border")
    public boolean render = true;
    @Prefix("Kills the player after a small delay of getting in within kill distance of the world border")
    public boolean kill = false;

    @Prefix("How close the player has to get to the world border for the effect to start appearing")
    public double renderDistance = 10;

    @Prefix("How close the player has to get to the world border for the effect to reach full opacity, and kill the player if that is enabled")
    public double killDistance = 1;

    @Override
    public @NotNull String translationKey() {
        return "redacted.server_config";
    }

    @Override
    public boolean hasTranslation() {
        return true;
    }
}
