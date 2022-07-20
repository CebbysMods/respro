package lv.cebbys.mcmods.respro.component.resource.pack;

import com.google.gson.JsonObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lv.cebbys.mcmods.respro.Respro;
import lv.cebbys.mcmods.respro.api.builder.ResourceBuilder;
import lv.cebbys.mcmods.respro.api.ResproBuilders;
import lv.cebbys.mcmods.respro.api.initializer.core.PackProfileResourceInitializer;
import lv.cebbys.mcmods.respro.api.initializer.pack.PackResourcesInitializer;
import lv.cebbys.mcmods.respro.api.supplier.PackSupplier;
import lv.cebbys.mcmods.respro.component.core.ResproPackDump;
import lv.cebbys.mcmods.respro.component.resource.AbstractResource;
import lv.cebbys.mcmods.respro.component.resource.pack.profile.PackProfileResource;
import lv.cebbys.mcmods.respro.component.resource.core.MetaResource;
import lv.cebbys.mcmods.respro.exception.PackGenerationException;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.MetadataSectionSerializer;
import org.apache.commons.io.input.NullInputStream;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static lv.cebbys.mcmods.respro.constant.ResproConstants.PACK_ICON_PATH;
import static lv.cebbys.mcmods.respro.constant.ResproConstants.PACK_MCMETA_PATH;

@Getter
@Setter
public abstract class ResproPackResources<B extends PackResourcesInitializer<?>, S extends PackSupplier<?>> implements PackResources {
    private static final Logger LOGGER = LoggerFactory.getLogger(Respro.class);
    private static final ResproPackDump DUMP = new ResproPackDump();

    private final Map<ResourceLocation, AbstractResource> resources = new HashMap<>();
    private ResourceLocation id;
    private final Set<String> assetNamespaces = new HashSet<>();
    private final Set<String> dataNamespaces = new HashSet<>();
    @Setter(AccessLevel.NONE)
    private PackProfileResource profile = new PackProfileResource();
    private boolean enabledDumpMode;

    protected abstract B getInstance();

    public abstract S getSupplier();

    public @NotNull B setPackId(@NotNull ResourceLocation packId) {
        id = packId;
        return getInstance();
    }

    public @NotNull B setPackProfile(@NotNull Consumer<PackProfileResourceInitializer> consumer) {
        profile = new PackProfileResource();
        consumer.accept(profile);
        setResource(new ResourceLocation(id.getNamespace(), PACK_ICON_PATH), profile.getIcon());
        setResource(new ResourceLocation(id.getNamespace(), PACK_MCMETA_PATH), profile.getMeta());
        return getInstance();
    }

    public @NotNull B setDumpMode(boolean enabledDump) {
        enabledDumpMode = enabledDump;
        return getInstance();
    }

    public @NotNull B setResource(@NotNull ResourceLocation id, @NotNull AbstractResource resource) {
        if (resource.belongsTo(PackType.CLIENT_RESOURCES)) {
            assetNamespaces.add(id.getNamespace());
        }
        if (resource.belongsTo(PackType.SERVER_DATA)) {
            dataNamespaces.add(id.getNamespace());
        }
        resources.put(id, resource);
        return getInstance();
    }

    public @NotNull <I> B setResource(
            Class<I> initializerClass,
            ResourceLocation location,
            Consumer<I> consumer
    ) {
        ResourceBuilder<I, ?> builder = ResproBuilders.supplyBuilder(initializerClass);
        if (builder == null) {
            return getInstance();
        }
        builder.initialize(consumer);
        builder.validate();
        return setResource(location, builder.build());
    }

    @Nullable
    @Override
    public InputStream getRootResource(@NotNull String fileName) {
        if (PACK_MCMETA_PATH.equals(fileName)) {
            return resources.get(new ResourceLocation(id.getNamespace(), PACK_MCMETA_PATH)).getAsStream();
        } else if (PACK_ICON_PATH.equals(fileName)) {
            return resources.get(new ResourceLocation(id.getNamespace(), PACK_ICON_PATH)).getAsStream();
        }
        return new NullInputStream(0);
    }

    @Override
    public InputStream getResource(@NotNull PackType type, @NotNull ResourceLocation id) throws IOException {
        if (!hasResource(type, id)) throw new FileNotFoundException("File not found: " + id.getPath());
        else {
            return resources.get(id).getAsStream();
        }
    }

    @Override
    public Collection<ResourceLocation> getResources(@NotNull PackType type, @NotNull String namespace, @NotNull String prefix, int maxDepth, @NotNull Predicate<String> pathFilter) {
        Set<ResourceLocation> keys = new HashSet<>(resources.keySet());
        keys.removeIf(id ->
                !(id.getPath().startsWith(prefix) && pathFilter.test(id.getPath()) && resources.get(id).belongsTo(type))
        );
        return keys;
    }

    @Nullable
    @Override
    public <T> T getMetadataSection(@NotNull MetadataSectionSerializer<T> reader) throws IOException {
        ResourceLocation metaId = new ResourceLocation(id.getNamespace(), PACK_MCMETA_PATH);
        if (!resources.containsKey(metaId))
            throw new FileNotFoundException("Pack mcmeta was not found for pack: " + id);
        if (resources.get(metaId) instanceof MetaResource metaResource) {
            JsonObject meta = metaResource.getAsJsonObject();
            return meta.has(reader.getMetadataSectionName())
                    ? reader.fromJson(meta.getAsJsonObject(reader.getMetadataSectionName()))
                    : null;
        } else {
            throw new FileNotFoundException("Invalid metadata for pack: " + id);
        }
    }

    @Override
    public boolean hasResource(@NotNull PackType type, @NotNull ResourceLocation id) {
        return resources.containsKey(id) && resources.get(id).belongsTo(type);
    }

    @Override
    public Set<String> getNamespaces(@NotNull PackType type) {
        return switch (type) {
            case CLIENT_RESOURCES -> assetNamespaces;
            case SERVER_DATA -> dataNamespaces;
        };
    }

    @Override
    public String getName() {
        return profile.getName().getAsString();
    }

    @Override
    public void close() {

    }

    public void validate() {
        try {
            if (id == null) throw new PackGenerationException("Respro pack id is null");
            if (profile == null) throw new PackGenerationException("Respro pack profile is null");
            profile.validate();
        } catch (Exception e) {
            LOGGER.error("Failed to generate respro pack: {}", e.getMessage(), e);
            throw e;
        }
    }

    public void dump() {
        if (enabledDumpMode) {
            DUMP.dump(this);
        }
    }
}
