package lv.cebbys.mcmods.celib.respro.api;

import com.mojang.serialization.Lifecycle;
import lv.cebbys.mcmods.celib.respro.Respro;
import lv.cebbys.mcmods.celib.respro.imp.ResourceRegistry;
import lv.cebbys.mcmods.celib.respro.imp.builders.AssetPackBuilder;
import lv.cebbys.mcmods.celib.respro.imp.builders.AssetPackExtractor;
import lv.cebbys.mcmods.celib.respro.imp.builders.DataPackBuilder;
import lv.cebbys.mcmods.celib.respro.imp.builders.DataPackExtractor;
import lv.cebbys.mcmods.celib.respro.imp.loggers.ResproLogger;
import net.minecraft.resource.ResourcePackProvider;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SimpleRegistry;

import java.util.function.Consumer;

@SuppressWarnings("all")
public class ResproRegistry {
    public static final MutableRegistry<ResourcePackProvider> CLIENT_RESOURCE_PROVIDERS;
    public static final MutableRegistry<ResourcePackProvider> SERVER_RESOURCE_PROVIDERS;

    public static void registerAssetProvider(Identifier id, AssetPackProvider provider) {
        ResproLogger.debug("Registering asset pack provider: " + id);
        Registry.register(ResproRegistry.CLIENT_RESOURCE_PROVIDERS, id, provider);
    }

    public static void registerDataProvider(Identifier id, DataPackProvider provider) {
        ResproLogger.debug("Registering data pack provider: " + id);
        Registry.register(ResproRegistry.SERVER_RESOURCE_PROVIDERS, id, provider);
    }

    public static void registerAssetPack(Identifier id, Consumer<AssetPackBuilder> consumer) {
        ResproLogger.debug("Registering asset pack: " + id);
        AssetPackBuilder builder = AssetPackBuilder.builder(id);
        consumer.accept(builder);
        ResourceRegistry.registerAssetPack(id, AssetPackExtractor.from(builder));
    }

    public static void registerDataPack(Identifier id, Consumer<DataPackBuilder> consumer) {
        ResproLogger.debug("Registering data pack: " + id);
        DataPackBuilder builder = DataPackBuilder.builder(id);
        consumer.accept(builder);
        ResourceRegistry.registerDataPack(id, DataPackExtractor.from(builder));
    }

    static {
        CLIENT_RESOURCE_PROVIDERS = new SimpleRegistry<>(
                RegistryKey.ofRegistry(new Identifier(Respro.MODID, "client_resource_providers")),
                Lifecycle.stable()
        );
        SERVER_RESOURCE_PROVIDERS = new SimpleRegistry<>(
                RegistryKey.ofRegistry(new Identifier(Respro.MODID, "server_resource_providers")),
                Lifecycle.stable()
        );
//        SERVER_RESOURCE_PROVIDERS = Registry.register((Registry) Registry.REGISTRIES,
//                new Identifier(Respro.MODID, "common_resource_providers"),
//                new SimpleRegistry<>(
//                        RegistryKey.ofRegistry(new Identifier(Respro.MODID, "server_resource_providers")),
//                        Lifecycle.stable()
//                )
//        );
    }
}
