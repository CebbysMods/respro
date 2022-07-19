package lv.cebbys.mcmods.respro.component.core;

import com.mojang.serialization.Lifecycle;
import lv.cebbys.mcmods.respro.Respro;
import lv.cebbys.mcmods.respro.api.supplier.AssetSupplier;
import lv.cebbys.mcmods.respro.api.supplier.DataSupplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public class PackSuppliers {
    @Environment(EnvType.CLIENT)
    public static final Registry<AssetSupplier> ASSET_PROFILE_SUPPLIERS;
    public static final Registry<DataSupplier> DATA_PROFILE_SUPPLIERS;

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
    }
}
