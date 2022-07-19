package lv.cebbys.mcmods.respro.imp.utility;

import com.google.gson.JsonObject;
import lv.cebbys.mcmods.respro.component.resource.JsonObjectResource;
import lv.cebbys.mcmods.respro.component.resource.Resource;
import lv.cebbys.mcmods.respro.component.resource.TextResource;
import lv.cebbys.mcmods.respro.core.reflect.NonMethodTemplate;
import lv.cebbys.mcmods.respro.core.reflect.UniMethodTemplate;
import net.minecraft.resource.ResourceType;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ResourceUtils {
    private static final NonMethodTemplate<Resource, InputStream> RESOURCE_GET_AS_STREAM;
    private static final UniMethodTemplate<Resource, Boolean, ResourceType> RESOURCE_BELONGS_TO;
    private static final NonMethodTemplate<TextResource, String> TEXT_RESOURCE_GET_AS_STRING;
    private static final NonMethodTemplate<JsonObjectResource, JsonObject> JSON_OBJECT_RESOURCE_GET_AS_JSON_OBJECT;

    public static <T extends Resource> T createResource(Class<T> clazz) {
        Throwable error;
        try {
            Constructor<T> constructor = clazz.getConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
            error = e;
//            ResproLogger.error(e, "Failed to instantiate resource " + clazz.getName());
        } catch (NoSuchMethodException e) {
            error = e;
//            ResproLogger.error(e, "No argument constructor is present in class " + clazz.getName());
        }
        throw new RuntimeException(error);
    }

    public static InputStream getAsStream(Resource resource) {
        if (resource == null) return InputStream.nullInputStream();
        return RESOURCE_GET_AS_STREAM.invoke(resource);
    }

    public static String getAsString(TextResource resource) {
        if (resource == null) return null;
        return TEXT_RESOURCE_GET_AS_STRING.invoke(resource);
    }

    public static JsonObject getAsJsonObject(JsonObjectResource resource) {
        if (resource == null) return null;
        return JSON_OBJECT_RESOURCE_GET_AS_JSON_OBJECT.invoke(resource);
    }

    public static boolean belongsTo(Resource resource, ResourceType type) {
        if (resource == null) return false;
        return RESOURCE_BELONGS_TO.invoke(resource, type);
    }

    static {
        RESOURCE_GET_AS_STREAM = new NonMethodTemplate<>(InputStream.class, "getAsStream");
        RESOURCE_BELONGS_TO = new UniMethodTemplate<>(Boolean.class, "belongsTo", ResourceType.class);
        TEXT_RESOURCE_GET_AS_STRING = new NonMethodTemplate<>(String.class, "getAsString");
        JSON_OBJECT_RESOURCE_GET_AS_JSON_OBJECT = new NonMethodTemplate<>(JsonObject.class, "getAsJsonObject");
    }
}
