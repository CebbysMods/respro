package lv.cebbys.mcmods.respro.api.initializer.pack;

import lv.cebbys.mcmods.respro.api.initializer.blockstate.VariantBlockstateResourceInitializer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public interface AssetResourcesInitializer extends PackResourcesInitializer<AssetResourcesInitializer> {
    @NotNull AssetResourcesInitializer setVariantBlockState(@NotNull ResourceLocation id, @NotNull Consumer<VariantBlockstateResourceInitializer> consumer);
}
