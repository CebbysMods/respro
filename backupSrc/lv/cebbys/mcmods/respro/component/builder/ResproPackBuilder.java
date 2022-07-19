package lv.cebbys.mcmods.respro.component.builder;

import lv.cebbys.mcmods.respro.api.factory.profile.PackProfileSupplier;

public abstract class ResproPackBuilder<S extends PackProfileSupplier<?>> {
    public abstract S getSupplier();
}
