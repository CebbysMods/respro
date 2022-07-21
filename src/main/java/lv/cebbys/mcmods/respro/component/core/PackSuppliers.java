package lv.cebbys.mcmods.respro.component.core;

import com.mojang.serialization.Lifecycle;
import lv.cebbys.mcmods.respro.Respro;
import lv.cebbys.mcmods.respro.api.pack.Assets;
import lv.cebbys.mcmods.respro.api.pack.Data;
import lv.cebbys.mcmods.respro.api.supplier.AssetSupplier;
import lv.cebbys.mcmods.respro.api.supplier.DataSupplier;
import lv.cebbys.mcmods.respro.api.supplier.PackListSupplier;
import lv.cebbys.mcmods.respro.api.supplier.PackSupplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static lv.cebbys.mcmods.respro.constant.ResproConstants.RESPRO;

public class PackSuppliers {
    @Environment(EnvType.CLIENT)
    public static final Registry<AssetSupplier> ASSET_PROFILE_SUPPLIERS;
    public static final Registry<DataSupplier> DATA_PROFILE_SUPPLIERS;
    @Environment(EnvType.CLIENT)
    public static final PackListSupplier<?> RESPRO_ASSET_SUPPLIER;
    public static final PackListSupplier<?> RESPRO_DATA_SUPPLIER;

    static {
        ASSET_PROFILE_SUPPLIERS = new MappedRegistry<>(
                ResourceKey.createRegistryKey(new ResourceLocation(Respro.MODID, "asset_profile_suppliers")),
                Lifecycle.stable(),
                null
        );
        DATA_PROFILE_SUPPLIERS = new MappedRegistry<>(
                ResourceKey.createRegistryKey(new ResourceLocation(Respro.MODID, "data_profile_suppliers")),
                Lifecycle.stable(),
                null
        );

        RESPRO_ASSET_SUPPLIER = new PackListSupplier<Assets>() {
            private final ResourceLocation id = new ResourceLocation(RESPRO, "respro_asset_supplier");

            @Override
            public @NotNull ResourceLocation getSupplierId() {
                return id;
            }

            @Override
            public @NotNull List<Assets> getPacks() {
                return PackSuppliers.ASSET_PROFILE_SUPPLIERS.stream()
                        .map(PackSupplier::getPack)
                        .toList();
            }
        };
        RESPRO_DATA_SUPPLIER = new PackListSupplier<Data>() {
            private final ResourceLocation id = new ResourceLocation(RESPRO, "respro_data_supplier");

            @Override
            public @NotNull ResourceLocation getSupplierId() {
                return id;
            }

            @Override
            public @NotNull List<Data> getPacks() {
                return PackSuppliers.DATA_PROFILE_SUPPLIERS.stream()
                        .map(PackSupplier::getPack)
                        .toList();
            }
        };
    }
}
