package lv.cebbys.mcmods.respro.component.resource;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lv.cebbys.mcmods.respro.mapper.ResourceParser;

public abstract class JsonObjectResource extends TextResource {
    protected JsonObject getAsJsonObject() {
        return new ResourceParser().parse(this);
    }

    @Override
    protected String getAsString() {
        if (getAsJsonObject() == null) return null;
        return new Gson().toJson(getAsJsonObject());
    }
}
