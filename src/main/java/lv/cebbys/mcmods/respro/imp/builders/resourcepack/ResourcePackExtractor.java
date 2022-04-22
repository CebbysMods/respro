package lv.cebbys.mcmods.respro.imp.builders.resourcepack;

import lv.cebbys.mcmods.respro.imp.resource.Resource;
import net.minecraft.util.Identifier;

import java.util.Map;

public abstract class ResourcePackExtractor<T extends ResourcePackBuilder> extends ResourcePackBuilder {

    public static <T extends ResourcePackBuilder> Identifier getPackId(ResourcePackBuilder builder) {
        return builder.packId;
    }

    public static <T extends ResourcePackBuilder> String getPackName(ResourcePackBuilder builder) {
        return builder.packName;
    }

    public static <T extends ResourcePackBuilder> Map<Identifier, Resource> getPackResources(ResourcePackBuilder builder) {
        return builder.resources;
    }

    private ResourcePackExtractor(Identifier id) {
        super(id);
    }
}
