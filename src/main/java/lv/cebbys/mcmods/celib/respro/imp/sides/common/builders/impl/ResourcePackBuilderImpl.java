package lv.cebbys.mcmods.celib.respro.imp.sides.common.builders.impl;

import lv.cebbys.mcmods.celib.respro.imp.loggers.ResproLogger;
import lv.cebbys.mcmods.celib.respro.imp.sides.common.builders.ResourcePackBuilder;
import lv.cebbys.mcmods.celib.respro.imp.sides.common.resources.ImageResource;
import lv.cebbys.mcmods.celib.respro.imp.sides.common.resources.MetaResource;
import lv.cebbys.mcmods.celib.respro.imp.sides.common.resources.Resource;
import lv.cebbys.mcmods.celib.respro.imp.sides.common.resources.images.IconResource;
import lv.cebbys.mcmods.celib.respro.imp.utilities.Factory;
import lv.cebbys.mcmods.celib.respro.imp.utilities.ResourceUtils;
import net.minecraft.util.Identifier;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ResourcePackBuilderImpl<B extends ResourcePackBuilderImpl<?>> implements ResourcePackBuilder {

    protected final Map<Identifier, Resource> resources = new LinkedHashMap<>();
    protected final Identifier packId;
    protected String packName;

    public MetaResource setPackMeta(Consumer<MetaResource> consumer) {
        return addResource(new Identifier(packId.getNamespace(), "pack.mcmeta"), MetaResource.class, consumer);
    }

    @Override
    public IconResource setPackIcon(Identifier pathToPng) {
        IconResource resource = new IconResource(() -> {
            String resourcePath = "assets/" + pathToPng.getNamespace() + "/" + pathToPng.getPath();
            InputStream pngStream = getClass().getClassLoader().getResourceAsStream(resourcePath);
            if (pngStream != null) {
                return pngStream;
            } else {
                ResproLogger.warn("Failed to locate pack icon png at: " + resourcePath);
                ResproLogger.info("Using default pack png");
                return getClass().getClassLoader().getResourceAsStream(
                        "assets/respro/textures/icons/default_icon.png"
                );
            }
        });
        return addResource(new Identifier(pathToPng.getNamespace(), "pack.png"), resource);
    }

    @Override
    public MetaResource setPackDescription(Consumer<MetaResource> consumer) {
        return addResource(new Identifier(packId.getNamespace(), "pack.mcmeta"), MetaResource.class, consumer);
    }

    public String setPackName(String name) {
        if(packName == null) {
            packName = name;
        } else {
            ResproLogger.warn("Pack name has been already set");
        }
        return packName;
    }


    public ImageResource setPackIcon(String pathToPng) {
        return setPackIcon(new Identifier(packId.getNamespace(), pathToPng));
    }

    @Override
    public <T extends Resource> T addResource(Identifier id, Class<T> clazz) {
        T resource = ResourceUtils.createResource(clazz);
        addResource(id, resource);
        return resource;
    }

    @Override
    public <T extends Resource> T addResource(Identifier id, Class<T> clazz, Consumer<T> consumer) {
        T resource = ResourceUtils.createResource(clazz);
        consumer.accept(resource);
        addResource(id, resource);
        return resource;
    }

    @Override
    public <T extends Resource> T addResource(Identifier id, T resource) {
        ResproLogger.debug("Appending resource %s to resource pack %s", id, packId);
        if (resources.containsKey(id)) {
            ResproLogger.warn("Resource already exists with provided key " + id);
        } else {
            resources.put(id, resource);
        }
        return resource;
    }

    @Override
    public <T extends Resource> T addResource(Identifier id, Factory<T> factory) {
        return addResource(id, factory.get());
    }

    protected ResourcePackBuilderImpl(Identifier id) {
        packId = id;
    }
    protected B instance;
    protected B setInstance(B builderInstance) {
        instance = builderInstance;
        return instance;
    }
}
