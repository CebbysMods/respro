package lv.cebbys.mcmods.respro.utility;

import net.minecraft.resources.ResourceLocation;

public final class ResourceLocationUtils {
    private ResourceLocationUtils() {
    }

    public static ResourceLocation prefix(ResourceLocation id, String prefix) {
        return new ResourceLocation(id.getNamespace(), prefix + id.getPath());
    }

    public static ResourceLocation suffix(ResourceLocation id, String suffix) {
        return new ResourceLocation(id.getNamespace(), id.getPath() + suffix);
    }

    public static ResourceLocation wrapped(String prefix, ResourceLocation id, String suffix) {
        return new ResourceLocation(id.getNamespace(), prefix + id.getPath() + suffix);
    }
}
