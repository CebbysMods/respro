package lv.cebbys.mcmods.respro.component.resource.blockstate.multipart;

import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lv.cebbys.mcmods.respro.Respro;
import lv.cebbys.mcmods.respro.api.ResproBuilders;
import lv.cebbys.mcmods.respro.api.builder.ResourceBuilder;
import lv.cebbys.mcmods.respro.api.initializer.blockstate.BlockVariantResourceInitializer;
import lv.cebbys.mcmods.respro.api.initializer.blockstate.multipart.MultipartBlockstateResourceInitializer;
import lv.cebbys.mcmods.respro.api.initializer.blockstate.multipart.MultipartWhenResourceInitializer;
import lv.cebbys.mcmods.respro.component.core.maybe.RBMaybe;
import lv.cebbys.mcmods.respro.component.mapper.JsonPart;
import lv.cebbys.mcmods.respro.component.resource.AbstractJsonObjectResource;
import lv.cebbys.mcmods.respro.exception.ResourceValidationException;
import net.minecraft.server.packs.PackType;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@NoArgsConstructor
@Getter
public final class MultipartBlockstateResource extends AbstractJsonObjectResource implements MultipartBlockstateResourceInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Respro.class);
    @JsonPart("multipart")
    private final List<JsonObject> multipart = new ArrayList<>();

    @Override
    public @NotNull MultipartBlockstateResourceInitializer setWhen(@NotNull Consumer<MultipartWhenResourceInitializer> whenConsumer, @NotNull Consumer<BlockVariantResourceInitializer> applyConsumer) {
        JsonObject apply = new RBMaybe<BlockVariantResourceInitializer, AbstractJsonObjectResource>()
                .provide(ResproBuilders.supplyTypedBuilder(BlockVariantResourceInitializer.class))
                .manipulate(applyConsumer, ResourceBuilder::initialize)
                .manipulate(ResourceBuilder::validate)
                .transform(ResourceBuilder::build)
                .transform(AbstractJsonObjectResource::getAsJsonObject)
                .get();

        new RBMaybe<MultipartWhenResourceInitializer, AbstractJsonObjectResource>()
                .provide(ResproBuilders.supplyTypedBuilder(MultipartWhenResourceInitializer.class))
                .ifNull(apply)
                .manipulate(whenConsumer, ResourceBuilder::initialize)
                .manipulate(ResourceBuilder::validate)
                .transform(ResourceBuilder::build)
                .transform(AbstractJsonObjectResource::getAsJsonObject)
                .manipulate(json -> json.add("apply", apply))
                .manipulate(multipart::add);

        return this;
    }

    @Override
    public @NotNull MultipartBlockstateResourceInitializer setApply(@NotNull Consumer<BlockVariantResourceInitializer> consumer) {
        new RBMaybe<BlockVariantResourceInitializer, AbstractJsonObjectResource>()
                .provide(ResproBuilders.supplyTypedBuilder(BlockVariantResourceInitializer.class))
                .manipulate(consumer, ResourceBuilder::initialize)
                .manipulate(ResourceBuilder::validate)
                .transform(ResourceBuilder::build)
                .transform(AbstractJsonObjectResource::getAsJsonObject)
                .transform(json -> {
                    JsonObject apply = new JsonObject();
                    apply.add("apply", json);
                    return apply;
                })
                .manipulate(multipart::add);
        return this;
    }

    @Override
    public boolean belongsTo(@NotNull("Provided PackType is null") PackType type) {
        return PackType.CLIENT_RESOURCES.equals(type);
    }

    @Override
    public void validate() throws ResourceValidationException {

    }
}
