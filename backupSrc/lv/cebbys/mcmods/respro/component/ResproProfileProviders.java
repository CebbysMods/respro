package lv.cebbys.mcmods.respro.component;

import lv.cebbys.mcmods.respro.imp.sides.client.provider.ResproAssetProfileProvider;
import lv.cebbys.mcmods.respro.imp.sides.common.provider.ResproDataProfileProvider;
import lv.cebbys.mcmods.respro.imp.sides.common.provider.ResproProfileProvider;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resource.ResourcePackProvider;

public class ResproProfileProviders {
    @Environment(EnvType.CLIENT)
    public static final ResproProfileProvider<ResourcePackProvider> ASSET_PROFILE_PROVIDER = new ResproAssetProfileProvider();

    public static final ResproProfileProvider<ResourcePackProvider> DATA_PROFILE_PROVIDER = new ResproDataProfileProvider();

}
