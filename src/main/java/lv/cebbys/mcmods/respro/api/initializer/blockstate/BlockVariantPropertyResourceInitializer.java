package lv.cebbys.mcmods.respro.api.initializer.blockstate;

import net.minecraft.world.level.block.state.properties.Property;
import org.jetbrains.annotations.NotNull;

public interface BlockVariantPropertyResourceInitializer {
    @NotNull BlockVariantPropertyResourceInitializer setNormal();

    @NotNull BlockVariantPropertyResourceInitializer setProperty(@NotNull String id, @NotNull String value);

    default @NotNull BlockVariantPropertyResourceInitializer setProperty(@NotNull String id, int value) {
        return setProperty(id, String.valueOf(value));
    }

    default @NotNull BlockVariantPropertyResourceInitializer setProperty(@NotNull String id, boolean value) {
        return setProperty(id, String.valueOf(value));
    }

    default @NotNull <T extends Comparable<T>> BlockVariantPropertyResourceInitializer setProperty(@NotNull Property<T> property, @NotNull T value) {
        return setProperty(property.getName(), String.valueOf(value));
    }
}
