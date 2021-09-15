package lv.cebbys.mcmods.celib.respro.resources;

import lv.cebbys.mcmods.celib.respro.imp.builders.AssetPackBuilder;
import lv.cebbys.mcmods.celib.respro.imp.builders.ResourcePackBuilder;
import lv.cebbys.mcmods.celib.respro.imp.resources.images.IconResource;
import net.minecraft.util.Identifier;
import org.junit.jupiter.api.Test;

public class ResourcePackBuilderTests {

    private final String modid = "respro";
    private final AssetPackBuilder builder = AssetPackBuilder.builder(new Identifier(modid));

    @Test
    void setPackIconTest() {
        IconResource icon = builder.setPackIcon(new Identifier(modid, "textures/icons/default_icon.pg"));
        icon.getAsStream();
    }
}
