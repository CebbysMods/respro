package lv.cebbys.mcmods.respro.component.resource.blockstate.multipart;

import com.google.gson.JsonObject;
import lv.cebbys.mcmods.respro.api.initializer.blockstate.multipart.MultipartBlockPropertyResourceInitializer;
import lv.cebbys.mcmods.respro.component.core.maybe.Maybe;
import lv.cebbys.mcmods.respro.component.mapper.JsonPart;
import lv.cebbys.mcmods.respro.component.resource.AbstractJsonObjectResource;
import lv.cebbys.mcmods.respro.exception.ResourceValidationException;
import net.minecraft.server.packs.PackType;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public final class MultipartBlockPropertyResource extends AbstractJsonObjectResource implements MultipartBlockPropertyResourceInitializer {
    @JsonPart("properties")
    private final Map<String, String> properties = new HashMap<>();

    @Override
    public @NotNull MultipartBlockPropertyResourceInitializer setProperty(@NotNull String key, @NotNull String... values) {
        new Maybe<>(values).ifTrue(values.length != 0)
                .transform(Arrays::stream)
                .transform(stream -> stream.collect(Collectors.joining("|")))
                .manipulate(value -> properties.put(key, value));
        return this;
    }

    @Override
    public boolean belongsTo(@NotNull("Provided PackType is null") PackType type) {
        return false;
    }

    @Override
    public @NotNull("MultipartBlockPropertyResource content is null") JsonObject getAsJsonObject() {
        JsonObject original = super.getAsJsonObject();
        if (original.has("properties")) return original.getAsJsonObject("properties");
        return new JsonObject();
    }

    @Override
    public void validate() throws ResourceValidationException {
        if (properties.size() == 0) {
            throw new ResourceValidationException(this.getClass().getSimpleName() + " does not have any properties");
        }
    }
}
