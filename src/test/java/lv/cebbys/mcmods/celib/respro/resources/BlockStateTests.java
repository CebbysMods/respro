package lv.cebbys.mcmods.celib.respro.resources;

import lv.cebbys.mcmods.celib.respro.imp.parser.ResourceParser;
import lv.cebbys.mcmods.celib.respro.imp.resources.blockstates.variant.VariantStateResource;
import lv.cebbys.mcmods.celib.respro.imp.resources.blockstates.variant.VariantWrapper;
import lv.cebbys.mcmods.celib.respro.imp.utilities.BlockProperty;
import net.minecraft.util.Identifier;
import org.junit.jupiter.api.Test;

public class BlockStateTests {

    @Test
    void variantTests() {
        VariantStateResource state = new VariantStateResource();
        state.variants((VariantWrapper wrapper) -> {
            wrapper.variants(new BlockProperty().with("rotation", "90"), collector -> {
                collector.resource().model(new Identifier("test_model")).weight(100);
                collector.resource().model(new Identifier("test_model_1")).weight(120).uvlock(true);
                collector.resource().model(new Identifier("test_model_2")).weight(10).uvlock(true).y(99);
                collector.resource().model(new Identifier("test_model_3")).weight(40).uvlock(true).x(32);
            });
        });
        ResourceParser parser = new ResourceParser();
        System.out.println(parser.parse(state));
    }
}
