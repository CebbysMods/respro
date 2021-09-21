package lv.cebbys.mcmods.celib.respro.imp.sides.common.builders.impl;

import lv.cebbys.mcmods.celib.respro.imp.sides.common.resources.Resource;
import net.minecraft.util.Identifier;

import java.util.Map;

public abstract class ResourcePackExtractor<T extends ResourcePackBuilderImpl<?>> extends ResourcePackBuilderImpl<T> {

    public static <T extends ResourcePackBuilderImpl<?>> Identifier getPackId(ResourcePackBuilderImpl<T> builder) {
        return builder.packId;
    }

    public static <T extends ResourcePackBuilderImpl<?>> String getPackName(ResourcePackBuilderImpl<T> builder) {
        return builder.packName;
    }

    public static <T extends ResourcePackBuilderImpl<?>> Map<Identifier, Resource> getPackResources(ResourcePackBuilderImpl<T> builder) {
        return builder.resources;
    }

    private ResourcePackExtractor(Identifier id) {
        super(id);
    }
}
