package lv.cebbys.mcmods.respro.imp;

import com.mojang.serialization.Lifecycle;
import lv.cebbys.mcmods.respro.Respro;
import lv.cebbys.mcmods.respro.imp.sides.client.packs.ResproAssetPack;
import lv.cebbys.mcmods.respro.imp.sides.common.packs.ResproDataPack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SimpleRegistry;

public class ResourceRegistry {
    @Environment(EnvType.CLIENT)
    public static final MutableRegistry<ResproAssetPack> CLIENT_RESOURCE_PACKS;

    public static final MutableRegistry<ResproDataPack> SERVER_RESOURCE_PACKS;

    @Environment(EnvType.CLIENT)
    public static void registerAssetPack(Identifier id, ResproAssetPack pack) {
//        ResproLogger.debug("Registering asset pack: " + id);
        Registry.register(CLIENT_RESOURCE_PACKS, id, pack);
    }

    public static void registerDataPack(Identifier id, ResproDataPack pack) {
//        ResproLogger.debug("Registering data pack: " + id);
        Registry.register(SERVER_RESOURCE_PACKS, id, pack);
    }

    static {
        CLIENT_RESOURCE_PACKS = new SimpleRegistry<>(
                RegistryKey.ofRegistry(new Identifier(Respro.MODID, "asset_packs")),
                Lifecycle.stable(),
                null);
        SERVER_RESOURCE_PACKS = new SimpleRegistry<>(
                RegistryKey.ofRegistry(new Identifier(Respro.MODID, "data_packs")),
                Lifecycle.stable(),
                null);
    }
}
