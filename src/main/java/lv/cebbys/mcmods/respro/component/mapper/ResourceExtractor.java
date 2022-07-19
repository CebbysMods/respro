package lv.cebbys.mcmods.respro.component.mapper;

import java.util.Map;

public abstract class ResourceExtractor<V, T> extends ResourceWrapper<V, T> {
    public static <V, T> Map<V, T> data(ResourceWrapper<V, T> wrapper) {
        return wrapper.data;
    }
}
