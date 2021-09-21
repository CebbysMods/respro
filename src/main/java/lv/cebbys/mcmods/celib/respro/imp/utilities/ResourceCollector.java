package lv.cebbys.mcmods.celib.respro.imp.utilities;

import lv.cebbys.mcmods.celib.respro.imp.sides.common.resources.Resource;

import java.util.LinkedList;
import java.util.List;

public class ResourceCollector<T extends Resource> {

    protected final List<T> resources = new LinkedList<>();
    private final Class<T> resourceType;

    public ResourceCollector(Class<T> clazz) {
        resourceType = clazz;
    }

    public T resource() {
        T instance = ResourceUtils.createResource(resourceType);
        resources.add(instance);
        return instance;
    }
}
