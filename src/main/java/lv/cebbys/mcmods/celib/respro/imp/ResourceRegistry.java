package lv.cebbys.mcmods.celib.respro.imp;

import com.mojang.serialization.Lifecycle;
import lv.cebbys.mcmods.celib.respro.Respro;
import lv.cebbys.mcmods.celib.respro.imp.loggers.ResproLogger;
import lv.cebbys.mcmods.celib.respro.imp.packs.ResproAssetPack;
import lv.cebbys.mcmods.celib.respro.imp.packs.ResproDataPack;
import lv.cebbys.mcmods.celib.respro.imp.packs.ResproResourcePack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SimpleRegistry;

public class ResourceRegistry {
    public static final MutableRegistry<ResproResourcePack> CLIENT_RESOURCE_PACKS;
    public static final MutableRegistry<ResproResourcePack> SERVER_RESOURCE_PACKS;

    public static void registerAssetPack(Identifier id, ResproAssetPack pack) {
        ResproLogger.debug("Registering asset pack: " + id);
        Registry.register(CLIENT_RESOURCE_PACKS, id, pack);
    }

    public static void registerDataPack(Identifier id, ResproDataPack pack) {
        ResproLogger.debug("Registering data pack: " + id);
        Registry.register(SERVER_RESOURCE_PACKS, id, pack);
    }

    static {
        CLIENT_RESOURCE_PACKS = new SimpleRegistry<>(
                RegistryKey.ofRegistry(new Identifier(Respro.MODID, "asset_packs")),
                Lifecycle.stable());
        SERVER_RESOURCE_PACKS = new SimpleRegistry<>(
                RegistryKey.ofRegistry(new Identifier(Respro.MODID, "data_packs")),
                Lifecycle.stable());
    }
}
