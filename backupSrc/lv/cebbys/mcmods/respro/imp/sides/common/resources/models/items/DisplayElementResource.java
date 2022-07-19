package lv.cebbys.mcmods.respro.component.resource.items;

import lv.cebbys.mcmods.respro.component.resource.JsonObjectResource;
import lv.cebbys.mcmods.respro.mapper.JsonPart;
import net.minecraft.resource.ResourceType;

import java.lang.reflect.Array;

public class DisplayElementResource extends JsonObjectResource {
    @JsonPart(value = "rotation")
    private Integer[] rotation;
    @JsonPart(value = "translation")
    private Float[] translation;
    @JsonPart(value = "scale")
    private Float[] scale;

    public DisplayElementResource rotation(Number x, Number y, Number z) {
        return rotation(
                x == null ? 0 : x.intValue(),
                y == null ? 0 : y.intValue(),
                z == null ? 0 : z.intValue()
        );
    }
    public DisplayElementResource rotation(int x, int y, int z) {
        if(rotation == null) {
            rotation = field(Integer.class, x, y, z);
        } else {
//            ResproLogger.warn("Item model translation has been already set");
        }
        return this;
    }

    public DisplayElementResource translation(Number x, Number y, Number z) {
        return translation(
                x == null ? 0 : x.floatValue(),
                y == null ? 0 : y.floatValue(),
                z == null ? 0 : z.floatValue()
        );
    }
    public DisplayElementResource translation(float x, float y, float z) {
        if(translation == null) {
            translation = field(Float.class, x, y, z);
        } else {
//            ResproLogger.warn("Item model translation has been already set");
        }
        return this;
    }

    public DisplayElementResource scale(Number x, Number y, Number z) {
        return scale(
                x == null ? 0 : x.floatValue(),
                y == null ? 0 : y.floatValue(),
                z == null ? 0 : z.floatValue()
        );
    }
    public DisplayElementResource scale(float x, float y, float z) {
        if(scale == null) {
            scale = field(Float.class, x, y, z);
        } else {
//            ResproLogger.warn("Item model translation has been already set");
        }
        return this;
    }

    @SuppressWarnings("unchecked")
    private <T> T[] field(Class<T> clazz, T x, T y, T z) {
        T[] container = (T[]) Array.newInstance(clazz, 3);
        container[0] = x;
        container[1] = y;
        container[2] = z;
        return container;
    }

    @Override
    protected boolean belongsTo(ResourceType type) {
        return ResourceType.CLIENT_RESOURCES.equals(type);
    }
}
