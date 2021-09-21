package lv.cebbys.mcmods.celib.respro.utils;

import lv.cebbys.mcmods.celib.respro.imp.sides.common.resources.blockstates.variant.VariantResource;
import lv.cebbys.mcmods.celib.respro.imp.utilities.Factory;
import org.junit.jupiter.api.Test;

public class GenericTests {

    @Test
    void test() {
        Factory<VariantResource> variantFactory = new Factory<VariantResource>() {
            @Override
            public VariantResource get() {
                return null;
            }
        };
        variantFactory.getInstance();
    }
}
