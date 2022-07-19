package lv.cebbys.mcmods.respro.imp.sides.client.provider;

import lv.cebbys.mcmods.respro.imp.sides.common.provider.ResproProfileProvider;
import net.minecraft.resource.ResourcePackProvider;
import net.minecraft.util.registry.MutableRegistry;

public class ResproAssetProfileProvider extends ResproProfileProvider<ResourcePackProvider> {
    @Override
    protected MutableRegistry<ResourcePackProvider> getRegistry() {
//        return ResproRegistry.CLIENT_RESOURCE_PROVIDERS;
        return null;
    }
}
