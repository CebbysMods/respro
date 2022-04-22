package lv.cebbys.mcmods.respro.imp.sides.client.providers;

import lv.cebbys.mcmods.respro.api.AssetPackProvider;
import lv.cebbys.mcmods.respro.imp.ResourceRegistry;
import lv.cebbys.mcmods.respro.imp.sides.client.packs.ResproAssetPack;
import lv.cebbys.mcmods.respro.imp.sides.common.providers.ResproPackProvider;
import net.minecraft.util.registry.MutableRegistry;

public class ResproAssetProvider extends ResproPackProvider<ResproAssetPack> implements AssetPackProvider {
    @Override
    public MutableRegistry<ResproAssetPack> getResourceRegistry() {
        return ResourceRegistry.CLIENT_RESOURCE_PACKS;
    }
}
