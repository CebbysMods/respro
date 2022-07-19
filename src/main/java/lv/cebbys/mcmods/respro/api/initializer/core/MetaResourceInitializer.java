package lv.cebbys.mcmods.respro.api.initializer.core;

import org.jetbrains.annotations.NotNull;

public interface MetaResourceInitializer {
    @NotNull MetaResourceInitializer setDescription(@NotNull String description);

    @NotNull MetaResourceInitializer setFormat(int version);
}
