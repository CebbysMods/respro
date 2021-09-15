package lv.cebbys.mcmods.celib.respro.imp.providers.pack;

import lv.cebbys.mcmods.celib.respro.api.AssetPackProvider;
import lv.cebbys.mcmods.celib.respro.imp.ResourceRegistry;
import lv.cebbys.mcmods.celib.respro.imp.packs.ResproResourcePack;
import net.minecraft.util.registry.MutableRegistry;

public class ResproAssetProvider extends ResproPackProvider implements AssetPackProvider {
    @Override
    public MutableRegistry<ResproResourcePack> getResourceRegistry() {
        return ResourceRegistry.CLIENT_RESOURCE_PACKS;
    }
}
