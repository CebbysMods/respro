package lv.cebbys.mcmods.respro.api.initializer.core;

import org.jetbrains.annotations.NotNull;

public interface ImageResourceInitializer {
    @NotNull ImageResourceInitializer setFromResources(@NotNull String pathInProjectResources);
}
