package lv.cebbys.mcmods.celib.respro.imp.builders;

import lv.cebbys.mcmods.celib.respro.imp.resources.blockstates.multipart.MultipartStateResource;
import lv.cebbys.mcmods.celib.respro.imp.resources.blockstates.variant.VariantStateResource;
import lv.cebbys.mcmods.celib.respro.imp.resources.blockstates.variant.VariantWrapper;
import lv.cebbys.mcmods.celib.respro.imp.resources.models.items.ItemModelResource;
import lv.cebbys.mcmods.celib.respro.imp.utilities.IdentifierUtils;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Consumer;

public class AssetPackBuilder extends ResourcePackBuilder<AssetPackBuilder> {

    public void addItemModel(Identifier id, Consumer<ItemModelResource> consumer) {
        ItemModelResource resource = resource(
                IdentifierUtils.wrapped("models/item/", id, ".json"), ItemModelResource.class);
        consumer.accept(resource);
    }

    public void addVariantBlockState(Identifier id, Consumer<VariantWrapper> consumer) {
        VariantWrapper wrapper = new VariantWrapper();
        consumer.accept(wrapper);
        VariantStateResource resource = resource(
                IdentifierUtils.wrapped("blockstates/", id, ".json"), VariantStateResource.class);
        resource.variants(wrapper);
    }

    public void addVariantBlockState(Block block, Consumer<VariantWrapper> consumer) {
        addVariantBlockState(Registry.BLOCK.getId(block), consumer);
    }

    public void addMultipartBlockState(Identifier id, Consumer<MultipartStateResource> consumer) {
        consumer.accept(resource(
                IdentifierUtils.wrapped("blockstates/", id, ".json"),
                MultipartStateResource.class)
        );
    }

    public static AssetPackBuilder builder(Identifier id) {
        AssetPackBuilder builder = new AssetPackBuilder(id);
        return builder.setInstance(builder);
    }

    protected AssetPackBuilder(Identifier id) {
        super(id);
    }
}
