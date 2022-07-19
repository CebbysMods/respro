package lv.cebbys.mcmods.respro.component.core.reflect;

import java.lang.reflect.Method;

public class MethodTemplate<P, R> {
    private final Class<R> returnType;
    private final String name;
    private final Class<?>[] parameters;

    public MethodTemplate(Class<R> r, String n, Class<?>... p) {
        returnType = r;
        name = n;
        parameters = p;
    }

    public R invoke(P parent, Object... args) {
        try {
            Method method = getMethod(parent);
            method.setAccessible(true);
            return returnType.cast(method.invoke(parent, args));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Method getMethod(P parent) {
        Class<?> clazz = parent.getClass();
        while (clazz != null) {
            try {
                Method method = clazz.getDeclaredMethod(name, parameters);
                method.setAccessible(true);
                return method;
            } catch (Exception e) {
                clazz = clazz.getSuperclass();
            }
        }
        throw new RuntimeException("Method " + name + " not found for class " + parent.getClass());
    }
}
