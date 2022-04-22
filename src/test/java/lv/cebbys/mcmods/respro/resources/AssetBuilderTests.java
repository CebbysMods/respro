package lv.cebbys.mcmods.respro.resources;

import lv.cebbys.mcmods.respro.imp.builders.assetpack.AssetPackBuilder;
import lv.cebbys.mcmods.respro.imp.component.BlockProperty;
import net.minecraft.util.Identifier;
import org.junit.jupiter.api.Test;

public class AssetBuilderTests {
    @Test
    void test0_assetPackBuilderTests() {
        AssetPackBuilder builder = new AssetPackBuilder(new Identifier("test"));

        builder.addMultipartBlockState(new Identifier("multipart_blockstate"), multipart -> {
            multipart
                    .when(new BlockProperty("size", "small", "medium", "large")
                            .with("weight", "heavy"))
                    .or(new BlockProperty("direction", "up", "down")
                            .with("test", "1", "2", "3", "4")
                            .with("sun", "shines"))
                    .or(new BlockProperty("a", "v", "c", "d", "g", "f", "e")
                            .with("i", "see", "you")
                            .with("value", "234"))
                    .apply(r -> r.model(new Identifier("test", "impossible_block_model")));
        });
    }
}
