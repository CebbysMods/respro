package lv.cebbys.mcmods.celib.respro.imp.sides.common.resources;

import com.google.gson.JsonObject;
import net.minecraft.resource.ResourceType;

import java.io.InputStream;

public abstract class ResourceUtils extends Resource {
    public static InputStream getStream(Resource resource) {
        return resource.getAsStream();
    }
    public static boolean belongsTo(Resource resource, ResourceType type) {
        return resource.belongsTo(type);
    }
    public static JsonObject getJson(JsonResource json) {
        return json.getAsJson();
    }
}
