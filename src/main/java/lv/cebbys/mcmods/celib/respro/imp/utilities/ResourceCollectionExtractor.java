package lv.cebbys.mcmods.celib.respro.imp.utilities;

import lv.cebbys.mcmods.celib.respro.imp.resources.Resource;

import java.util.List;

public abstract class ResourceCollectionExtractor<T extends Resource> extends ResourceCollector<T> {

    public static <T extends Resource> List<T> collect(ResourceCollector<T> collector) {
        return collector.resources;
    }

    private ResourceCollectionExtractor(Class<T> clazz) {
        super(clazz);
    }
}
