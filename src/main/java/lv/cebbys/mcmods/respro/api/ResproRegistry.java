package lv.cebbys.mcmods.respro.api;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lv.cebbys.mcmods.respro.api.initializer.pack.AssetResourcesInitializer;
import lv.cebbys.mcmods.respro.api.initializer.pack.DataResourcesInitializer;
import lv.cebbys.mcmods.respro.api.supplier.AssetSupplier;
import lv.cebbys.mcmods.respro.api.supplier.DataSupplier;
import lv.cebbys.mcmods.respro.component.core.PackSuppliers;
import lv.cebbys.mcmods.respro.component.resource.pack.ResproAssetResources;
import lv.cebbys.mcmods.respro.component.resource.pack.ResproDataResources;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.Registry;

import java.util.function.Consumer;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ResproRegistry {

    @Environment(EnvType.CLIENT)
    public static void registerAssets(AssetSupplier supplier) {
        Registry.register(PackSuppliers.ASSET_PROFILE_SUPPLIERS, supplier.getSupplierId(), supplier);
    }

    @Environment(EnvType.CLIENT)
    public static void registerAssets(Consumer<AssetResourcesInitializer> builderConsumer) {
        ResproAssetResources builder = new ResproAssetResources();
        builderConsumer.accept(builder);
        builder.validate();
        builder.dump();
        registerAssets(builder.getSupplier());
    }

    public static void registerData(DataSupplier supplier) {
        Registry.register(PackSuppliers.DATA_PROFILE_SUPPLIERS, supplier.getSupplierId(), supplier);
    }

    public static void registerData(Consumer<DataResourcesInitializer> builderConsumer) {
        ResproDataResources builder = new ResproDataResources();
        builderConsumer.accept(builder);
        builder.validate();
        builder.dump();
        registerData(builder.getSupplier());
    }
}
