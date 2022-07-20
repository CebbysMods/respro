package lv.cebbys.mcmods.respro.api.initializer.pack;

import lv.cebbys.mcmods.respro.api.initializer.blockstate.multipart.MultipartBlockstateResourceInitializer;
import lv.cebbys.mcmods.respro.api.initializer.blockstate.variant.VariantBlockstateResourceInitializer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

@SuppressWarnings("all")
public interface AssetResourcesInitializer extends PackResourcesInitializer<AssetResourcesInitializer> {
    @NotNull AssetResourcesInitializer setVariantBlockstate(
            @NotNull ResourceLocation id,
            @NotNull Consumer<VariantBlockstateResourceInitializer> consumer
    );

    @NotNull AssetResourcesInitializer setMultipartBlockstate(
            @NotNull ResourceLocation id,
            @NotNull Consumer<MultipartBlockstateResourceInitializer> consumer
    );
}
