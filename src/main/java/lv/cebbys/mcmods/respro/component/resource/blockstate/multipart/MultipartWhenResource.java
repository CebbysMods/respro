package lv.cebbys.mcmods.respro.component.resource.blockstate.multipart;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lv.cebbys.mcmods.respro.api.ResproBuilders;
import lv.cebbys.mcmods.respro.api.builder.ResourceBuilder;
import lv.cebbys.mcmods.respro.api.initializer.blockstate.multipart.MultipartBlockPropertyResourceInitializer;
import lv.cebbys.mcmods.respro.api.initializer.blockstate.multipart.MultipartWhenResourceInitializer;
import lv.cebbys.mcmods.respro.component.core.maybe.RBMaybe;
import lv.cebbys.mcmods.respro.component.mapper.JsonPart;
import lv.cebbys.mcmods.respro.component.resource.AbstractJsonObjectResource;
import lv.cebbys.mcmods.respro.exception.ResourceValidationException;
import net.minecraft.server.packs.PackType;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

@NoArgsConstructor
@Getter
public final class MultipartWhenResource extends AbstractJsonObjectResource implements MultipartWhenResourceInitializer {
    @JsonPart("when")
    private JsonObject when = new JsonObject();

    @Override
    public @NotNull MultipartWhenResourceInitializer setWhen(@NotNull Consumer<MultipartBlockPropertyResourceInitializer> consumer) {
        new RBMaybe<MultipartBlockPropertyResourceInitializer, AbstractJsonObjectResource>()
                .provide(ResproBuilders.supplyTypedBuilder(MultipartBlockPropertyResourceInitializer.class))
                .ifNotNull(when)
                .manipulate(consumer, ResourceBuilder::initialize)
                .manipulate(ResourceBuilder::validate)
                .transform(ResourceBuilder::build)
                .transform(AbstractJsonObjectResource::getAsJsonObject)
                .manipulate(newWhen -> {
                    if (when.entrySet().size() == 0) {
                        when = newWhen;
                        return;
                    } else if (when.entrySet().size() != 1 || !when.has("OR") || !when.get("OR").isJsonArray()) {
                        JsonArray or = new JsonArray();
                        or.add(when);
                        when = new JsonObject();
                        when.add("OR", or);
                    }
                    JsonArray or = when.getAsJsonArray("OR");
                    or.add(newWhen);
                });
        return this;
    }

    @Override
    public boolean belongsTo(@NotNull("Provided PackType is null") PackType type) {
        return false;
    }

    @Override
    public void validate() throws ResourceValidationException {
        if (when == null) {
            throw new ResourceValidationException("MultipartWhenResource 'when' property is null");
        }
        if (when.entrySet().size() == 0) {
            throw new ResourceValidationException("MultipartWhenResource 'when' property does not have any entries");
        }
    }
}
