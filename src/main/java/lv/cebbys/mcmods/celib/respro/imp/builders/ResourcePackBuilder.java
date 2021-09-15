package lv.cebbys.mcmods.celib.respro.imp.builders;

import lv.cebbys.mcmods.celib.respro.imp.loggers.ResproLogger;
import lv.cebbys.mcmods.celib.respro.imp.resources.ImageResource;
import lv.cebbys.mcmods.celib.respro.imp.resources.MetaResource;
import lv.cebbys.mcmods.celib.respro.imp.resources.Resource;
import lv.cebbys.mcmods.celib.respro.imp.resources.images.IconResource;
import lv.cebbys.mcmods.celib.respro.imp.utilities.ResourceUtils;
import net.minecraft.util.Identifier;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ResourcePackBuilder<B extends ResourcePackBuilder<?>> {

    protected final Map<Identifier, Resource> resources = new LinkedHashMap<>();
    protected final Identifier packId;
    protected String packName;

    protected ResourcePackBuilder(Identifier id) {
        packId = id;
    }

    public MetaResource setPackMeta(Consumer<MetaResource> consumer) {
        return resource(new Identifier(packId.getNamespace(), "pack.mcmeta"), MetaResource.class, consumer);
    }

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
        return resource(new Identifier(pathToPng.getNamespace(), "pack.png"), resource);
    }

    public String setPackName(String name) {
        if(packName == null) {
            packName = name;
        } else {
            ResproLogger.warn("Pack name has been already set");
        }
        return packName;
    }

    public void setPackIcon(String pathToPng) {
        setPackIcon(new Identifier(packId.getNamespace(), pathToPng));
    }

    protected <T extends Resource> T resource(Identifier id, Class<T> clazz) {
        T resource = ResourceUtils.createResource(clazz);
        resource(id, resource);
        return resource;
    }

    protected <T extends Resource> T resource(Identifier id, Class<T> clazz, Consumer<T> consumer) {
        T resource = ResourceUtils.createResource(clazz);
        consumer.accept(resource);
        resource(id, resource);
        return resource;
    }

    protected <T extends Resource> T resource(Identifier id, T resource) {
        ResproLogger.debug("Appending resource %s to resource pack %s", id, packId);
        if (resources.containsKey(id)) {
            ResproLogger.warn("Resource already exists with provided key " + id);
        } else {
            resources.put(id, resource);
        }
        return resource;
    }

    protected B instance;

    protected B setInstance(B builderInstance) {
        instance = builderInstance;
        return instance;
    }
}
