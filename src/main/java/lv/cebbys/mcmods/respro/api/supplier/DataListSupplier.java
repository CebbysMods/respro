package lv.cebbys.mcmods.respro.api.supplier;

import lv.cebbys.mcmods.respro.api.pack.Data;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public interface DataListSupplier extends PackListSupplier<Data> {
}
