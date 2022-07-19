package lv.cebbys.mcmods.respro.component.resource.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractJsonElementResource extends AbstractStringResource {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().serializeNulls().create();

    public abstract @NotNull("AbstractJsonElementResource content is null") JsonElement getAsJsonElement();

    @Override
    public @NotNull("AbstractStringResource content is null") String getAsString() {
        return GSON.toJson(getAsJsonElement());
    }
}
