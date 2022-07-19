package lv.cebbys.mcmods.respro.component.supplier;

import lv.cebbys.mcmods.respro.api.pack.Data;
import lv.cebbys.mcmods.respro.api.supplier.DataSupplier;
import lv.cebbys.mcmods.respro.component.resource.pack.ResproPackResources;

import java.util.function.Function;
import java.util.function.Supplier;

public class SimpleDataSupplier extends SimplePackSupplier<Data> implements DataSupplier {
    public SimpleDataSupplier(Supplier<ResproPackResources<?, ?>> packResources, Function<ResproPackResources<?, ?>, Data> packCreator) {
        super(packResources, packCreator);
    }
}
