package lv.cebbys.mcmods.respro.api;

import com.mojang.serialization.Lifecycle;
import lv.cebbys.mcmods.respro.Respro;
import lv.cebbys.mcmods.respro.imp.ResourceRegistry;
import lv.cebbys.mcmods.respro.imp.builders.assetpack.AssetPackBuilder;
import lv.cebbys.mcmods.respro.imp.builders.assetpack.AssetPackExtractor;
import lv.cebbys.mcmods.respro.imp.builders.datapack.DataPackBuilder;
import lv.cebbys.mcmods.respro.imp.builders.datapack.DataPackExtractor;
import lv.cebbys.mcmods.respro.imp.loggers.ResproLogger;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resource.ResourcePackProvider;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SimpleRegistry;

import java.util.function.Consumer;

public class ResproRegistry {
    @Environment(EnvType.CLIENT)
    public static final MutableRegistry<ResourcePackProvider> CLIENT_RESOURCE_PROVIDERS = new SimpleRegistry<>(
            RegistryKey.ofRegistry(new Identifier(Respro.MODID, "client_resource_providers")),
            Lifecycle.stable(),
            null
    );
    public static final MutableRegistry<ResourcePackProvider> SERVER_RESOURCE_PROVIDERS = new SimpleRegistry<>(
            RegistryKey.ofRegistry(new Identifier(Respro.MODID, "server_resource_providers")),
            Lifecycle.stable(),
            null
    );

    @Environment(EnvType.CLIENT)
    public static void registerAssetProvider(Identifier id, AssetPackProvider provider) {
        ResproLogger.debug("Registering asset pack provider: " + id);
        Registry.register(ResproRegistry.CLIENT_RESOURCE_PROVIDERS, id, provider);
    }

    public static void registerDataProvider(Identifier id, DataPackProvider provider) {
        ResproLogger.debug("Registering data pack provider: " + id);
        Registry.register(ResproRegistry.SERVER_RESOURCE_PROVIDERS, id, provider);
    }

    @Environment(EnvType.CLIENT)
    public static void registerAssetPack(Identifier id, Consumer<AssetPackBuilder> consumer) {
        ResproLogger.debug("Registering asset pack: " + id);
        AssetPackBuilder builder = new AssetPackBuilder(id);
        consumer.accept(builder);
        ResourceRegistry.registerAssetPack(id, AssetPackExtractor.from(builder));
    }

    public static void registerDataPack(Identifier id, Consumer<DataPackBuilder> consumer) {
        ResproLogger.debug("Registering data pack: " + id);
        DataPackBuilder builder = new DataPackBuilder(id);
        consumer.accept(builder);
        ResourceRegistry.registerDataPack(id, DataPackExtractor.from(builder));
    }
}
