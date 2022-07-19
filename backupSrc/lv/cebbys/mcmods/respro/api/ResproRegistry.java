package lv.cebbys.mcmods.respro.api;

import lv.cebbys.mcmods.respro.api.builder.pack.AssetBuilder;
import lv.cebbys.mcmods.respro.api.builder.pack.DataBuilder;
import lv.cebbys.mcmods.respro.api.factory.profile.AssetProfileSupplier;
import lv.cebbys.mcmods.respro.api.factory.profile.DataProfileSupplier;
import lv.cebbys.mcmods.respro.component.builder.ResproAssetBuilder;
import lv.cebbys.mcmods.respro.component.builder.ResproDataBuilder;
import lv.cebbys.mcmods.respro.core.ProfileSuppliers;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.registry.Registry;

import java.util.function.Consumer;

public class ResproRegistry {
    @Environment(EnvType.CLIENT)
    public static void registerAssets(AssetProfileSupplier supplier) {
        Registry.register(ProfileSuppliers.ASSET_PROFILE_SUPPLIERS, supplier.getSupplierId(), supplier);
    }

    @Environment(EnvType.CLIENT)
    public static void registerAssets(Consumer<AssetBuilder> builderConsumer) {
        ResproAssetBuilder builder = new ResproAssetBuilder();
        builderConsumer.accept(builder);
        registerAssets(builder.getSupplier());
    }

    public static void registerData(DataProfileSupplier supplier) {
        Registry.register(ProfileSuppliers.DATA_PROFILE_SUPPLIERS, supplier.getSupplierId(), supplier);
    }

    public static void registerData(Consumer<DataBuilder> builderConsumer) {
        ResproDataBuilder builder = new ResproDataBuilder();
        builderConsumer.accept(builder);
        registerData(builder.getSupplier());
    }
}
