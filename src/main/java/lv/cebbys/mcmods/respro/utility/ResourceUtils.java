package lv.cebbys.mcmods.respro.utility;

import lv.cebbys.mcmods.respro.component.resource.binary.BinaryResource;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ResourceUtils {
    public static <T extends BinaryResource> T createResource(Class<T> clazz) {
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
}
