package lv.cebbys.mcmods.celib.respro.imp.sides.common.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lv.cebbys.mcmods.celib.respro.imp.loggers.ResproLogger;
import lv.cebbys.mcmods.celib.respro.imp.sides.common.resources.JsonResource;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ResourceParser {

    public JsonObject parse(Object object) {
        Class<?> clazz = object.getClass();
        JsonObject json = new JsonObject();
        Arrays.asList(clazz.getDeclaredFields()).forEach(field -> {
            try {
                if (field.trySetAccessible()) {
                    Annotation jsonPart = field.getAnnotation(JsonPart.class);
                    if(jsonPart != null) {
                        Method pathMethod = jsonPart.annotationType().getMethod("path");
                        pathMethod.setAccessible(true);
                        String path = pathMethod.invoke(jsonPart).toString();
                        appendToJsonObject(path, json, field.get(object));
                    }
                }

            } catch (Exception e) {
                ResproLogger.error(e, "Failed to parse field");
            }
        });
        return json;
    }

    @SuppressWarnings("all")
    private void appendToJsonObject(String path, JsonObject json, Object data) {
        if (data != null) {
            if (path.contains("/")) {
                String name = path.substring(0, path.indexOf("/"));
                path = path.substring(path.indexOf("/") + 1);
                JsonObject sub = json.has(name) ? json.getAsJsonObject(name) : new JsonObject();
                appendToJsonObject(path, sub, data);
                json.add(name, sub);
            } else {
                if (data instanceof String s) {
                    json.addProperty(path, s);
                } else if (data instanceof Number s) {
                    json.addProperty(path, s);
                } else if (data instanceof Character s) {
                    json.addProperty(path, s);
                } else if (data instanceof Boolean s) {
                    json.addProperty(path, s);
                } else if (data instanceof Object[] s) {
                    appendToJsonObject(path, json, Arrays.asList(s));
                } else if (data instanceof List) {
                    JsonArray array = new JsonArray();
                    appendToJsonArray(array, data);
                    json.add(path, array);
                } else if (data instanceof Map) {
                    Map<Object, Object> s = (Map<Object, Object>) data;
                    JsonObject map = new JsonObject();
                    s.forEach((key, val) -> {
                        appendToJsonObject(key.toString(), map, val);
                    });
                    json.add(path, map);
                } else if (data instanceof JsonResource s) {
                    json.add(path, parse(s));
                } else {
                    throw new RuntimeException(data.getClass() + " parsing has not been implemented.");
                }
            }
        }
    }

    @SuppressWarnings("all")
    private void appendToJsonArray(JsonArray array, Object data) {
        if (data != null) {
            List<Object> l = (List<Object>) data;
            l.forEach(element -> {
                if (element instanceof String s) {
                    array.add(s);
                } else if (element instanceof Number s) {
                    array.add(s);
                } else if (element instanceof Character s) {
                    array.add(s);
                } else if (element instanceof Boolean s) {
                    array.add(s);
                } else if (element instanceof Object[] s) {
                    appendToJsonArray(array, Arrays.asList(s));
                } else if (element instanceof List) {
                    JsonArray subArray = new JsonArray();
                    appendToJsonArray(subArray, element);
                    array.add(subArray);
                } else if (element instanceof Map) {
                    Map<Object, Object> s = (Map<Object, Object>) element;
                    JsonObject map = new JsonObject();
                    s.forEach((key, val) -> {
                        appendToJsonObject(key.toString(), map, val);
                    });
                    array.add(map);
                } else if (element instanceof JsonResource s) {
                    array.add(parse(s));
                } else {
                    throw new RuntimeException(element.getClass() + " Resource parsing has not been implemented.");
                }
            });
        }
    }
}
