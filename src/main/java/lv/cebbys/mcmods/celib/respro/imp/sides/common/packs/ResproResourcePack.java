package lv.cebbys.mcmods.celib.respro.imp.sides.common.packs;

import com.google.gson.JsonObject;
import lv.cebbys.mcmods.celib.respro.imp.sides.common.resources.MetaResource;
import lv.cebbys.mcmods.celib.respro.imp.sides.common.resources.Resource;
import lv.cebbys.mcmods.celib.respro.imp.sides.common.resources.ResourceUtils;
import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.ResourcePackSource;
import net.minecraft.resource.ResourceType;
import net.minecraft.resource.metadata.ResourceMetadataReader;
import net.minecraft.util.Identifier;
import org.apache.commons.io.input.NullInputStream;
import org.jetbrains.annotations.Nullable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public abstract class ResproResourcePack implements ResourcePack {

    public static final String PACK_ICON_NAME = "pack.png";
    public static final ResourcePackSource RESOURCE_PACK_SOURCE = ResourcePackSource.nameAndSource("generated");

    protected final Map<Identifier, Resource> packResources;
    protected final Set<String> packClientNamespaces;
    protected final Set<String> packServerNamespaces;
    protected final Identifier packId;
    protected final String packName;

    protected ResproResourcePack(Identifier id, String name, Map<Identifier, Resource> res) {
        packResources = new HashMap<>(res);
        packClientNamespaces = new HashSet<>();
        packServerNamespaces = new HashSet<>();
        packResources.forEach((resourceId, resource) -> {
            if (ResourceUtils.belongsTo(resource, ResourceType.CLIENT_RESOURCES)) {
                packClientNamespaces.add(resourceId.getNamespace());
            }
            if (ResourceUtils.belongsTo(resource, ResourceType.SERVER_DATA)) {
                packClientNamespaces.add(resourceId.getNamespace());
            }
        });
        packId = id;
        packName = name != null ? name : packId.getNamespace() + " resources";
    }

    @Nullable
    @Override
    public InputStream openRoot(String fileName) throws IOException {
        if (PACK_METADATA_NAME.equals(fileName)) {
            return ResourceUtils.getStream(packResources.get(new Identifier(packId.getNamespace(), PACK_METADATA_NAME)));
        } else if (PACK_ICON_NAME.equals(fileName)) {
            return ResourceUtils.getStream(packResources.get(new Identifier(packId.getNamespace(), PACK_ICON_NAME)));
        } else {
            return new NullInputStream(0);
        }
    }

    @Override
    public InputStream open(ResourceType type, Identifier id) throws IOException {
        if (!contains(type, id)) throw new FileNotFoundException("File not found: " + id.getPath());
        else {
            return ResourceUtils.getStream(packResources.get(id));
        }
    }

    @Override
    public Collection<Identifier> findResources(ResourceType type, String namespace, String prefix, int maxDepth, Predicate<String> pathFilter) {
        Set<Identifier> keys = new HashSet<>(this.packResources.keySet());
        keys.removeIf(id ->
                !(id.getPath().startsWith(prefix)
                        && pathFilter.test(id.getPath())
                        && ResourceUtils.belongsTo(packResources.get(id), type)));
        return keys;
    }

    @Override
    public boolean contains(ResourceType type, Identifier id) {
        return packResources.containsKey(id) && ResourceUtils.belongsTo(packResources.get(id), type);
    }

    @Override
    public Set<String> getNamespaces(ResourceType type) {
        return switch (type) {
            case CLIENT_RESOURCES -> packClientNamespaces;
            case SERVER_DATA -> packServerNamespaces;
        };
    }

    @Nullable
    @Override
    public <T> T parseMetadata(ResourceMetadataReader<T> reader) throws IOException {
        Identifier id = new Identifier(packId.getNamespace(), PACK_METADATA_NAME);
        if (!packResources.containsKey(id))
            throw new FileNotFoundException("Pack mcmeta was not found for pack: " + id);
        if (packResources.get(id) instanceof MetaResource metaResource) {
            JsonObject meta = ResourceUtils.getJson(metaResource);
            return meta.has(reader.getKey())
                    ? reader.fromJson(meta.getAsJsonObject(reader.getKey()))
                    : null;
        } else {
            throw new RuntimeException("Invalid metadata for pack: " + id);
        }
    }

    @Override
    public String getName() {
        return packName;
    }

    @Override
    public void close() {

    }

    public ResourcePackProfile getResourcePack(ResourcePackProfile.Factory factory) {
        String id = packId.toString();
        return ResourcePackProfile.of(
                id,
                false, () -> this, factory,
                ResourcePackProfile.InsertionPosition.BOTTOM,
                RESOURCE_PACK_SOURCE
        );
    }
}
