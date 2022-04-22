package lv.cebbys.mcmods.respro.utils;

import lv.cebbys.mcmods.respro.imp.sides.common.resources.blockstates.variant.VariantResource;
import lv.cebbys.mcmods.respro.imp.function.Factory;
import org.junit.jupiter.api.Test;

public class GenericTests {

    @Test
    void test() {
        Factory<VariantResource> variantFactory = () -> null;
        variantFactory.get();
    }
}
