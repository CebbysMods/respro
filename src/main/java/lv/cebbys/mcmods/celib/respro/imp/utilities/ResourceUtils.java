package lv.cebbys.mcmods.celib.respro.imp.utilities;

import lv.cebbys.mcmods.celib.respro.imp.loggers.ResproLogger;
import lv.cebbys.mcmods.celib.respro.imp.sides.common.resources.Resource;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ResourceUtils {
    public static <T extends Resource> T createResource(Class<T> clazz) {
        Throwable error;
        try {
            Constructor<T> constructor = clazz.getConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
            error = e;
            ResproLogger.error(e, "Failed to instantiate resource " + clazz.getName());
        } catch (NoSuchMethodException e) {
            error = e;
            ResproLogger.error(e, "No argument constructor is present in class " + clazz.getName());
        }
        throw new RuntimeException(error);
    }
}
