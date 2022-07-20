package lv.cebbys.mcmods.respro.api.initializer.blockstate.variant;

import net.minecraft.world.level.block.state.properties.Property;
import org.jetbrains.annotations.NotNull;

public interface VariantBlockPropertyResourceInitializer {
    @NotNull VariantBlockPropertyResourceInitializer setNormal();

    @NotNull VariantBlockPropertyResourceInitializer setProperty(@NotNull String id, @NotNull String value);

    default @NotNull VariantBlockPropertyResourceInitializer setProperty(@NotNull String id, int value) {
        return setProperty(id, String.valueOf(value));
    }

    default @NotNull VariantBlockPropertyResourceInitializer setProperty(@NotNull String id, boolean value) {
        return setProperty(id, String.valueOf(value));
    }

    default @NotNull <T extends Comparable<T>> VariantBlockPropertyResourceInitializer setProperty(@NotNull Property<T> property, @NotNull T value) {
        return setProperty(property.getName(), String.valueOf(value));
    }
}
