package lv.cebbys.mcmods.respro.component.supplier;

import lv.cebbys.mcmods.respro.api.pack.Assets;
import lv.cebbys.mcmods.respro.api.supplier.AssetSupplier;
import lv.cebbys.mcmods.respro.component.resource.pack.ResproPackResources;

import java.util.function.Function;
import java.util.function.Supplier;

public class SimpleAssetSupplier extends SimplePackSupplier<Assets> implements AssetSupplier {
    public SimpleAssetSupplier(Supplier<ResproPackResources<?, ?>> packResources, Function<ResproPackResources<?, ?>, Assets> packCreator) {
        super(packResources, packCreator);
    }
}
