package lv.cebbys.mcmods.respro.imp.builders.assetpack;

import lv.cebbys.mcmods.respro.imp.builders.resourcepack.ResourcePackBuilder;
import lv.cebbys.mcmods.respro.imp.sides.common.resources.blockstates.multipart.MultipartStateResource;
import lv.cebbys.mcmods.respro.imp.sides.common.resources.blockstates.variant.VariantStateResource;
import lv.cebbys.mcmods.respro.imp.sides.common.resources.blockstates.variant.VariantWrapper;
import lv.cebbys.mcmods.respro.imp.sides.common.resources.models.items.ItemModelResource;
import lv.cebbys.mcmods.respro.imp.utilities.IdentifierUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Consumer;

@Environment(EnvType.CLIENT)
public class AssetPackBuilder extends ResourcePackBuilder {

    public void addItemModel(Identifier id, Consumer<ItemModelResource> consumer) {
        addResource(
                IdentifierUtils.wrapped("models/item/", id, ".json"),
                ItemModelResource.class,
                consumer);
    }

    public void addVariantBlockState(Identifier id, Consumer<VariantWrapper> consumer) {
        VariantWrapper wrapper = new VariantWrapper();
        consumer.accept(wrapper);
        VariantStateResource resource = addResource(
                IdentifierUtils.wrapped("blockstates/", id, ".json"),
                VariantStateResource.class);
        resource.variants(wrapper);
    }

    public void addVariantBlockState(Block block, Consumer<VariantWrapper> consumer) {
        addVariantBlockState(Registry.BLOCK.getId(block), consumer);
    }

    public void addMultipartBlockState(Identifier id, Consumer<MultipartStateResource> consumer) {
        addResource(
                IdentifierUtils.wrapped("blockstates/", id, ".json"),
                MultipartStateResource.class,
                consumer);
    }

    public AssetPackBuilder(Identifier id) {
        super(id);
    }
}
