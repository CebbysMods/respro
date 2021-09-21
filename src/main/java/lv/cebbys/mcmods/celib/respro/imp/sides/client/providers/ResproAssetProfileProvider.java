package lv.cebbys.mcmods.celib.respro.imp.sides.client.providers;

import lv.cebbys.mcmods.celib.respro.api.ResproRegistry;
import lv.cebbys.mcmods.celib.respro.imp.sides.common.providers.ResproProfileProvider;
import net.minecraft.resource.ResourcePackProvider;
import net.minecraft.util.registry.MutableRegistry;

public class ResproAssetProfileProvider extends ResproProfileProvider<ResourcePackProvider> {
    @Override
    protected MutableRegistry<ResourcePackProvider> getRegistry() {
        return ResproRegistry.CLIENT_RESOURCE_PROVIDERS;
    }
}
