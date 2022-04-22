package lv.cebbys.mcmods.respro.imp.parser;

import lv.cebbys.mcmods.respro.imp.resource.Resource;

import java.util.List;

public abstract class ResourceCollectionExtractor<T extends Resource> extends ResourceCollector<T> {

    public static <T extends Resource> List<T> collect(ResourceCollector<T> collector) {
        return collector.resources;
    }

    private ResourceCollectionExtractor(Class<T> clazz) {
        super(clazz);
    }
}
