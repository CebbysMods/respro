package lv.cebbys.mcmods.respro.api.supplier;

import lv.cebbys.mcmods.respro.api.pack.Assets;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public interface AssetListSupplier extends PackListSupplier<Assets> {
}
