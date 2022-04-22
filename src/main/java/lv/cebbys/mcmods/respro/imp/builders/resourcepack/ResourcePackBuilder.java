package lv.cebbys.mcmods.respro.imp.builders.resourcepack;

import lv.cebbys.mcmods.respro.imp.function.Factory;
import lv.cebbys.mcmods.respro.imp.resource.IconResource;
import lv.cebbys.mcmods.respro.imp.resource.ImageResource;
import lv.cebbys.mcmods.respro.imp.resource.MetaResource;
import lv.cebbys.mcmods.respro.imp.resource.Resource;
import lv.cebbys.mcmods.respro.imp.utilities.ResourceUtils;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ResourcePackBuilder {
    private static final Logger logger = LoggerFactory.getLogger("ResproPackBuilder");

    protected final Map<Identifier, Resource> resources = new LinkedHashMap<>();
    protected final Identifier packId;
    protected String packName;
    protected MetaResource packMeta;
    protected ImageResource packImage;

    protected ResourcePackBuilder(Identifier id) {
        packId = id;
    }

    public ImageResource addPackIcon(Identifier iconId) {
        return addPackIcon(String.format("assets/%s/%s", iconId.getNamespace(), iconId.getPath()));
    }

    public ImageResource addPackIcon(String resourcePath) {
        return addPackIcon(new IconResource(resourcePath));
    }

    public ImageResource addPackIcon(IconResource resource) {
        if (packImage == null) {
            packImage = addResource(new Identifier(packId.getNamespace(), "pack.png"), resource);
        } else {
            logger.warn("Pack {} image has been already added", packId);
        }
        return packImage;
    }

    public MetaResource addPackMeta(Consumer<MetaResource> consumer) {
        if (packMeta == null) {
            packMeta = addResource(new Identifier(packId.getNamespace(), "pack.mcmeta"), MetaResource.class, consumer);
        } else {
            logger.warn("Pack {} metadata has been already added", packId);
        }
        return packMeta;
    }

    public String addPackName(String name) {
        if (packName == null) {
            packName = name;
        } else {
            logger.warn("Pack {} name has been already added", packId);
        }
        return packName;
    }

    public <T extends Resource> T addResource(Identifier id, Class<T> clazz) {
        T resource = ResourceUtils.createResource(clazz);
        addResource(id, resource);
        return resource;
    }

    public <T extends Resource> T addResource(Identifier id, Class<T> clazz, Consumer<T> consumer) {
        T resource = ResourceUtils.createResource(clazz);
        consumer.accept(resource);
        addResource(id, resource);
        return resource;
    }

    public <T extends Resource> T addResource(Identifier id, T resource) {
        if (resources.containsKey(id)) {
            logger.warn("Resource {} already exists in pack {}", id, packId);
        } else {
            logger.debug("Appending resource {} to pack {}", id, packId);
            resources.put(id, resource);
        }
        return resource;
    }

    public <T extends Resource> T addResource(Identifier id, Factory<T> factory) {
        return addResource(id, factory.get());
    }
}
