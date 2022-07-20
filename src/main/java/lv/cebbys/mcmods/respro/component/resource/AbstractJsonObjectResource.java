package lv.cebbys.mcmods.respro.component.resource;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lv.cebbys.mcmods.respro.component.mapper.ResourceParser;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractJsonObjectResource extends AbstractJsonElementResource {
    private static final ResourceParser PARSER = new ResourceParser();

    public @NotNull("AbstractJsonObjectResource content is null") JsonObject getAsJsonObject() {
        return PARSER.parse(this);
    }

    @Override
    public @NotNull("AbstractJsonElementResource content is null") JsonElement getAsJsonElement() {
        return getAsJsonObject();
    }
}
