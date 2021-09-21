package lv.cebbys.mcmods.celib.respro.imp.sides.common.resources;

import com.google.gson.JsonObject;
import lv.cebbys.mcmods.celib.respro.imp.sides.common.parser.ResourceParser;
import net.minecraft.resource.ResourceType;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public abstract class JsonResource extends Resource {
    protected JsonObject getAsJson() {
        ResourceParser parser = new ResourceParser();
        return parser.parse(this);
    }

    @Override
    protected InputStream getAsStream() {
        return new ByteArrayInputStream(getAsJson().toString().getBytes(StandardCharsets.UTF_8));
    }

    @Override
    protected boolean belongsTo(ResourceType type) {
        return true;
    }
}
