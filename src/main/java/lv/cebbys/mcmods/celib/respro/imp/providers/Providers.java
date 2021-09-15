package lv.cebbys.mcmods.celib.respro.imp.providers;

import lv.cebbys.mcmods.celib.respro.imp.providers.profile.ResproAssetProfileProvider;
import lv.cebbys.mcmods.celib.respro.imp.providers.profile.ResproDataProfileProvider;
import lv.cebbys.mcmods.celib.respro.imp.providers.profile.ResproProfileProvider;

public class Providers {
    public static final ResproProfileProvider ASSET_PROFILE_PROVIDER = new ResproAssetProfileProvider();
    public static final ResproProfileProvider DATA_PROFILE_PROVIDER = new ResproDataProfileProvider();
}
