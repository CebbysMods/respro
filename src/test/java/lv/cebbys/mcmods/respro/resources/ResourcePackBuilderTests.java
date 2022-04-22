package lv.cebbys.mcmods.respro.resources;

import lv.cebbys.mcmods.respro.Respro;
import lv.cebbys.mcmods.respro.imp.builders.assetpack.AssetPackBuilder;
import lv.cebbys.mcmods.respro.imp.builders.datapack.DataPackBuilder;
import lv.cebbys.mcmods.respro.imp.resource.recipe.CustreRecipeResource;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import org.junit.jupiter.api.Test;

public class ResourcePackBuilderTests {

    private final String modid = "respro";
    private final AssetPackBuilder builder = new AssetPackBuilder(new Identifier(modid));

    private DataPackBuilder dataBuilder = new DataPackBuilder(new Identifier(modid));

    @Test
    void dataPackTest() {
        dataBuilder.addPackName("Test Data Pack");
        dataBuilder.addCustreRecipe(new Identifier(Respro.MODID, "oak_log_strip"), new CustreRecipeResource(Blocks.OAK_LOG, Blocks.STRIPPED_OAK_LOG));
    }

    @Test
    void setPackIconTest() {
        builder.addPackIcon(new Identifier(modid, "textures/icons/default_icon.pg"));
    }
}
