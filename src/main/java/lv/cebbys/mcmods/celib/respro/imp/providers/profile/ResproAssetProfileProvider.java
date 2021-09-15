package lv.cebbys.mcmods.celib.respro.imp.providers.profile;

import lv.cebbys.mcmods.celib.respro.api.ResproRegistry;
import net.minecraft.resource.ResourcePackProvider;
import net.minecraft.util.registry.MutableRegistry;

public class ResproAssetProfileProvider extends ResproProfileProvider {

    @Override
    protected MutableRegistry<ResourcePackProvider> getRegistry() {
        return ResproRegistry.CLIENT_RESOURCE_PROVIDERS;
    }
}
