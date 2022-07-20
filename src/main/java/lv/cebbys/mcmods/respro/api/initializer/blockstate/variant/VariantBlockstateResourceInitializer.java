package lv.cebbys.mcmods.respro.api.initializer.blockstate.variant;

import lv.cebbys.mcmods.respro.api.initializer.blockstate.BlockVariantResourceInitializer;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public interface VariantBlockstateResourceInitializer {
    @NotNull VariantBlockstateResourceInitializer setVariant(
            @NotNull Consumer<VariantBlockPropertyResourceInitializer> propertyConsumer,
            @NotNull Consumer<BlockVariantResourceInitializer> variantConsumer
    );

    default @NotNull VariantBlockstateResourceInitializer setVariant(@NotNull Consumer<BlockVariantResourceInitializer> consumer) {
        return setVariant(VariantBlockPropertyResourceInitializer::setNormal, consumer);
    }
}
