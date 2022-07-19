package lv.cebbys.mcmods.respro.core;

import com.mojang.serialization.Lifecycle;
import lv.cebbys.mcmods.respro.Respro;
import lv.cebbys.mcmods.respro.api.factory.profile.AssetProfileSupplier;
import lv.cebbys.mcmods.respro.api.factory.profile.DataProfileSupplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SimpleRegistry;

public class ProfileSuppliers {
    @Environment(EnvType.CLIENT)
    public static final MutableRegistry<AssetProfileSupplier> ASSET_PROFILE_SUPPLIERS;
    public static final MutableRegistry<DataProfileSupplier> DATA_PROFILE_SUPPLIERS;

    static {
        ASSET_PROFILE_SUPPLIERS = new SimpleRegistry<>(
                RegistryKey.ofRegistry(new Identifier(Respro.MODID, "asset_profile_suppliers")),
                Lifecycle.stable(),
                null
        );
        DATA_PROFILE_SUPPLIERS = new SimpleRegistry<>(
                RegistryKey.ofRegistry(new Identifier(Respro.MODID, "data_profile_suppliers")),
                Lifecycle.stable(),
                null
        );
    }
}
