package lv.cebbys.mcmods.respro.component.pack;

import com.google.gson.JsonObject;
import lombok.Getter;
import lv.cebbys.mcmods.respro.component.resource.MetaResource;
import lv.cebbys.mcmods.respro.component.resource.Resource;
import lv.cebbys.mcmods.respro.imp.utility.ResourceUtils;
import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.ResourcePackSource;
import net.minecraft.resource.ResourceType;
import net.minecraft.resource.metadata.ResourceMetadataReader;
import net.minecraft.util.Identifier;
import org.apache.commons.io.input.NullInputStream;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import static lv.cebbys.mcmods.respro.constant.ResproConstants.PACK_ICON_PATH;
import static lv.cebbys.mcmods.respro.constant.ResproConstants.PACK_MCMETA_PATH;

@Getter
public abstract class ResproPack implements ResourcePack {
    private final Identifier id;
    private final String name;
    private final Map<Identifier, Resource> resources;
    private final Set<String> assetNamespaces;
    private final Set<String> dataNamespaces;
    private final ResourcePackSource source;
    private final boolean alwaysEnabled;

    protected ResproPack(
            @NotNull Identifier packId,
            @NotNull String packName,
            @NotNull Map<Identifier, Resource> packResources,
            @NotNull ResourcePackSource packSource,
            boolean packAlwaysEnabled
    ) {
        id = packId;
        name = packName;
        resources = new LinkedHashMap<>();
        assetNamespaces = new HashSet<>();
        dataNamespaces = new HashSet<>();
        packResources.forEach((Identifier resourceId, Resource resource) -> {
            if (ResourceUtils.belongsTo(resource, ResourceType.CLIENT_RESOURCES)) {
                assetNamespaces.add(resourceId.getNamespace());
            }
            if (ResourceUtils.belongsTo(resource, ResourceType.SERVER_DATA)) {
                dataNamespaces.add(resourceId.getNamespace());
            }
        });
        source = packSource;
        alwaysEnabled = packAlwaysEnabled;
    }


    @Nullable
    @Override
    public InputStream openRoot(String fileName) {
        if (PACK_MCMETA_PATH.equals(fileName)) {
            return ResourceUtils.getAsStream(resources.get(new Identifier(id.getNamespace(), PACK_MCMETA_PATH)));
        } else if (PACK_ICON_PATH.equals(fileName)) {
            return ResourceUtils.getAsStream(resources.get(new Identifier(id.getNamespace(), PACK_ICON_PATH)));
        }
        return new NullInputStream(0);
    }

    @Override
    public InputStream open(ResourceType type, Identifier id) throws IOException {
        if (!contains(type, id)) throw new FileNotFoundException("File not found: " + id.getPath());
        else {
            return ResourceUtils.getAsStream(resources.get(id));
        }
    }

    @Override
    public Collection<Identifier> findResources(ResourceType type, String namespace, String prefix, int maxDepth, Predicate<String> pathFilter) {
        Set<Identifier> keys = new HashSet<>(resources.keySet());
        keys.removeIf(id ->
                !(id.getPath().startsWith(prefix)
                        && pathFilter.test(id.getPath())
                        && ResourceUtils.belongsTo(resources.get(id), type)));
        return keys;
    }

    @Nullable
    @Override
    public <T> T parseMetadata(ResourceMetadataReader<T> reader) throws IOException {
        Identifier metaId = new Identifier(id.getNamespace(), PACK_METADATA_NAME);
        if (!resources.containsKey(metaId))
            throw new FileNotFoundException("Pack mcmeta was not found for pack: " + id);
        if (resources.get(metaId) instanceof MetaResource metaResource) {
            JsonObject meta = ResourceUtils.getAsJsonObject(metaResource);
            return meta.has(reader.getKey())
                    ? reader.fromJson(meta.getAsJsonObject(reader.getKey()))
                    : null;
        } else {
            throw new RuntimeException("Invalid metadata for pack: " + id);
        }
    }

    @Override
    public boolean contains(ResourceType type, Identifier id) {
        return resources.containsKey(id) && ResourceUtils.belongsTo(resources.get(id), type);
    }

    @Override
    public Set<String> getNamespaces(ResourceType type) {
        return switch (type) {
            case CLIENT_RESOURCES -> assetNamespaces;
            case SERVER_DATA -> dataNamespaces;
        };
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void close() {

    }
}
