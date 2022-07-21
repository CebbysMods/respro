package lv.cebbys.mcmods.respro.component.resource.pack;

import lv.cebbys.mcmods.respro.api.initializer.blockstate.multipart.MultipartBlockstateResourceInitializer;
import lv.cebbys.mcmods.respro.api.initializer.blockstate.variant.VariantBlockstateResourceInitializer;
import lv.cebbys.mcmods.respro.api.initializer.pack.AssetResourcesInitializer;
import lv.cebbys.mcmods.respro.api.pack.Assets;
import lv.cebbys.mcmods.respro.api.supplier.AssetSupplier;
import lv.cebbys.mcmods.respro.component.supplier.SimpleAssetSupplier;
import lv.cebbys.mcmods.respro.utility.ResourceLocationUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Environment(EnvType.CLIENT)
public class ResproAssetResources extends ResproPackResources<ResproAssetResources, AssetSupplier> implements AssetResourcesInitializer {

    @Override
    public AssetSupplier getSupplier() {
        return new SimpleAssetSupplier(this::getInstance, (resources) -> new Assets(
                resources.getId(), resources.getProfile(), () -> resources
        ));
    }

    @Override
    protected ResproAssetResources getInstance() {
        return this;
    }

    @Override
    public @NotNull AssetResourcesInitializer setVariantBlockstate(
            @NotNull ResourceLocation id,
            @NotNull Consumer<VariantBlockstateResourceInitializer> consumer
    ) {
        return setResource(
                VariantBlockstateResourceInitializer.class,
                ResourceLocationUtils.wrapped("blockstates/", id, ".json"),
                consumer
        );
    }

    @Override
    public @NotNull AssetResourcesInitializer setMultipartBlockstate(
            @NotNull ResourceLocation id,
            @NotNull Consumer<MultipartBlockstateResourceInitializer> consumer
    ) {
        return setResource(
                MultipartBlockstateResourceInitializer.class,
                ResourceLocationUtils.wrapped("blockstates/", id, ".json"),
                consumer
        );
    }
}
