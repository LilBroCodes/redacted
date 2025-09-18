package org.lilbrocodes.redacted.config;

import me.fzzyhmstrs.fzzy_config.api.ConfigApiJava;
import me.fzzyhmstrs.fzzy_config.api.RegisterType;

public class Configs {
    public static RedactedServerConfig SERVER;
    public static RedactedClientConfig CLIENT;

    public static void initialize() {
        SERVER = ConfigApiJava.registerAndLoadConfig(RedactedServerConfig::new, RegisterType.BOTH);
        CLIENT = ConfigApiJava.registerAndLoadConfig(RedactedClientConfig::new, RegisterType.CLIENT);
    }
}
