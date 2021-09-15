package lv.cebbys.mcmods.celib.respro.imp.utilities;

import lv.cebbys.mcmods.celib.respro.imp.loggers.ResproLogger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public abstract class ResourceWrapper<K, T> {
    protected Map<K, T> data = new LinkedHashMap<>();

    protected abstract Class<?> wrappedClass();

    protected T resource(K name) {
        return resource(Collections.singleton(name));
    }

    protected T resource(K... names) {
        return resource(Arrays.asList(names));
    }

    protected T resource(List<K> names) {
        return resource(new LinkedHashSet<>(names));
    }

    protected T resource(Set<K> names) {
        T resource = createResource();
        if(names.stream().anyMatch(Objects::isNull)) {
            ResproLogger.fail("Specified keys contains null " + names);
        } else if(names.stream().anyMatch(key -> data.containsKey(key))) {
            ResproLogger.fail("Resources are already present with specified keys " + names);
        } else {
            names.forEach(name -> {
                data.put(name, resource);
            });
        }
        return resource;
    }

    protected T resources(K name, T resource) {
        data.put(name, resource);
        return resource;
    }

    @SuppressWarnings("unchecked")
    protected T createResource() {
        Class<?> wrapped = wrappedClass();
        Throwable error;
        try {
            Constructor<?> constructor = wrapped.getConstructor();
            constructor.setAccessible(true);
            return (T) constructor.newInstance();
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
            error = e;
            ResproLogger.error(e, "Failed to instantiate resource " + wrapped.getName());
        } catch (NoSuchMethodException e) {
            error = e;
            ResproLogger.error(e, "No argument constructor is present in class " + wrapped.getName());
        }
        throw new RuntimeException(error);
    }
}
