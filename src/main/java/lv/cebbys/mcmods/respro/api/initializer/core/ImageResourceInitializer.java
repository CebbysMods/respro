package lv.cebbys.mcmods.respro.api.initializer.core;

import org.jetbrains.annotations.NotNull;

import java.io.InputStream;

public interface ImageResourceInitializer {
    @NotNull ImageResourceInitializer setFromResources(@NotNull ClassLoader modClassLoader, @NotNull String pathInProjectResources);

    default @NotNull ImageResourceInitializer setFromResources(@NotNull Class<?> modClass, @NotNull String pathInProjectResources) {
        return setFromResources(modClass.getClassLoader(), pathInProjectResources);
    }

    @NotNull ImageResourceInitializer setFromStream(@NotNull InputStream stream);
}
