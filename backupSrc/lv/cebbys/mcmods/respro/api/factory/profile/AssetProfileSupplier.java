package lv.cebbys.mcmods.respro.api.factory.profile;

import lv.cebbys.mcmods.respro.api.pack.AssetPackProfile;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public interface AssetProfileSupplier extends PackProfileSupplier<AssetPackProfile> {
}
