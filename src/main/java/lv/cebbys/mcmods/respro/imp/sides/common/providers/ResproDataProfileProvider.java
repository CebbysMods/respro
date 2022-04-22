package lv.cebbys.mcmods.respro.imp.sides.common.providers;

import lv.cebbys.mcmods.respro.api.ResproRegistry;
import net.minecraft.resource.ResourcePackProvider;
import net.minecraft.util.registry.MutableRegistry;

public class ResproDataProfileProvider extends ResproProfileProvider<ResourcePackProvider> {
    @Override
    protected MutableRegistry<ResourcePackProvider> getRegistry() {
        return ResproRegistry.SERVER_RESOURCE_PROVIDERS;
    }
}
