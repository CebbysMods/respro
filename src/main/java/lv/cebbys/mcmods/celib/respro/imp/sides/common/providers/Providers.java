package lv.cebbys.mcmods.celib.respro.imp.sides.common.providers;

import lv.cebbys.mcmods.celib.respro.imp.sides.client.providers.ResproAssetProfileProvider;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resource.ResourcePackProvider;

public class Providers {
    @Environment(EnvType.CLIENT)
    public static final ResproProfileProvider<ResourcePackProvider> ASSET_PROFILE_PROVIDER = new ResproAssetProfileProvider();

    public static final ResproProfileProvider<ResourcePackProvider> DATA_PROFILE_PROVIDER = new ResproDataProfileProvider();

}
