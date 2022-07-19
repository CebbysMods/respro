package lv.cebbys.mcmods.respro.utility;

import lv.cebbys.mcmods.respro.component.resource.string.kleeslabs.KleeSlabsCompatResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ClassUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtils.class);

    public static final String KLEESLABS_CONVERTER_CLASSNAME;

    public static List<Class<?>> getParentClasses(Class<?> child) {
        List<Class<?>> parents = new ArrayList<>();
        do {
            child = child.getSuperclass();
            parents.add(child);
        } while (child != Object.class);
        return parents;
    }

    public static boolean isInstanceOf(String parent, Class<?> child) {
        List<Class<?>> parents = getParentClasses(child);
        return parents.stream().anyMatch(c -> c.toString().equals(parent));
    }

    public static boolean isInstanceOf(Class<?> parent, Class<?> child) {
        return isInstanceOf(parent.getName(), child);
    }

    public static void main(String... args) {
        Class<?> c = KleeSlabsCompatResource.class;
        List<String> classes = new ArrayList<>();
        classes.add(c.getName());
        do {
            c = c.getSuperclass();
            classes.add(c.getName());
        } while (c != Object.class);

        for (String s : classes) {
            System.out.println(s);
        }
    }

    static {
        KLEESLABS_CONVERTER_CLASSNAME = "net.blay09.mods.kleeslabs.converter.SlabConverter";
    }

}
