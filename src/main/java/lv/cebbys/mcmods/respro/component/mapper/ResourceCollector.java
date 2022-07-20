package lv.cebbys.mcmods.respro.component.mapper;

import lv.cebbys.mcmods.respro.component.resource.core.BinaryResource;
import lv.cebbys.mcmods.respro.utility.ResourceUtils;

import java.util.LinkedList;
import java.util.List;

public class ResourceCollector<T extends BinaryResource> {

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
