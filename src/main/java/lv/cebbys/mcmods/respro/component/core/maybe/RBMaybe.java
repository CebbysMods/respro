package lv.cebbys.mcmods.respro.component.core.maybe;

import lv.cebbys.mcmods.respro.api.builder.ResourceBuilder;
import lv.cebbys.mcmods.respro.component.resource.AbstractResource;

public class RBMaybe<I, R extends AbstractResource> extends Maybe<ResourceBuilder<I, R>> {
    public RBMaybe(ResourceBuilder<I, R> v) {
        super(v);
    }

    public RBMaybe() {
        this(null);
    }
}
