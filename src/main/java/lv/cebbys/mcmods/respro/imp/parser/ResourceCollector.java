package lv.cebbys.mcmods.respro.imp.parser;

import lv.cebbys.mcmods.respro.imp.resource.Resource;
import lv.cebbys.mcmods.respro.imp.utilities.ResourceUtils;

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
